package com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.domain

import com.adsizzler.mangolaa.kafkaconsumer.impressions.storage.request.ImpressionRequest
import groovy.transform.ToString
import org.springframework.util.Assert

import java.time.ZonedDateTime

/**
 * Created by ankushsharma on 19/02/18.
 */
@ToString(includePackage = false)
class Impression {

    final UUID uuid
    final ZonedDateTime timestamp
    final String openRtbVer
    final Integer campaignId
    final Integer creativeId
    final Integer advId
    final Integer srcId
    final Integer clientId
    final String bidReqId
    final UUID bidRespUuid
    final String impId
    final String seatId
    final String adId
    final String cur
    final Double price
    final Double mbr
    final Integer lossCode

    Impression(ImpressionRequest req){
        Assert.notNull(req, 'ImpressionRequest req cannot be null')
        this.uuid = req.uuid
        this.timestamp = req.timestamp
        this.openRtbVer = req.openRtbVer
        this.campaignId = req.campaignId
        this.creativeId = req.creativeId
        this.advId = req.advId
        this.srcId = req.sourceId
        this.bidReqId = req.bidReqId
        this.bidRespUuid = req.bidRespUuid
        this.clientId = req.clientId
        this.impId = req.impId
        this.seatId = req.seatId
        this.adId = req.adId
        this.cur = req.cur
        this.price = req.price
        this.mbr = req.mbr
        this.lossCode = req.lossCode
    }

}
