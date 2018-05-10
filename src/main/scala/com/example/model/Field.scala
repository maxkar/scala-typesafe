package com.example.model

import java.util.Date

trait Field {

  def clazz: Class[_]

  def name: String
}

case class StringField(name: String) extends Field {
  override def clazz: Class[_] = classOf[String]
}

case class DateField(name: String) extends Field {
  override def clazz: Class[_] = classOf[Date]
}

//
//case class FloatField(name: String) extends Field[Float]
//
//case class GUserField(name: String) extends Field[GUser]
//
//case class IntegerField(name: String) extends Field[Integer]
//
//case class SeqStringField(name: String) extends Field[Seq[String]]

/*
object Field {
  def apply(fieldName: String): Field[String] = {
    new Field[String](classOf[String], fieldName)
  }

  def seqString(fieldName: String): Field[Seq[String]] = {
    new Field[Seq[String]](classOf[Seq[String]], fieldName)
  }

  def string(fieldName: String): Field[String] = {
    Field(fieldName)
  }

  def date(fieldName: String): Field[Date] = {
    new Field[Date](classOf[Date], fieldName)
  }

  def float(fieldName: String): Field[Float] = {
    new Field[Float](classOf[Float], fieldName)
  }

  def user(fieldName: String): Field[GUser] = {
    new Field[GUser](classOf[GUser], fieldName)
  }

  def integer(fieldName: String): Field[Integer] = {
    new Field[Integer](classOf[Integer], fieldName)
  }
}
*/
