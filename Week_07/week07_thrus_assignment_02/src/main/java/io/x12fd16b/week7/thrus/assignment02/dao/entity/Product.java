package io.x12fd16b.week7.thrus.assignment02.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 *
 * @author David Liu
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -4497201081945943032L;
    /**
     * 商品 ID
     */
    private Long id;
    /**
     * 商家 ID
     */
    private Long merchantId;
    /**
     * 商品编码
     */
    private String code;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品类目编码
     */
    private String categoryCode;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 商品更新时间
     */
    private Date gmtModified;
}
