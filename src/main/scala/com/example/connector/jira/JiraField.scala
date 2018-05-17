package com.example.connector.jira

import com.example.model._

/**
  * JIRA field names
  */
object JiraField {

  val fields = List(Id, Components, Summary, TaskStatus, Description, TaskType, EstimatedTime, Assignee,
    CreatedOn, DueDate, Priority)
}
