package com.example.storage

import java.io.File

import io.circe.Json
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import scala.collection.JavaConverters._
import com.example.storage.CirceBoilerplateForConfigs._

class ConfigStorage(folder: File) {

  def saveConfig(syncConfig: UISyncConfig): Unit = {
    val mappings = syncConfig.mappings
    val mappingsStr = mappings.asJson.noSpaces
    println(mappingsStr)
  }
}
