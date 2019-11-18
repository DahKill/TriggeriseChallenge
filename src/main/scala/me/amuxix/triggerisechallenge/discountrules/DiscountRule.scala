package me.amuxix.triggerisechallenge.discountrules

import me.amuxix.triggerisechallenge.{Item, Settings}

trait DiscountRule {
  def settings: Settings
  def calculateDiscount(items: List[Item]): Int
}
