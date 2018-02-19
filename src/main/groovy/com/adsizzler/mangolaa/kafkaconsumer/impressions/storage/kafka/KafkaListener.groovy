package com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.kafka


import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.constants.KafkaTopics
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.domain.Impression
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.request.ImpressionRequest
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.service.ImpressionService
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.util.util.Gzip
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.util.util.Json
import groovy.util.logging.Slf4j
import io.vertx.kafka.client.consumer.KafkaConsumer
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * Created by ankushsharma on 19/02/18.
 */
@Component
@Slf4j
class KafkaListener {

    private final KafkaConsumer<String,byte[]> kafka
    private final ImpressionService impressionService

    KafkaListener(
            KafkaConsumer<String,byte[]> kafka,
            ImpressionService impressionService
    )
    {
        this.kafka = kafka
        this.impressionService = impressionService
    }

    @PostConstruct
    void saveImpressions(){

        kafka.handler{ record ->

            def offset = record.offset()
            log.debug 'Offset {}' , offset

            // payload -> Decompress Gzip -> JSON -> POJO
            def payload = record.value()
            def json = Gzip.decompress(payload)

            log.trace 'JSON {}', json

            def req = Json.toObject(json, ImpressionRequest)
            def impression = new Impression(req)
            log.debug 'Impression {}', impression

            impressionService.save(impression)

        }
        .exceptionHandler{ Throwable ex ->
            log.error '', ex
        }
        kafka.subscribe(KafkaTopics.IMPRESSIONS)
    }

}
