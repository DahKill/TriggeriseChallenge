package me.amuxix.triggerisechallenge

import me.amuxix.triggerisechallenge.discountrules.DiscountRule

case class Checkout(discountRules: List[DiscountRule] = List.empty, items: List[Item] = List.empty) {
  def scan(itemCode: String): Checkout = {
    ItemCode.withNameInsensitiveOption(itemCode)
      .flatMap(Item.fromItemCode)
      .fold(throw InvalidItemCodeError(itemCode) ) { item =>
        copy(items = items :+ item)
      }
  }

  def totalPriceInCents: Int = {
    val totalPrice = items.foldLeft(0)(_ + _.priceInCents)
    discountRules.foldLeft(totalPrice)(_ - _.calculateDiscount(items))
  }

  def total: String =
    "%.2f€".format(totalPriceInCents.toDouble / 100)
}
