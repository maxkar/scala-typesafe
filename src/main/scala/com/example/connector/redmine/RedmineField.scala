package com.example.connector.redmine

import com.example.model._

object RedmineField {
  val category = Components
  val author = Reporter

  def fields = List(author,
    category,
    Summary,
    Description,
    TaskType,
    EstimatedTime,
    DoneRatio,
    Assignee,
    DueDate,
    StartDate,
    CreatedOn,
    UpdatedOn,
    TaskStatus,
    TargetVersion,
    Priority)
}
