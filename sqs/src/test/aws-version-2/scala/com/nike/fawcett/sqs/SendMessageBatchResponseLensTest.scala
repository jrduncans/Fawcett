package com.nike.fawcett.sqs

import cats.implicits._
import org.scalatest.{ FunSuite, Matchers }
import monocle.law.discipline.LensTests
import org.typelevel.discipline.Laws
import SendMessageBatchResponseLens._
import SendMessageBatchResultEntryLens._
import BatchResultErrorEntryLens._
import org.typelevel.discipline.scalatest.Discipline
import software.amazon.awssdk.services.sqs.model.{BatchResultErrorEntry, SendMessageBatchResultEntry}
import BatchResultErrorEntryGen._
import SendMessageBatchResultEntryGen._

/* Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-3-Clause license found in
 * the LICENSE file in the root directory of this source tree.
 */

class SendMessageBatchResponseLensTest extends FunSuite with Matchers with Discipline with Laws {
  import SendMessageBatchResponseGen._

  checkAll("failed", LensTests(failed))
  checkAll("successful", LensTests(successful))

  checkAll("anyFailedBySender", existsTraversal(
    anyFailedBySender,
    containsCondition = genSendMessageBatchResponse(FailuresAndSuccess.includesSpecific[BatchResultErrorEntry, SendMessageBatchResultEntry](
      specific = genBatchResultErrorEntry(Some(true)),
      nonSpecific = genBatchResultErrorEntry(),
      min = 1,
      max = 10
    )),
    doesntContainCondition = genSendMessageBatchResponse(FailuresAndSuccess.includesSpecific[BatchResultErrorEntry, SendMessageBatchResultEntry](
      specific = genBatchResultErrorEntry(Some(false)),
      nonSpecific = genBatchResultErrorEntry(Some(false)),
      min = 1,
      max = 10
    ))))
}