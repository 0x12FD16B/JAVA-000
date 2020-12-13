package io.x12fd16b.week8.sat.assignment02.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品锁库存 DTO
 *
 * @author David Liu
 */
@Data
public class LockStockDTO implements Serializable {
    private static final long serialVersionUID = 8488989271318949962L;
    /**
     * 商品编码
     */
    private String skuCode;
    /**
     * 锁库存数量
     */
    private Integer quantity;
}
