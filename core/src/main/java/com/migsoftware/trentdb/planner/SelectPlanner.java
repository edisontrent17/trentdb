package com.migsoftware.trentdb.planner;

import com.migsoftware.trentdb.parser.Query;
import com.migsoftware.trentdb.parser.QueryBody;
import com.migsoftware.trentdb.parser.QuerySpecification;
import org.apache.calcite.plan.RelOptUtil;


public class SelectPlanner {


  public LogicalOperator createPlan(Query query) {
    QueryBody body = query.getQueryBody();
    LogicalOperator root;
    if (body instanceof QuerySpecification) {
      QuerySpecification specification = (QuerySpecification) body;
      root = planFrom(specification.getFrom());

      root.addChild
    }
    return null;
  }
}
