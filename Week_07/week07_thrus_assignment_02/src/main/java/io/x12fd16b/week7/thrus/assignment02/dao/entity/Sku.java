package io.x12fd16b.week7.thrus.assignment02.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品 SKU
 *
 * @author David Liu
 */
@Data
public class Sku implements Serializable {
    private static final long serialVersionUID = 1031209935845342503L;
    /**
     * ID
     */
    private Long id;
    /**
     * 商品编码
     */
    private String productCode;
    /**
     * SKU 编码
     */
    private String code;
    /**
     * SKU 名称
     */
    private String name;
    /**
     * SKU 类目编码
     */
    private String categoryCode;
    /**
     * SKU 库存
     */
    private Integer stock;
    /**
     * SKU 价格
     */
    private Integer price;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
}
