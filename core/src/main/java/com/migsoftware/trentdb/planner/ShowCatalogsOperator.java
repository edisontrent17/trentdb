package com.migsoftware.trentdb.planner;

public class ShowCatalogsOperator implements LogicalOperator {
  @Override
  public void addChild(LogicalOperator child) {

  }

  @Override
  public LogicalOperatorType getType() {
    return LogicalOperatorType.SHOW_CATALOGS;
  }
}
