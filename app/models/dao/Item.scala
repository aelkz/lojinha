package models.dao

import org.joda.time.DateTime

case class Category(id: Int, name: String)
case class Item(id: Int, name: String, description: String, imageKeys: Option[String], cat: Category, sold: Boolean = false)
case class Bid(id: Int, bidderEmail: String, value: BigDecimal, dateTime: DateTime, item: Item) extends Ordered[Bid] {
  def compare(otherBid: Bid) = (value - otherBid.value).toInt
}

object Bid {
  def apply(bidderEmail: String, value: BigDecimal, item: Item): Bid = Bid(0, bidderEmail, value, new DateTime, item)
}

trait CategoryDAO {
  def create(name: String)

  def findById(id: Int): Option[Category]
  def findByName(name: String): Option[Category]

  def getByName(name: String): Category

  def all(): List[Category]
}

trait ItemDAO {
  def create(name: String, description: String, imageKeys: Option[String], cat: Category)

  def sell(id: Int): Option[Item]

  def findById(id: Int): Option[Item]

  def all(sold: Boolean): List[Item]

  def all(cat: Category, sold: Boolean): List[Item]

  def delete(id: Long)
}

trait BidDAO {
  def create(bid: Bid)

  def all(itemId: Int): List[Bid]

  def highest(itemId: Int): Option[Bid]
}
