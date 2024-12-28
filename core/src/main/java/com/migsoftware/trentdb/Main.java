package com.migsoftware.trentdb;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.util.SqlVisitor;
import org.apache.calcite.sql.validate.SqlConformanceEnum;

import com.migsoftware.trentdb.parser.CalciteVisitor;
import com.migsoftware.trentdb.parser.CaseInsensitiveStream;
import com.migsoftware.trentdb.parser.SqlBaseLexer;
import com.migsoftware.trentdb.parser.SqlBaseParser;

public class Main {
    public static void main(String[] args) throws Exception {
        String sql = "SELECT name from test";
        SqlBaseLexer lexer = new SqlBaseLexer(new CaseInsensitiveStream(new ANTLRInputStream(sql)));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(tokenStream);
        ParserRuleContext tree = parser.singleStatement();
        for (ParseTree t : tree.children) {
            dfs(t);
        }
        System.out.println("tree");

        //        new AstBuilder().visit(parser.singleStatement());
        //        new AstBuilder().visit(tree);

        SqlParser sqlParser = SqlParser.create(
                sql,
                SqlParser.configBuilder().setParserFactory(SqlParserImpl.FACTORY).setQuoting(Quoting.DOUBLE_QUOTE).setUnquotedCasing(Casing.TO_UPPER)
                         .setQuotedCasing(Casing.UNCHANGED).setConformance(SqlConformanceEnum.DEFAULT).build()
                                              );
        SqlNode sqlNode = sqlParser.parseQuery();

        System.out.println(sqlNode.toString());

        sqlNode.accept(new CalciteVisitor());

    }

    private static void dfs(ParseTree parseTree) {
        System.out.println(parseTree.getText());
        if (parseTree.getChildCount() == 0) {
            return;
        }
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            dfs(parseTree.getChild(i));
        }
    }
}