package com.migsoftware.trentdb.data;

import java.util.List;

/**
 * Represents a chunk of data. Each DataChunk is of the same length
 */
public interface DataChunk {

  List<LogicalType> getTypes();

  int capacity();

  void setValue(int colIdx, int index, Value value);

  void print();
}
