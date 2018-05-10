package com.example.connector.redmine

import java.util

import com.example.model._

import scala.collection.JavaConverters._

object RedmineField {
  // Redmine field names
  val id = Field("Id")
  val summary = Field.string("Summary")
  val category = Field.string("Category")
  val description = Field.string("Description")
  val taskType = Field.string("Tracker type")
  val estimatedTime = Field.float("Estimated time")

  /**
    * %% complete (e.g. "30%"). int value
    */
  val doneRatio = Field.float("Done ratio")
  val author = Field.user("Author")
  val assignee = Field.user("Assignee")
  val dueDate = Field.date("Due Date")
  val startDate = Field.date("Start Date")
  val createdOn = Field.date("Created On")
  val updatedOn = Field.date("Updated On")
  val taskStatus = Field.string("Task status")
  val targetVersion = Field.string("Target Version")
  val priority = Field.integer("Priority")

  def fields: List[Field[Any]] = List(author,
    category,
    summary,
    description,
    taskType,
    estimatedTime,
    doneRatio,
    assignee,
    dueDate,
    startDate,
    createdOn,
    updatedOn,
    taskStatus,
    targetVersion,
    priority)

  def fieldsAsJava(): util.List[Field[Any]] = fields.asJava

  // id field is not in the suggested list because typically
  // id from one system cannot be directly used as id in another system.
  def suggestedStandardFields: Map[Field[Any], StandardField[Any]] = Map(
    author -> Reporter,
    category -> Components,
    summary -> Summary, description -> Description, taskType -> TaskType,
    estimatedTime -> EstimatedTime,
    doneRatio -> DoneRatio,
    assignee -> Assignee,
    dueDate -> DueDate,
    startDate -> StartDate,
    createdOn -> CreatedOn,
    updatedOn -> UpdatedOn,
    taskStatus -> TaskStatus,
    targetVersion -> TargetVersion,
    priority -> Priority)

  def getSuggestedCombinations(): Map[Field[Any], StandardField[Any]] = {
    suggestedStandardFields
  }
}
