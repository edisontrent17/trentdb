package com.migsoftware.trentdb.execution;

import com.migsoftware.trentdb.data.DataChunk;
import com.migsoftware.trentdb.data.LogicalType;
import com.migsoftware.trentdb.data.LogicalTypeId;
import com.migsoftware.trentdb.data.PhysicalDataChunk;
import com.migsoftware.trentdb.data.Value;
import java.util.List;

public class ShowCatalogsPhysicalOperator implements PhysicalOperator {

  List<String> catalogs;


  public ShowCatalogsPhysicalOperator(List<String> catalogs) {
    this.catalogs = catalogs;
  }

  @Override
  public DataChunk getData() {
    PhysicalDataChunk chunk =
        new PhysicalDataChunk(List.of(LogicalType.of(LogicalTypeId.STRING)), List.of("Catalog Name"),
            catalogs.size());
    chunk.setValue(0, 0, new Value() {
      @Override
      public Object data() {
        return "In Memory";
      }

      @Override
      public String string() {
        return "In Memory";
      }
    });
    return chunk;
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
