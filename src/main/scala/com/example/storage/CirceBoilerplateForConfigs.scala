package com.example.storage

import com.example.model.{CustomDate, CustomFloat, CustomSeqString, CustomString, Field, FieldMapping, GUser}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import io.circe._
import io.circe.generic.semiauto._

object CirceBoilerplateForConfigs {
  implicit val userDecoder: Decoder[GUser] = deriveDecoder[GUser]
  implicit val userEncoder: Encoder[GUser] = deriveEncoder[GUser]

  implicit val d1: Decoder[CustomString] = deriveDecoder[CustomString]
  implicit val e1: Encoder[CustomString] = deriveEncoder[CustomString]

  implicit val d2: Decoder[CustomDate] = deriveDecoder[CustomDate]
  implicit val e2: Encoder[CustomDate] = deriveEncoder[CustomDate]

  implicit val d3: Decoder[CustomFloat] = deriveDecoder[CustomFloat]
  implicit val e3: Encoder[CustomFloat] = deriveEncoder[CustomFloat]

  implicit val d4: Decoder[CustomSeqString] = deriveDecoder[CustomSeqString]
  implicit val e4: Encoder[CustomSeqString] = deriveEncoder[CustomSeqString]

  implicit val fieldDecoder: Decoder[Field[_]] = deriveDecoder[Field[_]]
  implicit val fieldEncoder: Encoder[Field[_]] = deriveEncoder[Field[_]]
  implicit val fooDecoder: Decoder[FieldMapping[_]] = deriveDecoder[FieldMapping[_]]
  implicit val fooEncoder: Encoder[FieldMapping[_]] = deriveEncoder[FieldMapping[_]]
}