package com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.service

import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.domain.Impression
import io.vertx.core.Future

/**
 * Created by ankushsharma on 19/02/18.
 */
interface ImpressionService {

    Future<Void> save(Impression impression)

}
