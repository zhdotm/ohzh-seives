package io.github.zhdotm.ohzh.sieve.starter.web.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName table_y
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableY implements Serializable {

    private Long id;

    private String column1;

    private String column2;

    private String column3;

    private String column4;

    private String column5;
}
