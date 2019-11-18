package me.amuxix.triggerisechallenge

import me.amuxix.triggerisechallenge.discountrules.{CFO, Marketing}

object TriggeriseChallenge {
  lazy val settings = Settings.fromConfig()

  def main(args: Array[String]): Unit = {
    println(
      Checkout(List(CFO(settings), Marketing(settings)))
        .scan("TICKET")
        .scan("HOODIE")
        .scan("HAT")
        .total
    )

    println(
      Checkout(List(CFO(settings), Marketing(settings)))
        .scan("TICKET")
        .scan("TICKET")
        .scan("HOODIE")
        .total
    )

    println(
      Checkout(List(CFO(settings), Marketing(settings)))
        .scan("HOODIE")
        .scan("HOODIE")
        .scan("HOODIE")
        .scan("TICKET")
        .scan("HOODIE")
        .total
    )

    println(
      Checkout(List(CFO(settings), Marketing(settings)))
        .scan("TICKET")
        .scan("HOODIE")
        .scan("TICKET")
        .scan("TICKET")
        .scan("HAT")
        .scan("HOODIE")
        .scan("HOODIE")
        .total
    )
  }
}
