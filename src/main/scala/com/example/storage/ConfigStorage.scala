package com.example.storage

import java.io.File

import com.example.storage.CirceBoilerplateForConfigs._
import io.circe.syntax._
import com.example.model.FieldMapping

case class UISyncConfig(mappings: Seq[FieldMapping[_]])

class ConfigStorage(folder: File) {

  def saveConfig(syncConfig: UISyncConfig): Unit = {
    val mappings = syncConfig.mappings
    val mappingsStr = mappings.asJson.noSpaces
    println(mappingsStr)
  }
}
