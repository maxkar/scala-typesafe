package com.example.model

import java.util

/**
  * Generic "Task" object. Internal representation for all "tasks" loaded from various external systems.
  */
final class GTask() {
  // TODO REVIEW Why not EnumMap?
  final private val fields = new util.HashMap[String, Any]

  setValue(GTaskDescriptor.FIELD.CHILDREN, new util.ArrayList[GTask]())
  setValue(GTaskDescriptor.FIELD.RELATIONS, new util.ArrayList[GRelation]())

  /**
    * Copy-constructor creating a shallow clone.
    */
  def this(taskToClone: GTask) {
    this()
    fields.putAll(taskToClone.fields)
  }

  def getValue(field: GTaskDescriptor.FIELD): Any = getValue(field.name)

  def getValue[T](field: Field): T = getValue(field.name).asInstanceOf[T]

  def getValue(field: String): Any = fields.get(field)

  // TODO REVIEW This method could break getters. task.setValue(FIELD.ID, "'xj").
  //    Have you considered more type-safe field keys? Then this method would be
  //    public <T> void setValue(Field<T> field, T value)
  //    I definitely have shown you this technique (attributes in lpg).
  def setValue(field: GTaskDescriptor.FIELD, value: Any): Unit = fields.put(field.name, value)

  def setValue[T](field: Field, value: T): Unit = fields.put(field.name, value)

  /**
    * Unsafe way of setting fields. Used mostly for custom fields where we don't know their types.
    * Use [[setValue(Field, Object]] instead if possible.
    */
  def setValue(field: String, value: Any): Unit = fields.put(field, value)

  def getIdentity: TaskId = {
    var id = getId
    if (id == null) id = 0L
    new TaskId(id, getKey)
  }

  /**
    * Like database ID for Redmine and Jira and Unique ID (row number) for MSP.
    */
  def getId: java.lang.Long = getValue(GTaskDescriptor.FIELD.ID).asInstanceOf[java.lang.Long]

  /**
    * This is database ID for Redmine and Jira and Unique ID (row number) for MSP.
    */
  def setId(id: java.lang.Long): Unit = setValue(GTaskDescriptor.FIELD.ID, id)

  def getParentIdentity: TaskId = getValue(GTaskDescriptor.FIELD.PARENT_KEY).asInstanceOf[TaskId]

  def getSourceSystemId: TaskId = getValue(GTaskDescriptor.FIELD.SOURCE_SYSTEM_ID).asInstanceOf[TaskId]

  def setSourceSystemId(sourceSystemId: TaskId): Unit = setValue(GTaskDescriptor.FIELD.SOURCE_SYSTEM_ID, sourceSystemId)

  /**
    *
    * @return the list of children of an empty list when no children. never NULL.
    */
  def getChildren: util.List[GTask] = getValue(GTaskDescriptor.FIELD.CHILDREN).asInstanceOf[util.List[GTask]]

  def addChildTask(child: GTask): Unit = getChildren.add(child)

  def setChildren(children: util.List[GTask]): Unit = setValue(GTaskDescriptor.FIELD.CHILDREN, children)

  def hasChildren: Boolean = getChildren != null && !getChildren.isEmpty

  override def equals(o: Any): Boolean = {
    if (this == o) return true
    if (o == null || (getClass ne o.getClass)) return false
    val gTask = o.asInstanceOf[GTask]
    if (if (fields != null) !(fields == gTask.fields)
    else gTask.fields != null) return false
    true
  }

  override def hashCode: Int = {
    val result = if (fields != null) fields.hashCode
    else 0
    result
  }

  /**
    * Some systems like Jira can have string-based "key" like "TEST-1"
    * to identify issues. This is NOT a database identifier.
    */
  def getKey: String = getValue(GTaskDescriptor.FIELD.KEY).asInstanceOf[String]

  def setKey(key: String): Unit = setValue(GTaskDescriptor.FIELD.KEY, key)

  def getRelations: util.List[GRelation] = getValue(GTaskDescriptor.FIELD.RELATIONS).asInstanceOf[util.List[GRelation]]

  def setRelations(relations: util.List[GRelation]): Unit = setValue(GTaskDescriptor.FIELD.RELATIONS, relations)

  override def toString: String = fields.toString

  def setParentIdentity(parentIssueKey: TaskId): Unit = setValue(GTaskDescriptor.FIELD.PARENT_KEY, parentIssueKey)

  def getFields: util.Map[String, Any] = fields
}