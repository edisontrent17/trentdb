package com.migsoftware.trentdb.execution;

import java.util.List;

public interface PhysicalOperator {

    OperatorType operatorType();

    List<Type> types();

    PhysicalOperatorState currentState();
}
