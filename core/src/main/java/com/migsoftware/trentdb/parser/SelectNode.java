package com.migsoftware.trentdb.parser;

public class SelectNode extends QueryNode {

  private final QueryNodeType type;

  public SelectNode() {
    this.type = QueryNodeType.SELECT_NODE;
  }


}
