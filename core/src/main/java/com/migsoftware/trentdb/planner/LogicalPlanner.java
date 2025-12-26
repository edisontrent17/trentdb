package com.migsoftware.trentdb.planner;


import com.migsoftware.trentdb.parser.Node;
import com.migsoftware.trentdb.parser.Query;
import com.migsoftware.trentdb.parser.ShowCatalogs;
import com.migsoftware.trentdb.parser.Statement;
import com.migsoftware.trentdb.parser.StatementType;
import java.util.Objects;

public class LogicalPlanner {

  public LogicalOperator createPlan(Node node) {
    if (node instanceof Statement statement) {
      if (Objects.requireNonNull(statement.getType()) == StatementType.SELECT) {
        return new SelectPlanner().createPlan((Query) statement);
      }
      throw new UnsupportedOperationException("Unknown statement type");
    } else if (node instanceof ShowCatalogs) {
      return new ShowCatalogsOperator();
    }
    throw new UnsupportedOperationException("Not Supported");
  }
}
