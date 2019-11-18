package me.amuxix.triggerisechallenge.discountrules

import me.amuxix.triggerisechallenge.{Item, Settings}
import me.amuxix.triggerisechallenge.ItemCode.Ticket

case class Marketing(settings: Settings) extends DiscountRule {
  override def calculateDiscount(items: List[Item]): Int = {
    val tickets = items.filter(_.code == Ticket)
    tickets.headOption.fold(0) { item =>
      (tickets.size / 2) * item.priceInCents
    }
  }
}
