package com.migsoftware.trentdb.data;

public class LogicalType {


  private final LogicalTypeId type;

  public LogicalType(LogicalTypeId type) {
    this.type = type;
  }

  public LogicalTypeId typeId() {
    return this.type;
  }
}
