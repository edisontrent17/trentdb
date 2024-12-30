package com.migsoftware.trentdb.parser;

import java.util.Optional;

public class QuerySpecification extends QueryBody {

  private final Select select;
  private final Optional<Relation> from;


  public Optional<Relation> getFrom() {
    return from;
  }

  public Select getSelect() {
    return select;
  }

  public QuerySpecification(Select select, Optional<Relation> from) {
    this.select = select;
    this.from = from;
  }
}
