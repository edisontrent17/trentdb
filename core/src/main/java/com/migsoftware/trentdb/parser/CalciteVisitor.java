package com.migsoftware.trentdb.parser;

import org.apache.calcite.sql.SqlCall;
import org.apache.calcite.sql.SqlDataTypeSpec;
import org.apache.calcite.sql.SqlDynamicParam;
import org.apache.calcite.sql.SqlIdentifier;
import org.apache.calcite.sql.SqlIntervalQualifier;
import org.apache.calcite.sql.SqlLiteral;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlNodeList;
import org.apache.calcite.sql.util.SqlVisitor;

import com.google.common.base.Optional;

public class CalciteVisitor implements SqlVisitor {
    @Override
    public Object visit(SqlLiteral sqlLiteral) {
        return null;
    }

    @Override
    public Object visit(SqlCall sqlCall) {
        return Optional.absent();
    }

    @Override
    public Object visit(SqlNodeList sqlNodeList) {
        return null;
    }

    @Override
    public Object visit(SqlIdentifier sqlIdentifier) {
        return null;
    }

    @Override
    public Object visit(SqlDataTypeSpec sqlDataTypeSpec) {
        return null;
    }

    @Override
    public Object visit(SqlDynamicParam sqlDynamicParam) {
        return null;
    }

    @Override
    public Object visit(SqlIntervalQualifier sqlIntervalQualifier) {
        return null;
    }

    @Override
    public Object visitNode(SqlNode n) {
        return SqlVisitor.super.visitNode(n);
    }
}
