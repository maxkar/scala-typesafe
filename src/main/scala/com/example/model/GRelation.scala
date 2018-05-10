package com.example.model

sealed trait RelationType

case object Precedes extends RelationType

case class GRelation(taskId: TaskId, relatedTaskId: TaskId, `type`: RelationType)
