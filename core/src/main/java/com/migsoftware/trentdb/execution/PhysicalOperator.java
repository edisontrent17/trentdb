package com.migsoftware.trentdb.execution;

import com.migsoftware.trentdb.data.DataChunk;
import java.util.List;

public interface PhysicalOperator {

  DataChunk getData();

  boolean done();

  OperatorType operatorType();

  List<Type> types();

  PhysicalOperatorState currentState();
}
