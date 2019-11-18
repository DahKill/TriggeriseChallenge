package me.amuxix.triggerisechallenge

import enumeratum.{Enum, EnumEntry}

sealed trait ItemCode extends EnumEntry

object ItemCode extends Enum[ItemCode] {

  val values = findValues

  case object Ticket extends ItemCode
  case object Hoodie extends ItemCode
  case object Hat extends ItemCode
}