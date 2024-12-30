package com.migsoftware.trentdb.planner;


import com.migsoftware.trentdb.parser.Node;
import com.migsoftware.trentdb.parser.Query;
import com.migsoftware.trentdb.parser.Statement;

public class LogicalPlanner {

  public LogicalOperator createPlan(Node node) {
    if (node instanceof Statement) {
      Statement statement = (Statement) node;
      switch (statement.getType()) {
        case SELECT -> {
          return new SelectPlanner().createPlan((Query) statement);
        }
        default -> throw new UnsupportedOperationException("Unknown statement type");
      }
    }
    return new LogicalOperator();
  }
}
