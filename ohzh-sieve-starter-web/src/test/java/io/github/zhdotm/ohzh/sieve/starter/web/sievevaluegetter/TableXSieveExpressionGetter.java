package io.github.zhdotm.ohzh.sieve.starter.web.sievevaluegetter;

import io.github.zhdotm.ohzh.sieve.starter.web.getter.ISpringExpressionTextSieveValueGetter;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableXSieveExpressionGetter implements ISpringExpressionTextSieveValueGetter {

    @Override
    public List<String> get() {
        return Lists.newArrayList(" table_x.column_3 = 'x_3xxx' ", "table_x.column_4 = 'x_4xxx' ");
    }

}
