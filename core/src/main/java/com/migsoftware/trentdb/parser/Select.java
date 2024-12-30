package com.migsoftware.trentdb.parser;

import java.util.List;

public class Select {

  private final boolean distinct;
  private final List<SelectItem> selectItems;

  public boolean isDistinct() {
    return distinct;
  }

  public List<SelectItem> getSelectItems() {
    return selectItems;
  }

  public Select(boolean distinct, List<SelectItem> selectItems) {
    this.distinct = distinct;
    this.selectItems = selectItems;
  }
}
