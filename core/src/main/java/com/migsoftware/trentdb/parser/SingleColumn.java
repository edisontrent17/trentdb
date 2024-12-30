package com.migsoftware.trentdb.parser;

import java.util.Optional;

public class SingleColumn extends SelectItem {

  private final Expression expression;
  private final Optional<String> alias;

  public SingleColumn(Expression expression, Optional<String> alias) {
    this.expression = expression;
    this.alias = alias;
  }
}
