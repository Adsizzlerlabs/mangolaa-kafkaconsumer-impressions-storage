package com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.service.impl

import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.domain.Impression
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.repository.ImpressionRepository
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.service.ImpressionService
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.util.util.Assert
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import org.springframework.stereotype.Service


/**
 * Created by ankushsharma on 19/02/18.
 */
@Service
@Slf4j
class ImpressionServiceImpl implements ImpressionService {

    private final ImpressionRepository repository

    ImpressionServiceImpl(ImpressionRepository repository){
        this.repository = repository
    }

    @Override
    Future<Void> save(Impression impression) {
        Assert.notNull(impression, 'impression cannot be null')
        repository.save(impression)
    }
}
