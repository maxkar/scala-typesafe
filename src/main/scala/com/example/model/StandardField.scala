package com.example.model

import java.util.Date

sealed trait StandardField[+T]

/**
  * A Key to uniquely identify a Task in the native system (Redmine, JIRA, etc).
  *
  * In JIRA that would be something like "MYPROJ-1", in Redmine - a numeric value like 123.
  */
//case object Key extends StandardField

case object Assignee extends StandardField[GUser]

/**
  * Save this as Seq[String]
  */
case object Components extends StandardField[Seq[String]]

case object Reporter extends StandardField[GUser]

case object ClosedOn extends StandardField[Date]

case object CreatedOn extends StandardField[Date]

case object EstimatedTime extends StandardField[Float]

case object Description extends StandardField[String]

case object DoneRatio extends StandardField[Float]

case object DueDate extends StandardField[Date]

//case object Id extends StandardField

case object Priority extends StandardField[Integer]

case object StartDate extends StandardField[Date]

case object Summary extends StandardField[String]

case object TaskType extends StandardField[String]

case object TaskStatus extends StandardField[String]

case object TargetVersion extends StandardField[String]

case object UpdatedOn extends StandardField[Date]
