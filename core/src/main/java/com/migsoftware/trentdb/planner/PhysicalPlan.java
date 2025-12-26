package com.migsoftware.trentdb.planner;

import com.migsoftware.trentdb.execution.PhysicalOperator;

public interface PhysicalPlan {

    PhysicalOperator createPlan();
}
