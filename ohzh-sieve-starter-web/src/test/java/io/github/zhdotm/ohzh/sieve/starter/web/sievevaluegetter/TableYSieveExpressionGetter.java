package io.github.zhdotm.ohzh.sieve.starter.web.sievevaluegetter;

import io.github.zhdotm.ohzh.sieve.starter.web.getter.ISpringExpressionTextSieveValueGetter;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableYSieveExpressionGetter implements ISpringExpressionTextSieveValueGetter {

    @Override
    public List<String> get() {
        return Lists.newArrayList(" table_y.column_3 = 'y_3yyy' ", "table_y.column_4 = 'y_4yyy' ");
    }

}
