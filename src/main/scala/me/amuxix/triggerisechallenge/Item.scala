package me.amuxix.triggerisechallenge

import enumeratum.EnumEntry

/**
 * Represents an item to be sold on a physical store.
 *
 * @param code Unique identifier for an item
 * @param name Name of the product
 * @param priceInCents price in cents of the item
 */
sealed case class Item(
  code: ItemCode,
  name: String,
  priceInCents: Int,
) extends EnumEntry

object Item {
  def fromItemCode(itemCode: ItemCode): Option[Item] = itemCode match {
    case ItemCode.Ticket => Some(Ticket)
    case ItemCode.Hoodie => Some(Hoodie)
    case ItemCode.Hat => Some(Hat)
    case _ => None
  }

  object Ticket extends Item(ItemCode.Ticket, "Triggerise Ticket", 500)
  object Hoodie extends Item(ItemCode.Hoodie, "Triggerise Hoodie", 2000)
  object Hat extends Item(ItemCode.Hat, "Triggerise Hat", 750)
}
