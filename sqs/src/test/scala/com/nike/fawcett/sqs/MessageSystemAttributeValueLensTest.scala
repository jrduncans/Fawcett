package com.nike.fawcett.sqs

import MessageAttributeValueLens._
import cats.implicits._
import org.scalatest.matchers.should.Matchers
import org.scalatest.OptionValues
import org.scalatest.funsuite.AnyFunSuite
import monocle.law.discipline.PrismTests
import org.typelevel.discipline.scalatest.Discipline

/* Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-3-Clause license found in
 * the LICENSE file in the root directory of this source tree.
 */

class MessageAttributeValueLensTest extends AnyFunSuite with Matchers with Discipline with OptionValues {
  import MessageAttributeValueGen._

  checkAll("stringValue", PrismTests(MessageAttributeValueLens.stringValue))
  checkAll("numberValue", PrismTests(MessageAttributeValueLens.numberValue))
  checkAll("binaryValue", PrismTests(MessageAttributeValueLens.binaryValue))
}
