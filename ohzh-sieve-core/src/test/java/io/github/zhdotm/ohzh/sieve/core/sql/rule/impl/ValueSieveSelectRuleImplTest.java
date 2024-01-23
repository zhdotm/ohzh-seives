package io.github.zhdotm.ohzh.sieve.core.sql.rule.impl;


import cn.hutool.core.lang.Assert;
import io.github.zhdotm.ohzh.sieve.core.sql.rule.ISieveRule;
import lombok.SneakyThrows;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhihao.mao
 */

public class ValueSieveSelectRuleImplTest {

    private ISieveRule rule;

    @SneakyThrows
    @Before
    public void createSelectRule() {
        rule = new SieveSelectRuleImpl("table_1", CCJSqlParserUtil.parseExpression(" table_1.column_1 = 'xxxxx' "));
    }

    @SneakyThrows
    @Test
    public void simpleSql() {
        String sql = " select * from table_1 where table_1.column_2 > 100";
        Statement statement = CCJSqlParserUtil.parse(sql);
        rule.accept(statement);
        Assert.isTrue("SELECT * FROM table_1 WHERE table_1.column_2 > 100 AND table_1.column_1 = 'xxxxx'".equals(statement.toString()));
    }

    @SneakyThrows
    @Test
    public void joinSql() {
        String sql1 = "select * from table_1 join table_2 on table_1.column_2 = table_2.column_2";
        Statement statement1 = CCJSqlParserUtil.parse(sql1);
        rule.accept(statement1);
        Assert.isTrue("SELECT * FROM table_1 JOIN table_2 ON table_1.column_2 = table_2.column_2 WHERE table_1.column_1 = 'xxxxx'".equals(statement1.toString()));

        String sql2 = "select * from table_2 join table_1 on table_1.column_2 = table_2.column_2";
        Statement statement2 = CCJSqlParserUtil.parse(sql2);
        rule.accept(statement2);
        Assert.isTrue("SELECT * FROM table_2 JOIN table_1 ON table_1.column_2 = table_2.column_2 AND table_1.column_1 = 'xxxxx'".equals(statement2.toString()));

        String sql3 = "select * from table_2 join (table_1 join table_3 on table_2.column_2 = table3_.column_2) on table_1.column_1 = table_2.column_1";
        Statement statement3 = CCJSqlParserUtil.parse(sql3);
        rule.accept(statement3);
        Assert.isTrue("SELECT * FROM table_2 JOIN (table_1 JOIN table_3 ON table_2.column_2 = table3_.column_2 AND table_1.column_1 = 'xxxxx') ON table_1.column_1 = table_2.column_1".equals(statement3.toString()));

        String sql4 = "select * from table_2 join (table_3 join table_1 on table_2.column_2 = table3_.column_2) on table_1.column_1 = table_2.column_1";
        Statement statement4 = CCJSqlParserUtil.parse(sql4);
        rule.accept(statement4);
        Assert.isTrue("SELECT * FROM table_2 JOIN (table_3 JOIN table_1 ON table_2.column_2 = table3_.column_2 AND table_1.column_1 = 'xxxxx') ON table_1.column_1 = table_2.column_1".equals(statement4.toString()));
    }

    @SneakyThrows
    @Test
    public void subSelect() {
        String sql1 = "select * from ( select * from table_1)";
        Statement statement1 = CCJSqlParserUtil.parse(sql1);
        rule.accept(statement1);
        Assert.isTrue("SELECT * FROM (SELECT * FROM table_1 WHERE table_1.column_1 = 'xxxxx')".equals(statement1.toString()));

        String sql2 = "select * from ( select * from table_1 join table_2 on table_1.column_2 = table_2.column_2 )";
        Statement statement2 = CCJSqlParserUtil.parse(sql2);
        rule.accept(statement2);
        Assert.isTrue("SELECT * FROM (SELECT * FROM table_1 JOIN table_2 ON table_1.column_2 = table_2.column_2 WHERE table_1.column_1 = 'xxxxx')".equals(statement2.toString()));

        String sql3 = "select * from ( select table_2.* from table_1 join table_2 on table_1.column_2 = table_2.column_2 ) as t join table_1 on table_1.column_5 = t.column_5 where t.column_6 > 100";
        Statement statement3 = CCJSqlParserUtil.parse(sql3);
        rule.accept(statement3);
        Assert.isTrue("SELECT * FROM (SELECT table_2.* FROM table_1 JOIN table_2 ON table_1.column_2 = table_2.column_2 WHERE table_1.column_1 = 'xxxxx') AS t JOIN table_1 ON table_1.column_5 = t.column_5 AND table_1.column_1 = 'xxxxx' WHERE t.column_6 > 100".equals(statement3.toString()));

        String sql4 = "select * from table_1 where table_1.column_2 in ( select table_2.column_2 from table_2 )";
        Statement statement4 = CCJSqlParserUtil.parse(sql4);
        rule.accept(statement4);
        Assert.isTrue("SELECT * FROM table_1 WHERE table_1.column_2 IN (SELECT table_2.column_2 FROM table_2) AND table_1.column_1 = 'xxxxx'".equals(statement4.toString()));

        String sql5 = "select * from table_2 where table_2.column_2 in ( select table_1.column_2 from table_1 )";
        Statement statement5 = CCJSqlParserUtil.parse(sql5);
        rule.accept(statement5);
        Assert.isTrue("SELECT * FROM table_2 WHERE table_2.column_2 IN (SELECT table_1.column_2 FROM table_1 WHERE table_1.column_1 = 'xxxxx')".equals(statement5.toString()));
    }
}

