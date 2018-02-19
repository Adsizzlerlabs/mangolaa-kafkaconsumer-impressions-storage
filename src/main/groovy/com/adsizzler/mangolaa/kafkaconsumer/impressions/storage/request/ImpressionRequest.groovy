package com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.request

import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.jackson.UUIDDeserializer
import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.jackson.ZonedDateTimeDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize

import java.time.ZonedDateTime

/**
 * Created by ankushsharma on 19/02/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class ImpressionRequest {

    @JsonProperty(value = "uuid", required = true)
    @JsonDeserialize(using = UUIDDeserializer)
    final UUID uuid

    @JsonProperty(value = "timestamp", required = true)
    @JsonDeserialize(using = ZonedDateTimeDeserializer)
    final ZonedDateTime timestamp

    @JsonProperty(value = 'openRtbVer', required = true)
    final String openRtbVer

    @JsonProperty(value = 'advId', required = true)
    final Integer advId

    @JsonProperty(value = "campId", required = true)
    final Integer campaignId

    @JsonProperty(value = "creativeId", required = true)
    final Integer creativeId

    @JsonProperty(value = "clientId", required = true)
    final Integer clientId

    @JsonProperty(value = "srcId", required = true)
    final Integer sourceId

    @JsonProperty(value = 'bidReqId')
    final String bidReqId

    @JsonProperty(value = 'winUuid')
    final String winUuid

    @JsonProperty(value = 'bidRespUuid')
    @JsonDeserialize(using = UUIDDeserializer)
    final UUID bidRespUuid

    @JsonProperty(value = 'impId')
    final String impId

    @JsonProperty(value = 'seatId')
    final String seatId

    @JsonProperty(value = 'adId')
    final String adId

    @JsonProperty(value = 'cur')
    final String cur

    @JsonProperty(value = 'price')
    final Double price

    @JsonProperty(value = 'mbr')
    final Double mbr

    @JsonProperty(value = 'lossCode')
    final Integer lossCode


}
