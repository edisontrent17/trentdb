package com.migsoftware.trentdb.data;

import java.util.List;

public class PhysicalDataChunk implements DataChunk {

  private final List<LogicalType> logicalTypes;
  private final int capacity;
  private final Value[][] data;
  private final List<String> colNames;

  public PhysicalDataChunk(List<LogicalType> types, List<String> colNames, int capacity) {
    this.logicalTypes = types;
    this.capacity = capacity;
    this.data = new Value[capacity][types.size()];
    this.colNames = colNames;
  }

  @Override
  public List<LogicalType> getTypes() {
    return logicalTypes;
  }

  @Override
  public int capacity() {
    return this.capacity;
  }

  @Override
  public void setValue(int colIdx, int index, Value value) {
    data[index][colIdx] = value;
  }

  @Override
  public void print() {
    StringBuilder buffer = new StringBuilder();
    for (String colName : colNames) {
      buffer.append(colName).append(",");
    }
    buffer.append("\n");
    for (Value[] row : data) {
      for (Value value : row) {
        buffer.append(value.string()).append(",");
      }
      buffer.append("\n");
    }
    System.out.println(buffer);
  }
}
