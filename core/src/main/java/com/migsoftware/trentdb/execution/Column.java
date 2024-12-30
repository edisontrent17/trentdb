package com.migsoftware.trentdb.execution;

public class Column {

    private final String name;
    private final Type type;

    public Column(String name, Type type) {
        this.name = name;
        this.type = type;
    }
}
