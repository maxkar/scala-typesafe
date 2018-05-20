package com.example.model

import java.util
import java.util.Date

sealed abstract class Field[T](clazz: Class[T], val name: String)

object Assignee extends Field[GUser](classOf[GUser], "Assignee")

object Components extends Field[Seq[String]](classOf[Seq[String]], "Components")

object EstimatedTime extends Field[Float](classOf[Float], "EstimatedTime")

object StartDate extends Field[Date](classOf[Date], "StartDate")

object Id extends Field[java.lang.Long](classOf[java.lang.Long], "Id")

object SourceSystemId extends Field[TaskId](classOf[TaskId], "SourceSystemId")

object Key extends Field[String](classOf[String], "Key")

object ParentKey extends Field[TaskId](classOf[TaskId], "ParentKey")

object Priority extends Field[Int](classOf[Int], "Priority")

object Summary extends Field[String](classOf[String], "Summary")

object Children extends Field[util.List[GTask]](classOf[util.List[GTask]], "Children")

object Relations extends Field[util.List[GRelation]](classOf[util.List[GRelation]], "Relations")

case class CustomString(override val name: String) extends Field[String](classOf[String], name)

case class CustomDate(override val name: String) extends Field[Date](classOf[Date], name)

case class CustomFloat(override val name: String) extends Field[Float](classOf[Float], name)

case class CustomSeqString(override val name: String) extends Field[Seq[String]](classOf[Seq[String]], name)
