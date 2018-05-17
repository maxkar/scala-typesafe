package com.example.storage

import com.example.model.{Field, FieldMapping}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

object CirceBoilerplateForConfigs {

  implicit val fieldDecoder: Decoder[Field[_]] = deriveDecoder[Field[_]]
  implicit val fieldEncoder: Encoder[Field[_]] = deriveEncoder[Field[_]]

  implicit val fooDecoder: Decoder[FieldMapping[_]] = deriveDecoder[FieldMapping[_]]
  implicit val fooEncoder: Encoder[FieldMapping[_]] = deriveEncoder[FieldMapping[_]]
}