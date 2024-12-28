package com.migsoftware.trentdb.parser;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.misc.NotNull;

import com.migsoftware.trentdb.parser.SqlBaseParser.ExpressionContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.QueryContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.QueryNoWithContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.QuerySpecificationContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.SelectSingleContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.SingleStatementContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.StringLiteralContext;

public class AstBuilder extends SqlBaseBaseVisitor<Node> {

    @Override
    public Node visitQueryNoWith(QueryNoWithContext ctx) {
        return super.visitQueryNoWith(ctx);
    }

    @Override
    public Node visitQuerySpecification(QuerySpecificationContext ctx) {
        Optional<Relation> from = Optional.empty();
        List<Relation> relations = new ArrayList<>();/* = visit(ctx.relation())*/
        checkJoin(relations);
        if (!relations.isEmpty()) {
            from = Optional.of(relations.get(0));
        }
        return new QuerySpecification(

        );
    }

    private void checkJoin(List<Relation> relations) {
        if (relations.size() > 1)
            throw new UnsupportedOperationException("Multi relation queries are not supported");
    }

    @Override
    public Node visitQuery(QueryContext ctx) {
        QueryNoWith queryNoWith = (QueryNoWith) visit(ctx.queryNoWith());
        return super.visitQuery(ctx);
    }

    @Override
    public Node visitSingleStatement(SingleStatementContext ctx) {
        return visit(ctx.statement());
    }

    @Override
    public Node visitSingleExpression(@NotNull SqlBaseParser.SingleExpressionContext context) {
        return visit(context.expression());
    }

    @Override
    public Node visitExpression(ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public Node visitSelectSingle(SelectSingleContext ctx) {
        return new SelectColumn((Expression) visit(ctx.expression()));
    }

    @Override
    public Node visitStringLiteral(StringLiteralContext ctx) {
        System.out.println("Visiting literal");
        return new StringLiteral(ctx.STRING().getText());
    }
}
