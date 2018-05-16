package com.example.storage

import com.example.model.{DateField, Field, FieldMapping, StringField}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

object CirceBoilerplateForConfigs {
  implicit val stringDecoder: Decoder[StringField] = deriveDecoder[StringField]
  implicit val stringEncoder: Encoder[StringField] = deriveEncoder[StringField]

  implicit val dateDecoder: Decoder[DateField] = deriveDecoder[DateField]
  implicit val dateEncoder: Encoder[DateField] = deriveEncoder[DateField]

  implicit val fieldDecoder: Decoder[Field] = deriveDecoder[Field]
  implicit val fieldEncoder: Encoder[Field] = deriveEncoder[Field]

  implicit val fooDecoder: Decoder[FieldMapping] = deriveDecoder[FieldMapping]
  implicit val fooEncoder: Encoder[FieldMapping] = deriveEncoder[FieldMapping]
}