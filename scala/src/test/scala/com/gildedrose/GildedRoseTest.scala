package com.gildedrose

import org.scalatest._

class GildedRoseTest  extends FlatSpec with Matchers {
  it should "foo" in {
    var items = Array[Item](new Item("foo", 0, 0))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).name) should equal ("foo")
  }

  "base item" should "decrease quality and sellIn" in {
    var items = Array[Item](new Item("base item", 10, 5))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (4)
    (app.items(0).sellIn) should equal (9)
  }

  "base item" should "not decrease quality below zero" in {
    var items = Array[Item](new Item("base item", 10, 0))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (0)
  }

  "base item" should "decrease sellIn to -1 on sellIn 0" in {
    var items = Array[Item](new Item("base item", 0, 5))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).sellIn) should equal (-1)
  }

  "base item" should "decrease sellIn to -2 on sellIn -1" in {
    var items = Array[Item](new Item("base item", -1, 5))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).sellIn) should equal (-2)
  }

  "base item" should "decrease twice as fast after sellIn is smaller than zero" in {
    var items = Array[Item](new Item("base item", 0, 5))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (3)
  }

  "Aged Brie" should "increase in Quality the older it gets" in {
    var items = Array[Item](new Item("Aged Brie", 2, 5))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (6)
  }

  "Aged Brie" should "increase in Quality after sellIn with double rate" in {
    // legacy behaviour, covered but not specified
    var items = Array[Item](new Item("Aged Brie", 0, 5))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (7)
  }

  "base item" should "never have a quality higher than 50" in {
    pending
    var items = Array[Item](new Item("base item", 0, 55))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (50)
  }

  "Aged Brie" should "never have a Quality higher than 50" in {
    var items = Array[Item](new Item("Aged Brie", 0, 50))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (50)
  }

  "Sulfuras" should "never has to be sold" in {
    var items = Array[Item](new Item("Sulfuras, Hand of Ragnaros", 77, 50))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).sellIn) should equal (77)
  }

  "Sulfuras" should "never decreases in Quality" in {
    var items = Array[Item](new Item("Sulfuras, Hand of Ragnaros", 77, 50))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (50)
  }

  "Backstage passes" should "increases in Quality" in {
    var items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (11)
  }

  "Backstage passes" should "increases in Quality by 2 when there are 10 days or less" in {
    var items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (12)
  }

  "Backstage passes" should "increases in Quality by 3 when there are 5 days or less" in {
    var items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (13)
  }

  "Backstage passes" should "decreases Quality to 0 after the concert" in {
    var items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal (0)
  }

}