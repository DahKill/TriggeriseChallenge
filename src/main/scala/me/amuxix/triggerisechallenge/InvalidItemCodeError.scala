package me.amuxix.triggerisechallenge

import scala.util.control.NoStackTrace

case class InvalidItemCodeError(itemCode: String) extends Exception with NoStackTrace {
  override def getMessage: String = s"Could not find Item with item code: $itemCode!"
}
