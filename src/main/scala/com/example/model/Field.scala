package com.example.model

import java.util.Date

sealed abstract class Field {
  def clazz: Class[_]

  def name: String
}

case class StringField(name: String) extends Field {
  override def clazz: Class[_] = classOf[String]
}

case class DateField(name: String) extends Field {
  override def clazz: Class[_] = classOf[Date]
}

case class FloatField(name: String) extends Field {
  override def clazz: Class[_] = classOf[Float]
}

case class GUserField(name: String) extends Field {
  override def clazz: Class[_] = classOf[GUser]
}

case class IntegerField(name: String) extends Field {
  override def clazz: Class[_] = classOf[Integer]
}

case class SeqStringField(name: String) extends Field {
  override def clazz: Class[_] = classOf[Seq[String]]
}

object Field {
  def apply(fieldName: String): Field = {
    StringField(fieldName)
  }

  def seqString(fieldName: String): Field = {
    SeqStringField(fieldName)
  }

  def string(fieldName: String): Field = {
    Field(fieldName)
  }

  def date(fieldName: String): Field = {
    DateField(fieldName)
  }

  def float(fieldName: String): Field = {
    FloatField(fieldName)
  }

  def user(fieldName: String): Field = {
    GUserField(fieldName)
  }

  def integer(fieldName: String): Field = {
    IntegerField(fieldName)
  }
}
