package com.migsoftware.trentdb.planner;

public class ShowCatalogsOperator implements LogicalOperator {
  @Override
  public void addChild(LogicalOperator child) {
    //NOOP
  }

  @Override
  public LogicalOperatorType getType() {
    return LogicalOperatorType.SHOW_CATALOGS;
  }
}
