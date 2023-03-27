package com.example.springhttp.toDo.model;

import com.example.springhttp.common.CrudResource;

public class ToDo implements CrudResource<Integer> {

  private Integer id;
  private String task;
  private ToDoStatus status;
  private String deadline;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer integer) {
    this.id = integer;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public ToDoStatus getStatus() {
    return status;
  }

  public void setStatus(ToDoStatus status) {
    this.status = status;
  }

  public String getDeadline() {
    return deadline;
  }

  public void setDeadline(String deadline) {
    this.deadline = deadline;
  }
}
