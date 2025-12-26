package com.migsoftware.trentdb.planner;


public interface LogicalOperator {

  void addChild(LogicalOperator child);

  LogicalOperatorType getType();
}
