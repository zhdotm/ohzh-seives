package io.github.zhdotm.ohzh.sieve.starter.web.holder;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import net.sf.jsqlparser.expression.Expression;

import java.util.List;
import java.util.Map;

/**
 * @author zhihao.mao
 */

public class SieveConditionHolder {

    private static final ThreadLocal<Map<String, List<Expression>>> CONDITION_MAP_THREAD_LOCAL = ThreadLocal.withInitial(MapUtil::newHashMap);

    public static Map<String, List<Expression>> getConditionMap() {

        return CONDITION_MAP_THREAD_LOCAL.get();
    }

    public static List<Expression> getExpression(String tableName) {

        return getConditionMap().get(tableName);
    }

    public static List<String> getTableNames() {

        return ListUtil.list(Boolean.FALSE, getConditionMap().keySet());
    }

    public static void addCondition(String tableName, Expression expression) {
        List<Expression> expressions = getConditionMap().getOrDefault(tableName, ListUtil.list(Boolean.FALSE));
        expressions.add(expression);

        getConditionMap().put(tableName, expressions);
    }

    public static void clear() {

        CONDITION_MAP_THREAD_LOCAL.remove();
    }
}
