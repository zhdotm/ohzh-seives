package io.github.zhdotm.ohzh.sieve.starter.web;


import cn.hutool.extra.spring.SpringUtil;
import io.github.zhdotm.ohzh.sieve.starter.web.repository.TableXYRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {

    @Test
    public void test1() {
        TableXYRepository tableXYRepository = SpringUtil.getBean(TableXYRepository.class);
        tableXYRepository.simpleSelect();
    }

    @Test
    public void test2() {
        TableXYRepository tableXYRepository = SpringUtil.getBean(TableXYRepository.class);
        tableXYRepository.subSelectSelect();
    }

    @Test
    public void test3() {
        TableXYRepository tableXYRepository = SpringUtil.getBean(TableXYRepository.class);
        tableXYRepository.joinSelect();
    }


}
