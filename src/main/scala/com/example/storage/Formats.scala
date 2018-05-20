package com.example.storage

import ru.maxkar.json.Json
import ru.maxkar.json.JsonValue
import ru.maxkar.json.JsonString
import ru.maxkar.json.JsonObject
import ru.maxkar.json.JsonUndefined
import ru.maxkar.json.JsonException
import ru.maxkar.json.JsonNull
import ru.maxkar.json.implicits._

import com.example.model._

/**
 * Implementation of serialization and de-serialization formats.
 */
object Formats {
  /** Converts sync config into json. */
  def config2Json(syncConfig: UISyncConfig) : JsonValue =
    Json.make(
      "mappings" → syncConfig.mappings.map(mapping2Json)
    )

  /** Parses json into sync config. */
  def json2Config(v : JsonValue) : UISyncConfig =
    UISyncConfig(
      mappings = v.mappings.as[Seq[JsonValue]].map(json2Mapping)
    )


  /** Converts a field mapping into json. */
  def mapping2Json(fm : FieldMapping[_]) : JsonValue =
    Json.make(
      "finc1" → fm.fieldInConnector1.map(field2Json).getOrElse(JsonUndefined),
      "finc2" → fm.fieldInConnector2.map(field2Json).getOrElse(JsonUndefined),
      "selected" → fm.selected,
      "defaultValue" → default2Json(fm.defaultValue)
    )


  /** Converts a json into field mapping. */
  def json2Mapping(v : JsonValue) : FieldMapping[_] =
    FieldMapping[Any](
      v.finc1.as[Option[JsonObject]].map(json2Field).asInstanceOf[Option[Field[Any]]],
      v.finc2.as[Option[JsonObject]].map(json2Field).asInstanceOf[Option[Field[Any]]],
      v.selected,
      json2Default(v.defaultValue)
    )


  /** Converts default value into json. */
  private def default2Json(v : Any) : JsonValue =
    v match {
      case null ⇒ JsonNull
      case x : String ⇒ x
      case x : GUser ⇒
        Json.make(
          "tt" → "guser",
          "id" → Option(x.id).map(_.intValue),
          "login" → x.loginName,
          "password" → x.displayName
        )
      case x : Seq[_] ⇒
        Json.make(
          "tt" → "seq",
          "v" → x.map(default2Json)
        )
      case other ⇒ throw new JsonException(s"Could not write ${other.getClass}")
    }


  /** Converts json into a default value. */
  private def json2Default(v : JsonValue) : Any =
    v match {
      case JsonNull ⇒ null
      case JsonString(s) ⇒ s
      case JsonObject(_) ⇒
        v.tt.as[String] match {
          case "guser" ⇒
            GUser(
              v.id.as[Option[Int]].map(Integer.valueOf).getOrElse(null),
              v.login,
              v.password
            )
          case "seq" ⇒
            v.v.as[Seq[JsonValue]].map(json2Default)
          case other ⇒ throw new JsonException(s"Don't know how to parse ${other}")
        }
      case other ⇒ throw new JsonException(s"Could not parse ${Json.typeName(other)}")
    }


  /** Converts a field into json definition. */
  def field2Json(field : Field[_]) : JsonValue =
    field match {
      case Assignee ⇒ co("assignee")
      case Components ⇒ co("components")
      case EstimatedTime ⇒ co("estimatedTime")
      case StartDate ⇒ co("startDate")
      case Id ⇒ co("id")
      case SourceSystemId ⇒ co("sourceSystemId")
      case Key ⇒ co("key")
      case ParentKey ⇒ co("parentKey")
      case Priority ⇒ co("priority")
      case Summary ⇒ co("summary")
      case Children ⇒ co("children")
      case Relations ⇒ co("relations")
      case CustomString(n) ⇒ cc("customString", "name" → n)
      case CustomDate(n) ⇒ cc("customDate", "name" → n)
      case CustomFloat(n) ⇒ cc("customFloat", "name" → n)
      case CustomSeqString(n) ⇒ cc("customSeqString", "name" → n)
    }


  /** Converts json into field. */
  def json2Field(v : JsonValue) : Field[_] =
    v("type").as[String] match {
      case "assignee" ⇒ Assignee
      case "components" ⇒ Components
      case "estimatedTime" ⇒ EstimatedTime
      case "startDate" ⇒ StartDate
      case "id" ⇒ Id
      case "sourceSystemId" ⇒ SourceSystemId
      case "key" ⇒ Key
      case "parentKey" ⇒ ParentKey
      case "priority" ⇒ Priority
      case "summary" ⇒ Summary
      case "children" ⇒ Children
      case "relations" ⇒ Relations
      case "customString" ⇒ CustomString(v.name)
      case "customDate" ⇒ CustomDate(v.name)
      case "customFloat" ⇒ CustomFloat(v.name)
      case "CustomSeqString" ⇒ CustomSeqString(v.name)
      case other ⇒ throw new JsonException(s"Unsupported field type ${other}")
    }


  /**
   * A constructor for "Case objects"
   */
  private def co(objectType : String) : JsonValue =
    Json.make("type" → objectType)


  private def cc(objectType : String, fields : (String, JsonValue)*) : JsonValue =
    Json.make(
      fields : _*
    ) ++ Json.make(
      "type" → objectType
    )
}
