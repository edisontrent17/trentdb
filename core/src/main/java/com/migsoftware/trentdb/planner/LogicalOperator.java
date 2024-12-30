package com.migsoftware.trentdb.planner;

import org.apache.calcite.plan.RelOptUtil;

public interface LogicalOperator {

  void addChild(LogicalOperator child);

  LogicalOperatorType getType();
}
