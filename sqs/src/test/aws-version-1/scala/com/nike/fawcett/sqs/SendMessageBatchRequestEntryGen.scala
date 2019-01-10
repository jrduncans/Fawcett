package com.nike.fawcett.sqs

import com.amazonaws.services.sqs.model.{SendMessageBatchRequestEntry, MessageAttributeValue}
import org.scalacheck._
import Gen._
import Arbitrary.arbitrary
import scala.collection.JavaConverters._
import scala.concurrent.duration._

/* Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-3-Clause license found in
 * the LICENSE file in the root directory of this source tree.
 */

object SendMessageBatchRequestEntryGen {

  implicit val cogenSendMessageBatchRequestEntry: Cogen[SendMessageBatchRequestEntry] =
    Cogen.tuple6[String, Int, Map[String, MessageAttributeValue], String, String, String].contramap { request =>
      (request.getId,
       request.getDelaySeconds,
       request.getMessageAttributes.asScala.toMap,
       request.getMessageBody,
       request.getMessageDeduplicationId,
       request.getMessageGroupId)
    }

  implicit val arbitrarySendMessageBatchRequestEntry = Arbitrary {
    for {
      delaySeconds <- choose(0, 15.minutes.toSeconds.toInt)
      id <- identifier(0, 80, '_', '-')
      attributes <- mapOf(attributeMapEntryGen)
      body <- arbitrary[String]
      deduplicationId <- identifier(0, 128, allValidSymbols :_*)
      groupId <- identifier(0, 128, allValidSymbols :_*)
    } yield {
      new SendMessageBatchRequestEntry()
        .withId(id)
        .withDelaySeconds(delaySeconds)
        .withMessageAttributes(attributes.asJava)
        .withMessageBody(body)
        .withMessageDeduplicationId(deduplicationId)
        .withMessageGroupId(groupId)
    }
  }
}
