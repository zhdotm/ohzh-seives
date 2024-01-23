package io.github.zhdotm.ohzh.sieve.starter.web.repository;

import io.github.zhdotm.ohzh.sieve.core.annotation.ExpressionSieve;
import io.github.zhdotm.ohzh.sieve.core.annotation.Sieves;
import io.github.zhdotm.ohzh.sieve.core.annotation.ValueSieve;
import io.github.zhdotm.ohzh.sieve.starter.web.dataobject.TableX;
import io.github.zhdotm.ohzh.sieve.starter.web.mapper.TableXYMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableXYRepository {

    @Autowired
    private TableXYMapper tableXYMapper;

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
    public List<TableX> simpleSelect() {
        return tableXYMapper.simpleSelect();
    }

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
    public List<TableX> subSelectSelect() {
        return tableXYMapper.subSelectSelect();
    }

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
    
}
