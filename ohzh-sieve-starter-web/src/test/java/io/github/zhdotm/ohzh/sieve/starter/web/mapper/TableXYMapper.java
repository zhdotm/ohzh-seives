package io.github.zhdotm.ohzh.sieve.starter.web.mapper;


import io.github.zhdotm.ohzh.sieve.starter.web.dataobject.TableX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TableXYMapper {

    @Select(" select * from table_x where table_x.column_1 = 'zhangsan' ")
    List<TableX> simpleSelect();

    @Select(" select * from (select table_x.* from table_x  where table_x.column_2 in (select table_y.column_2 from table_y)) as table_x_part")
    List<TableX> subSelectSelect();

    @Select(" select table_x.* from table_x join table_y on table_x.column_3 = table_y.column_3  where table_x.column_4 = 'x4_xxx' ")
    List<TableX> joinSelect();
}




