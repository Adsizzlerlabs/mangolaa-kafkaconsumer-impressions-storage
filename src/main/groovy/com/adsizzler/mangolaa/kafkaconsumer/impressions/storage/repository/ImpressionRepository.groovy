package com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.repository

import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.annotation.TestingOnly
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.domain.Impression
import io.vertx.core.Future

/**
 * Created by ankushsharma on 19/02/18.
 */
interface ImpressionRepository {

    Future<Void> save(Impression impression)

}
