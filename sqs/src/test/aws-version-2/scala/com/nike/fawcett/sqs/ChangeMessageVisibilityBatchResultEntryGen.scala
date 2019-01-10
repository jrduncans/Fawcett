package com.nike.fawcett.sqs

import org.scalacheck._
import Arbitrary.arbitrary
import software.amazon.awssdk.services.sqs.model.ChangeMessageVisibilityBatchResultEntry

/* Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-3-Clause license found in
 * the LICENSE file in the root directory of this source tree.
 */

object ChangeMessageVisibilityBatchResultEntryGen {
  implicit val cogenChangeMessageVisibilityBatchResultEntry: Cogen[ChangeMessageVisibilityBatchResultEntry] =
    Cogen[String].contramap(_.id)

  val genChangeMessageVisibilityBatchResultEntry = for {
    id <- arbitrary[String]
  } yield {
    ChangeMessageVisibilityBatchResultEntry.builder
      .id(id)
      .build
  }

  implicit val arbitraryChangeMessageVisibilityBatchResultEntry = Arbitrary(genChangeMessageVisibilityBatchResultEntry)
}
