package com.example.storage

import com.example.model.{Field, FieldMapping}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

object CirceBoilerplateForConfigs {
  implicit val fieldDecoder: Decoder[Field[Any]] = deriveDecoder[Field[Any]]
  implicit val fieldEncoder: Encoder[Field[Any]] = deriveEncoder[Field[Any]]
  implicit val fooDecoder: Decoder[FieldMapping] = deriveDecoder[FieldMapping]
  implicit val fooEncoder: Encoder[FieldMapping] = deriveEncoder[FieldMapping]
}
