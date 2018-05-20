package com.example.model

import com.example.model.td._

/**
 * Field definitions.
 */
package object field {
  /**
   * "Key" of the field. It identifies semantics and particular
   * format of the field.
   *
   * @tparam T type of the field value.
   */
  abstract sealed trait FieldKey[T] {
    /** Descriptor of the field. */
    val dtor : TypeDescriptor[T]
  }


  /**
   * A base class for all the standard fields.
   * Standard field defines both semantics and the only true format.
   */
  abstract class StandardField[T](implicit val td : TypeDescriptor[T])
      extends FieldKey[T] {
    override val dtor : TypeDescriptor[T] = td
  }


  case object Assignee extends StandardField[GUser]
  case object Component extends StandardField[Seq[String]]
  case object EstimatedTime extends StandardField[Float]
  case object StartDate extends StandardField[java.util.Date]
  case object Id extends StandardField[Option[Long]]
  case object SourceSystemId extends StandardField[TaskId]
  case object Key extends StandardField[String]
  case object ParentKey extends StandardField[TaskId]
  case object Priority extends StandardField[Int]
  case object Summary extends StandardField[String]

  /** User-created field key. */
  final case class CustomKey[T](id : String, dtor : TypeDescriptor[T])
      extends FieldKey[T]
}
