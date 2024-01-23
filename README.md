# 声明式数据筛选器

代码由https://github.com/zhdotm/ohzh-project/tree/main/ohzh-component/ohzh-sieve迁移过来

声明式数据筛选器目前支持根据注解动态改写SQL（简单查询、子查询、连表查询）

## 基本使用

使用案例源码：https://github.com/zhdotm/ohzh-seives/tree/main/ohzh-sieve-starter-web/src/test

### 1、引入依赖

```yml
<dependency>
    <groupId>io.github.zhdotm</groupId>
    <artifactId>ohzh-sieve-mybatis</artifactId>
    <version>${Last.Version}</version?>
</dependency>
```

### 2、启用注解@EnableSpringSieve

```java
@EnableSpringSieve
@SpringBootApplication
@MapperScan(basePackages = "io.github.zhdotm.ohzh.sieve.starter.web.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
```

### 3、定义筛选值/筛选表达式获取器

```java
@Component
public class TableXSieveExpressionGetter implements ISpringExpressionTextSieveValueGetter {

    @Override
    public List<String> get() {
        return Lists.newArrayList(" table_x.column_3 = 'x_3xxx' ", "table_x.column_4 = 'x_4xxx' ");
    }

}
```

```java
@Component
public class TableXSieveValueGetter implements ISpringSieveValueGetter {

    @Override
    public List<String> get() {
        return Lists.newArrayList("x_001", "x_002", "x_003");
    }

}
```

### 4、利用注解动态改写SQL

```java
@Sieves(
        valueSieves = {
                @ValueSieve(tableName = "table_x", columnName = "column_1", valueGetterName = "tableXSieveValueGetter"),
                @ValueSieve(tableName = "table_y", columnName = "column_1", valueGetterName = "tableYSieveValueGetter"),
        },
        expressionSieves = {
                @ExpressionSieve(tableName = "table_x", valueGetterName = "tableXSieveExpressionGetter"),
                @ExpressionSieve(tableName = "table_y", valueGetterName = "tableYSieveExpressionGetter"),
        }
)
public List<TableX> joinSelect() {
    return tableXYMapper.joinSelect();
}
```

### 5、改写效果

 [数据筛子]SQL增强前: select table_x.* from table_x join table_y on table_x.column_3 = table_y.column_3  where table_x.column_4 = 'x4_xxx'
[数据筛子]SQL增强后: SELECT table_x.* FROM table_x JOIN table_y ON table_x.column_3 = table_y.column_3 AND table_y.column_1 IN ('y_001', 'y_002', 'y_003') AND table_y.column_3 = 'y_3yyy' AND table_y.column_4 = 'y_4yyy' WHERE table_x.column_4 = 'x4_xxx' AND table_x.column_1 IN ('x_001', 'x_002', 'x_003') AND table_x.column_3 = 'x_3xxx' AND table_x.column_4 = 'x_4xxx'

