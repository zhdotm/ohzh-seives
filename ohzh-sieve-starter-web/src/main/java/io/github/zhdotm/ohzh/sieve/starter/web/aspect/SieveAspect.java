package io.github.zhdotm.ohzh.sieve.starter.web.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import io.github.zhdotm.ohzh.sieve.core.annotation.ExpressionSieve;
import io.github.zhdotm.ohzh.sieve.core.annotation.Sieves;
import io.github.zhdotm.ohzh.sieve.core.annotation.ValueSieve;
import io.github.zhdotm.ohzh.sieve.core.getter.expression.impl.EqualToExpressionGetterImpl;
import io.github.zhdotm.ohzh.sieve.core.getter.expression.impl.ExpressionTextGetterImpl;
import io.github.zhdotm.ohzh.sieve.core.getter.expression.impl.InExpressionGetterImpl;
import io.github.zhdotm.ohzh.sieve.core.getter.value.IExpressionTextSieveValueGetter;
import io.github.zhdotm.ohzh.sieve.core.getter.value.ISieveValueGetter;
import io.github.zhdotm.ohzh.sieve.starter.web.holder.SieveConditionHolder;
import io.github.zhdotm.ohzh.sieve.starter.web.manager.ISpringSieveValueGetterManager;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * @author zhihao.mao
 */

@AllArgsConstructor
@Aspect
public class SieveAspect {

    private final ISpringSieveValueGetterManager springValueGetterManager;

    @Pointcut("@annotation(io.github.zhdotm.ohzh.sieve.core.annotation.Sieves)")
    public void sievesPointcut() {
    }

    @Pointcut("@annotation(io.github.zhdotm.ohzh.sieve.core.annotation.ValueSieve)")
    public void valueSievePointcut() {
    }

    @Pointcut("@annotation(io.github.zhdotm.ohzh.sieve.core.annotation.ExpressionSieve)")
    public void expressionSievePointcut() {
    }

    @SneakyThrows
    @Around("valueSievePointcut() && @annotation(sieve)")
    public Object valueSieveAround(ProceedingJoinPoint pjp, ValueSieve sieve) {

        addCondition(sieve);
        Object proceed = pjp.proceed(pjp.getArgs());
        clearConditions();

        return proceed;
    }

    @SneakyThrows
    @Around("expressionSievePointcut() && @annotation(sieve)")
    public Object expressionSieveAround(ProceedingJoinPoint pjp, ExpressionSieve sieve) {

        addCondition(sieve);
        Object proceed = pjp.proceed(pjp.getArgs());
        clearConditions();

        return proceed;
    }

    @SneakyThrows
    @Around("sievesPointcut() && @annotation(sieves)")
    public Object sievesAround(ProceedingJoinPoint pjp, Sieves sieves) {

        addConditions(sieves);
        Object proceed = pjp.proceed(pjp.getArgs());
        clearConditions();

        return proceed;
    }

    private void addCondition(ValueSieve sieve) {
        String tableName = sieve.tableName();
        String columnName = sieve.columnName();
        String valueGetterName = sieve.valueGetterName();
        ISieveValueGetter valueGetter = springValueGetterManager.getValueGetter(valueGetterName);
        List<String> values = valueGetter.get();
        Expression expression;
        if (CollectionUtil.isEmpty(values)) {
            expression = new ExpressionTextGetterImpl(" 1 != 1 ")
                    .getExpression();
        } else if (values.size() == 1) {
            expression = new EqualToExpressionGetterImpl(tableName, columnName, values.get(0))
                    .getExpression();
        } else {
            expression = new InExpressionGetterImpl(tableName, columnName, values)
                    .getExpression();
        }

        SieveConditionHolder.addCondition(tableName, expression);
    }

    private void addCondition(ExpressionSieve sieve) {
        String tableName = sieve.tableName();
        String valueGetterName = sieve.valueGetterName();
        ISieveValueGetter valueGetter = springValueGetterManager.getValueGetter(valueGetterName);
        if (!(valueGetter instanceof IExpressionTextSieveValueGetter)) {

            return;
        }
        IExpressionTextSieveValueGetter expressionTextValueGetter = (IExpressionTextSieveValueGetter) valueGetter;
        Expression expression = expressionTextValueGetter.getExpression();

        SieveConditionHolder.addCondition(tableName, expression);
    }

    private void addConditions(Sieves sieves) {
        ValueSieve[] valueSieves = sieves.valueSieves();
        if (ArrayUtil.isNotEmpty(valueSieves)) {
            for (ValueSieve valueSieve : valueSieves) {
                addCondition(valueSieve);
            }
        }
        ExpressionSieve[] expressionSieves = sieves.expressionSieves();
        if (ArrayUtil.isNotEmpty(expressionSieves)) {
            for (ExpressionSieve expressionSieve : expressionSieves) {
                addCondition(expressionSieve);
            }
        }
    }

    private void clearConditions() {
        SieveConditionHolder.clear();
    }

}
