package com.example.config

import java.util.Date

import com.example.model.{CustomDate, CustomString, FieldMapping}
import com.example.storage.{ConfigStorage, UISyncConfig}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class ConfigStorageTest extends FunSpec with Matchers with ConfigsTempFolder {
  private val configName = "some_config_name"
  private val login = "autotest"

  it("config can be created in storage") {
    withTempFolder { folder =>
      val storage = new ConfigStorage(folder)
      val mappings = Seq(
        FieldMapping(CustomString("str"), CustomString("another"), true, "default"),
        FieldMapping(CustomDate("date1"), CustomDate("date2"), true, new Date())
      )
      val configId = storage.saveConfig(new UISyncConfig(mappings))
      //      val config = storage.getConfig(configId)
      //      config.isDefined shouldBe true
      //      config.get.getMappingsString shouldBe "mappings"
    }
  }
}
