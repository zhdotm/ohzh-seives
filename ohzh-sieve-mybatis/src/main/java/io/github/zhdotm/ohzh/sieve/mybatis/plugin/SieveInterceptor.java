package io.github.zhdotm.ohzh.sieve.mybatis.plugin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import io.github.zhdotm.ohzh.sieve.core.sql.rule.ISieveRule;
import io.github.zhdotm.ohzh.sieve.core.sql.rule.impl.SieveSelectRuleImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 插件
 *
 * @author zhihao.mao
 */

@Slf4j
@AllArgsConstructor
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})})
public class SieveInterceptor implements Interceptor {

    private final Supplier<Map<String, List<Expression>>> sieveConditionMapSupplier;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (ObjectUtil.isEmpty(sieveConditionMapSupplier)) {

            return invocation.proceed();
        }

        Map<String, List<Expression>> sieveConditionMap = sieveConditionMapSupplier.get();
        if (ObjectUtil.isEmpty(sieveConditionMap) || CollectionUtil.isEmpty(sieveConditionMap)) {

            return invocation.proceed();
        }

        Executor executor = (Executor) invocation.getTarget();
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        BoundSql boundSql;
        if (args.length == 4) {
            boundSql = ms.getBoundSql(parameter);
        } else {
            // 几乎不可能走进这里面,除非使用Executor的代理对象调用query[args[6]]
            boundSql = (BoundSql) args[5];
        }
        String sql = boundSql.getSql();
        log.debug("[数据筛子]SQL增强前: {}", sql);
        Statement statement = CCJSqlParserUtil.parse(sql);
        sieveConditionMap.forEach((tableName, expressions) -> {
            Optional.ofNullable(expressions)
                    .orElse(ListUtil.empty())
                    .forEach(expression -> {
                        ISieveRule sieveRule = new SieveSelectRuleImpl(tableName, expression);
                        sieveRule.accept(statement);
                    });
        });
        String newSql = statement.toString();
        log.debug("[数据筛子]SQL增强后: {}", newSql);

        boundSql = new BoundSql(ms.getConfiguration(), newSql, boundSql.getParameterMappings(), boundSql.getParameterObject());

        CacheKey cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);

        return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
    }

}
