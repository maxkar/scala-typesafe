package com.example.config

import com.example.model.{Field, FieldMapping}

/**
  * Given two maps of available connector-specific fields (for connector 1 and connector 2),
  * build a list of [[com.example.model.FieldMapping]] with suggested mappings: what fields in connector 1
  * are equivalent to fields in connector 2.
  * Equivalent means they refer to the same [[com.example.model.Field]].
  */
object NewConfigSuggester {
  val DEFAULT_VALUE_FOR_EMPTY_VALUES = ""

  def suggestedFieldMappingsForNewConfig(list1: Seq[Field[_]], list2: Seq[Field[_]]): Seq[FieldMapping[_]] = {
    list1.intersect(list2).map(f => getMapping(f))
  }

  private def getMapping[T](field: Field[T]): FieldMapping[T] = {
    FieldMapping.apply(field, field, true, null.asInstanceOf[T])
  }
}
