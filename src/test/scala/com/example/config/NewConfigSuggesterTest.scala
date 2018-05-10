package com.example.config

import com.example.connector.jira.JiraField
import com.example.connector.redmine.RedmineField
import com.example.model.{Field, FieldMapping}
import org.junit.runner.RunWith
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class NewConfigSuggesterTest extends FunSpec with ScalaFutures with Matchers {

  val jiraRedmineFieldsNumber = 15

  it("suggests all elements from left connector") {
    val list = NewConfigSuggester.suggestedFieldMappingsForNewConfig(
      RedmineField.getSuggestedCombinations(), JiraField.getSuggestedCombinations())

    list.size shouldBe jiraRedmineFieldsNumber
    list.contains(FieldMapping(RedmineField.assignee, JiraField.assignee, true, "")) shouldBe true
    list.contains(FieldMapping(RedmineField.targetVersion, Field(""), false, "")) shouldBe true
  }

  it("suggests all elements from right connector") {
    val list = NewConfigSuggester.suggestedFieldMappingsForNewConfig(
      JiraField.getSuggestedCombinations(), RedmineField.getSuggestedCombinations())

    list.size shouldBe jiraRedmineFieldsNumber
  }

}
