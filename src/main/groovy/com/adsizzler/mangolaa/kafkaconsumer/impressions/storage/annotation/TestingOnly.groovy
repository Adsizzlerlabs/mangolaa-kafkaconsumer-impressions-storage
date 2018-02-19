package com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Marker annotation to mark methods that will be used only for unit testing purposes.
 * Created by ankushsharma on 05/02/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TestingOnly {

}