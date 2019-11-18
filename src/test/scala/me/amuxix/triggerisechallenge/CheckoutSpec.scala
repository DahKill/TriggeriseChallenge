package me.amuxix.triggerisechallenge

import me.amuxix.triggerisechallenge.Item.{Hat, Hoodie, Ticket}
import me.amuxix.triggerisechallenge.discountrules.{CFO, Marketing}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class CheckoutSpec extends AnyWordSpec with Matchers {
  private lazy val settings = Settings.fromConfig()

  "A Checkout" should {
    "empty" should {
      "have total of 0" in {
        Checkout().totalPriceInCents shouldEqual 0
      }

      "have total formatted as 0.00€" in {
        Checkout().total shouldEqual "0.00€"
      }

      "scan items" in {
        Checkout().scan("Hat").items should contain (Hat)
      }

      "fail to scan if item code is invalid" in {
        an [InvalidItemCodeError] should be thrownBy Checkout().scan("Invalid")
      }
    }
    "non-empty" should {
      "scan items" in {
        Checkout(items = List(Hat))
          .scan("HOODIE")
          .items should contain allOf (Hat, Hoodie)
      }
      "fail scanning invalid item" in {
        an [InvalidItemCodeError] should be thrownBy Checkout(items = List(Hat)).scan("Invalid")
      }
      "without discounts" should {
        "calculate total of a single item" in {
          Checkout(items = List(Hat)).totalPriceInCents shouldEqual Hat.priceInCents
        }
        "should format price of a single item correctly" in {
          Checkout(items = List(Hat)).total shouldEqual "7.50€"
        }
        "apply no discounts" in {
          Checkout(items = List(Ticket, Ticket)).totalPriceInCents shouldEqual Ticket.priceInCents * 2
        }
      }
      "with discounts" should {
        val cfoDiscount = List(CFO(settings))
        val marketingDiscount = List(Marketing(settings))
        val bothDiscounts = cfoDiscount ++ marketingDiscount
        val minBulk = settings.discounts.minimumBulkAmount
        "apply CFO discount" in {
          Checkout(cfoDiscount, List.fill(minBulk)(Hoodie)).totalPriceInCents shouldEqual (settings.discounts.hoodieDiscountedPrice * minBulk)
        }

        "not CFO discount if below minimum bulk amount t" in {
          val belowMinBulk = minBulk - 1
          Checkout(cfoDiscount, List.fill(belowMinBulk)(Hoodie)).totalPriceInCents shouldEqual (Hoodie.priceInCents * belowMinBulk)
        }

        "apply Marketing discount with even number of tickets" in {
          Checkout(marketingDiscount, List.fill(2)(Ticket)).totalPriceInCents shouldEqual (Ticket.priceInCents)
        }

        "apply Marketing discount with odd number tickets" in {
          Checkout(marketingDiscount, List.fill(3)(Ticket)).totalPriceInCents shouldEqual (Ticket.priceInCents * 2)
        }

        "apply both discounts" in {
          val items = List.fill(2)(Ticket) ++ List.fill(minBulk)(Hoodie)
          Checkout(bothDiscounts, items).totalPriceInCents shouldEqual (Ticket.priceInCents + settings.discounts.hoodieDiscountedPrice * minBulk)
        }
        "apply both discounts discounts no matter the order items were added" in {
          val totalPrice = Checkout(bothDiscounts)
            .scan("HOODIE")
            .scan("TICKET")
            .scan("HOODIE")
            .scan("TICKET")
            .scan("HOODIE")
            .totalPriceInCents
          totalPrice shouldEqual (Ticket.priceInCents + settings.discounts.hoodieDiscountedPrice * 3)
        }
      }
    }
  }
}
