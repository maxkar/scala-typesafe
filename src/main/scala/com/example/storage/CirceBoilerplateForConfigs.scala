package com.example.storage

import com.example.model.{DateField, Field, FieldMapping, StringField}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

object CirceBoilerplateForConfigs {
  implicit val stringDecoder: Decoder[StringField] = deriveDecoder[StringField]
  implicit val stringEncoder: Encoder[StringField] = deriveEncoder[StringField]

  implicit val dateDecoder: Decoder[DateField] = deriveDecoder[DateField]
  implicit val dateEncoder: Encoder[DateField] = deriveEncoder[DateField]

  implicit val fieldDecoder: Decoder[Field[_]] = deriveDecoder[Field[_]]
  implicit val fieldEncoder: Encoder[Field[_]] = deriveEncoder[Field[_]]

  implicit val fooDecoder: Decoder[FieldMapping[_]] = deriveDecoder[FieldMapping[_]]
  implicit val fooEncoder: Encoder[FieldMapping[_]] = deriveEncoder[FieldMapping[_]]
}