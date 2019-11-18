package me.amuxix.triggerisechallenge.discountrules

import me.amuxix.triggerisechallenge.ItemCode.Hoodie
import me.amuxix.triggerisechallenge.{Item, Settings}

case class CFO(settings: Settings) extends DiscountRule {

  override def calculateDiscount(items: List[Item]): Int = {
    val hoodies = items.filter(_.code == Hoodie)
    if (hoodies.size >= settings.discounts.minimumBulkAmount) {
      (hoodies.head.priceInCents - settings.discounts.hoodieDiscountedPrice) * hoodies.size
    } else {
      0
    }
  }
}