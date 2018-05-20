package com.example.config

import com.example.storage.UISyncConfig
import com.example.storage.Formats

import com.example.model._

/**
 * A test for some configs.
 */
final class ConfigTest extends org.scalatest.FunSuite {
  test("Serialization is OK") {
    roll(Summary, Summary, "text")
    roll(Assignee, Assignee, null)
    roll(Assignee, Assignee, GUser(null, "log", "in"))
    roll(Components, Components, Seq.empty)
    roll(Components, Components, Seq("Left", "Right"))
  }


  private def roll[T](a : Field[T], b : Field[T], d : T) : Unit = {
    roll(FieldMapping(a, b, false, d))
    roll(FieldMapping(a, b, true, d))
  }


  private def roll[T](row : FieldMapping[T]) : Unit =
    roll(UISyncConfig(Seq(row)))


  /** Validates an conversion. */
  private def roll(x : UISyncConfig) : Unit =
    assert(x === Formats.json2Config(Formats.config2Json(x)))
}
