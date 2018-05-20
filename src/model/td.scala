package com.example.model

import ru.maxkar.json.Json
import ru.maxkar.json.JsonValue
import ru.maxkar.json.JsonUndefined
import ru.maxkar.json.JsonNull
import ru.maxkar.json.JsonException
import ru.maxkar.json.implicits._

package object td {

  /**
   * A TA-specific descriptor of the type `T`
   */
  abstract sealed class TypeDescriptor[T] {
    /** Serializes a value to json. */
    def toJson(v : T) : JsonValue

    /** Reads a value from json. */
    def fromJson(v : JsonValue) : T

    /** Parses a default value. */
    def parseDefault(s : String) : T =
      throw new UnsupportedOperationException(
        "Field of type ${productPrefix} could not have default values"
      )

    /** Type tag, used for error messages. */
    protected def productPrefix : String
  }


  implicit case object StringTypeDtor extends TypeDescriptor[String] {
    override def toJson(v : String) : JsonValue = v
    override def fromJson(v : JsonValue) : String = v
    override def parseDefault(s : String) : String = s
  }


  implicit case object FloatTypeDtor extends TypeDescriptor[Float] {
    override def toJson(v : Float) : JsonValue = v.toDouble
    override def fromJson(v : JsonValue) : Float = v.as[Double].toFloat
    override def parseDefault(s : String) : Float = s.toFloat
  }


  implicit case object LongTypeDtor extends TypeDescriptor[Long] {
    override def toJson(v : Long) : JsonValue = v
    override def fromJson(v : JsonValue) : Long = v
    override def parseDefault(s : String) : Long = s.toLong
  }


  implicit case object IntTypeDtor extends TypeDescriptor[Int] {
    override def toJson(v : Int) : JsonValue = v
    override def fromJson(v : JsonValue) : Int = v
    override def parseDefault(s : String) : Int = s.toInt
  }


  implicit case object DateTypeDtor extends TypeDescriptor[java.util.Date] {
    override def toJson(v : java.util.Date) : JsonValue = v.toString
    override def fromJson(v : JsonValue) : java.util.Date = parseDefault(v)
    override def parseDefault(s : String) : java.util.Date =
      new java.util.Date(s)
  }


  final case class SeqDtor[T](peer : TypeDescriptor[T]) extends TypeDescriptor[Seq[T]] {
    override def toJson(v : Seq[T]) : JsonValue = v.map(peer.toJson)
    override def fromJson(v : JsonValue) : Seq[T] =
      v.as[Seq[JsonValue]].map(peer.fromJson)
    override def parseDefault(s : String) : Seq[T] =
      s.split(",").map(peer.parseDefault)
  }


  final case class OptDtor[T](peer : TypeDescriptor[T]) extends TypeDescriptor[Option[T]] {
    override def toJson(v : Option[T]) : JsonValue =
      v.map(peer.toJson).getOrElse(JsonUndefined)
    override def fromJson(v : JsonValue) : Option[T] =
      if (v == JsonUndefined) None else Some(peer.fromJson(v))
    override def parseDefault(s : String) : Option[T] =
      if (s.trim.isEmpty) None else Some(peer.parseDefault(s))
  }


  implicit case object GUserTypeDtor extends TypeDescriptor[GUser] {
    override def toJson(v : GUser) : JsonValue =
      if (v == null) null
      else Json.make(
        "id" → Option(v.id).map(_.intValue),
        "login" → v.loginName,
        "password" → v.displayName
      )

    override def fromJson(v : JsonValue) : GUser =
      if (v == JsonNull) null
      else GUser(
        v.id.as[Option[Int]].map(Integer.valueOf).getOrElse(null),
        v.login,
        v.password
      )
  }


  implicit case object TaskIdTypeDtor extends TypeDescriptor[TaskId] {
    override def toJson(v : TaskId) : JsonValue =
      if (v == null) JsonNull
      else Json.make(
        "id" → v.id, "key" → v.key
      )

    override def fromJson(v : JsonValue) : TaskId =
      if (v == JsonNull) null
      else TaskId(v.id, v.key)
  }


  implicit val stringSeqDtor = SeqDtor(StringTypeDtor)
  implicit val optLongDtor = OptDtor(LongTypeDtor)


  object TypeDescriptor {
    /** Converts descriptor into a json value. */
    def writeDescriptor(d : TypeDescriptor[_]) : JsonValue =
      Json.make("v" → 1, "data" → writeDescriptorV1(d))

    /** Versioned writer of descriptor. */
    private def writeDescriptorV1(d : TypeDescriptor[_]) : JsonValue =
      d match {
        case GUserTypeDtor ⇒ Json.make("tid" → "guser")
        case StringTypeDtor ⇒ Json.make("tid" → "string")
        case FloatTypeDtor ⇒ Json.make("tid" → "float")
        case LongTypeDtor ⇒ Json.make("tid" → "long")
        case IntTypeDtor ⇒ Json.make("tid" → "int")
        case DateTypeDtor ⇒ Json.make("tid" → "date")
        case TaskIdTypeDtor ⇒ Json.make("tid" → "taskid")
        case SeqDtor(p) ⇒ Json.make("tid" → "seq", "of" → writeDescriptorV1(p))
        case OptDtor(p) ⇒ Json.make("tid" → "opt", "of" → writeDescriptorV1(p))
      }


    /** Reads a descriptor from json. */
    def readDescriptor(j : JsonValue) : TypeDescriptor[_] =
      j.v.as[Int] match {
        case 1 ⇒ readDescriptorV1(j.data)
        case other ⇒ throw new JsonException(s"Bad Type Descriptor Version ${other}")
      }


    /** Versioned descriptor reader. */
    private def readDescriptorV1(v : JsonValue) : TypeDescriptor[_] =
      v.tid.as[String] match {
        case "guser" ⇒ GUserTypeDtor
        case "string" ⇒ StringTypeDtor
        case "float" ⇒ FloatTypeDtor
        case "long" ⇒ LongTypeDtor
        case "int" ⇒ IntTypeDtor
        case "date" ⇒ DateTypeDtor
        case "taskid" ⇒ TaskIdTypeDtor
        case "seq" ⇒ SeqDtor(readDescriptorV1(v.of))
        case "opt" ⇒ OptDtor(readDescriptorV1(v.of))
        case other ⇒ throw new JsonException(s"Unrecognized type ${other}")
      }
  }

}
