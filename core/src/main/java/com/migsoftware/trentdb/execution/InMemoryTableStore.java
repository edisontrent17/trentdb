package com.migsoftware.trentdb.execution;

import java.util.List;

public class InMemoryTableStore implements Storage {

    @Override
    public List<Table> getTables() {
        return List.of();
    }
}
