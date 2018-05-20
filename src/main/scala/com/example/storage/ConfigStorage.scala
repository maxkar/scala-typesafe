package com.example.storage

import java.io.File

import com.example.model.FieldMapping

import ru.maxkar.json.Json
import ru.maxkar.json.JsonValue
import ru.maxkar.json.implicits._

case class UISyncConfig(mappings: Seq[FieldMapping[_]])

class ConfigStorage(folder: File) {

  def saveConfig(syncConfig: UISyncConfig) : Unit = {
    val jsonObj : JsonValue =
      syncConfig.mappings.map(Formats.mapping2Json)
    println(Json.toString(jsonObj))
  }
}
