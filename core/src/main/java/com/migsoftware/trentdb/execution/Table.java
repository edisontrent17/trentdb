package com.migsoftware.trentdb.execution;

import java.util.List;

public class Table {

    //Base things needed for a table
    private final List<Column> columns;
    private final String tableName;

    //auxiliary things like indexes,constraints etc..,

    public Table(List<Column> columns, String tableName) {
        this.columns = columns;
        this.tableName = tableName;
    }
}
