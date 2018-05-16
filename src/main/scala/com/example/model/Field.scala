package com.example.model

import java.util.Date

sealed abstract class Field[T] {
  def clazz: Class[T]

  def name: String
}

case class StringField(name: String) extends Field[String] {
  override def clazz: Class[String] = classOf[String]
}

case class DateField(name: String) extends Field[Date] {
  override def clazz: Class[Date] = classOf[Date]
}

case class FloatField(name: String) extends Field[Float] {
  override def clazz: Class[Float] = classOf[Float]
}

case class GUserField(name: String) extends Field[GUser] {
  override def clazz: Class[GUser] = classOf[GUser]
}

case class IntegerField(name: String) extends Field[Integer] {
  override def clazz: Class[Integer] = classOf[Integer]
}

case class SeqStringField(name: String) extends Field[Seq[String]] {
  override def clazz: Class[Seq[String]] = classOf[Seq[String]]
}

object Field {
  def apply(fieldName: String): Field[String] = {
    StringField(fieldName)
  }

  def seqString(fieldName: String): Field[Seq[String]] = {
    SeqStringField(fieldName)
  }

  def string(fieldName: String): Field[String] = {
    Field(fieldName)
  }

  def date(fieldName: String): Field[Date] = {
    DateField(fieldName)
  }

  def float(fieldName: String): Field[Float] = {
    FloatField(fieldName)
  }

  def user(fieldName: String): Field[GUser] = {
    GUserField(fieldName)
  }

  def integer(fieldName: String): Field[Integer] = {
    IntegerField(fieldName)
  }
}
