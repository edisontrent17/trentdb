package com.migsoftware.trentdb.parser;


public class Query extends Statement {

  private final QueryBody queryBody;

  public Query(QueryBody body) {
    this.queryBody = body;
  }

  public QueryBody getQueryBody() {
    return queryBody;
  }

  @Override
  public StatementType getType() {
    return StatementType.SELECT;
  }
}
