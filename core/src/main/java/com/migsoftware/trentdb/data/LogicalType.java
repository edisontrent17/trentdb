package com.migsoftware.trentdb.data;

public class LogicalType {


  private final LogicalTypeId type;

  public LogicalType(LogicalTypeId type) {
    this.type = type;
  }

  public static LogicalType of(LogicalTypeId typeId) {
    return new LogicalType(typeId);
  }

  public LogicalTypeId typeId() {
    return this.type;
  }
}
