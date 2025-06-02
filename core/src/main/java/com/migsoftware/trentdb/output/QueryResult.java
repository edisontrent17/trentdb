package com.migsoftware.trentdb.output;

import com.migsoftware.trentdb.data.DataChunk;
import com.migsoftware.trentdb.data.LogicalType;
import java.util.List;

public interface QueryResult {

  DataChunk fetch();

  void print();

  List<LogicalType> logicalTypes();
}
