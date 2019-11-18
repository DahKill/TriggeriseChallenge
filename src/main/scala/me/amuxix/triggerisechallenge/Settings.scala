package me.amuxix.triggerisechallenge

import pureconfig.generic.auto._
import com.typesafe.config.{Config, ConfigFactory}
import pureconfig.ConfigSource

case class DiscountSettings(
  minimumBulkAmount: Int = 3,
  hoodieDiscountedPrice: Int = 1900,
)

case class Settings(
  discounts: DiscountSettings,
)

object Settings {
  def fromConfig(config: Config = ConfigFactory.load()): Settings = ConfigSource.fromConfig(config).at("triggerise").loadOrThrow[Settings]
}
