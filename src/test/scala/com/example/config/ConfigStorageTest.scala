package com.example.config

import com.example.model.{DateField, FieldMapping, StringField}
import com.example.storage.{ConfigStorage, SetupId, UISyncConfig}
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
        FieldMapping(StringField("str"), StringField("another"), true, "default"),
        FieldMapping(DateField("date1"), DateField("date2"), true, "default")
      )
      val configId = storage.saveConfig(new UISyncConfig(mappings))
      //      val config = storage.getConfig(configId)
      //      config.isDefined shouldBe true
      //      config.get.getMappingsString shouldBe "mappings"
    }
  }
}
