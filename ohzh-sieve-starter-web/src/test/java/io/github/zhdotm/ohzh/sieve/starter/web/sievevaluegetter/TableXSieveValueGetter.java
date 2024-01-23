package io.github.zhdotm.ohzh.sieve.starter.web.sievevaluegetter;

import io.github.zhdotm.ohzh.sieve.starter.web.getter.ISpringSieveValueGetter;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableXSieveValueGetter implements ISpringSieveValueGetter {

    @Override
    public List<String> get() {
        return Lists.newArrayList("x_001", "x_002", "x_003");
    }

}
