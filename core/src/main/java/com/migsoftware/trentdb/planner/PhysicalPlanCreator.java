package com.migsoftware.trentdb.planner;


import com.migsoftware.trentdb.execution.PhysicalOperator;
import com.migsoftware.trentdb.execution.ShowCatalogsPhysicalOperator;
import java.util.List;

public class PhysicalPlanCreator {

  public PhysicalOperator createPlan(LogicalOperator logicalOperator) {
    switch (logicalOperator.getType()) {
      case SHOW_CATALOGS -> {
        return createShowCatalogsPlan();
      }
      default -> throw new UnsupportedOperationException("Only show catalogs is supported");
    }

  }

  private PhysicalOperator createShowCatalogsPlan() {
    return new ShowCatalogsPhysicalOperator(List.of("InMemory"));
  }
}
