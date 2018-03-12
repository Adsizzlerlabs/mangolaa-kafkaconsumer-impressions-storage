package com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.repository.impl

import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.domain.Impression
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.repository.ImpressionRepository
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.util.Assert
import com.datastax.driver.core.ResultSet
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import org.springframework.beans.factory.annotation.Value
import org.springframework.cassandra.core.CqlTemplate
import org.springframework.stereotype.Repository

import javax.annotation.PreDestroy
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by ankushsharma on 19/02/18.
 */
@Repository
@Slf4j
class CassandraImpressionRepositoryImpl implements ImpressionRepository {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor()

    private final String keyspace
    private final String table
    private final CqlTemplate cqlTemplate

    CassandraImpressionRepositoryImpl(
            CqlTemplate cqlTemplate,
            @Value("\${impressions.cassandra.keyspace}") String keyspace,
            @Value("\${impressions.cassandra.table}") String table
    )
    {
        this.cqlTemplate = cqlTemplate
        this.table = table
        this.keyspace = keyspace
    }

    @PreDestroy
    void cleanup() {
        log.info 'Attempting to shutdown executor for class {}', this.class.name
        executor.shutdown()
    }

    @Override
    Future<Void> save(Impression impression) {
        Assert.notNull(impression, 'impression cannot be null')
        def future = Future.future()

        def cassandraTimestamp  = Date.from(impression.timestamp.toInstant())

        def insertStatement = QueryBuilder
                                .insertInto(keyspace, table)
                                    .value('uuid', impression.uuid)
                                    .value('timestamp', cassandraTimestamp)
                                    .value('ortb_ver', impression.openRtbVer)
                                    .value('cr_id', impression.creativeId)
                                    .value('camp_id', impression.campaignId)
                                    .value('src_id', impression.srcId)
                                    .value('adv_id', impression.advId)
                                    .value('cl_id', impression.clientId)
                                    .value('bid_req_id', impression.bidReqId)
                                    .value('bid_resp_id', impression.bidRespUuid)
                                    .value('imp_id', impression.impId)
                                    .value('ad_id', impression.adId)
                                    .value('price', impression.price)
                                    .value('seat_id', impression.seatId)
                                    .value('cur', impression.cur)
                                    .value('mbr', impression.mbr)
                                    .value('loss_code', impression.lossCode)

        def insertFuture = cqlTemplate.executeAsynchronously(insertStatement)

        Futures.addCallback(insertFuture, [
                onSuccess : { ResultSet resultSet ->
                    log.debug 'ResultSet {}', resultSet?.one()
                    future.complete()
                },
                onFailure : { Throwable ex ->
                    future.fail(ex)
                }

        ] as FutureCallback<ResultSet>, executor)

        future
    }
}
