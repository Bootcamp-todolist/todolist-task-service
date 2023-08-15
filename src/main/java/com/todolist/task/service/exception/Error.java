package com.todolist.task.service.exception;

public enum Error {
  TASK_NOT_FOUND("task_not_found"),
  NO_PERMISSION("no_permission");

  private final String value;

  public String getValue() {
    return value;
  }

  Error(String value) {
    this.value = value;
  }
}
