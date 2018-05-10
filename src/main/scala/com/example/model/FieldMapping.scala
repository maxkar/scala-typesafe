package com.example.model

object FieldMapping {
  def apply(fieldInConnector1: Field[Any], fieldInConnector2: Field[Any], selected: Boolean, defaultValue: String): FieldMapping = {
    FieldMapping(Some(fieldInConnector1), Some(fieldInConnector2), selected, defaultValue)
  }
}

case class FieldMapping(fieldInConnector1: Option[Field[Any]], fieldInConnector2: Option[Field[Any]], selected: Boolean, defaultValue: String)
