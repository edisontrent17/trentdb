package com.migsoftware.trentdb.execution;

import com.migsoftware.trentdb.data.DataChunk;
import java.util.List;

public class ShowCatalogsPhysicalOperator implements PhysicalOperator {

  List<String> catalogs;

  @Override
  public DataChunk getData() {
    return null;
  }

  @Override
  public boolean done() {
    return true;
  }

  @Override
  public OperatorType operatorType() {
    return OperatorType.SHOW_CATALOGS;
  }

  @Override
  public List<Type> types() {
    return List.of();
  }

  @Override
  public PhysicalOperatorState currentState() {
    return null;
  }
}
