package io.x12fd16b.week7.sat.assignment02.support.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 动态数据源枚举
 *
 * @author David Liu
 */
@Getter
@AllArgsConstructor
public enum DynamicDataSourceEnum {

    MASTER("master"),
    SLAVE("slave");

    private final String dsName;
}
