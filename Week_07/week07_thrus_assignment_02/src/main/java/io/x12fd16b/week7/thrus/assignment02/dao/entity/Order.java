package io.x12fd16b.week7.thrus.assignment02.dao.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单 Entity
 *
 * @author David Liu
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 9146004833535159719L;
    /**
     * 订单 ID
     */
    private Long id;
    /**
     * 订单编码
     */
    private String code;
    /**
     * 商家 ID
     */
    private Long merchantId;
    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 订单金额
     */
    private Integer amount;
    /**
     * 减免总金额
     */
    private Integer cutAmount;
    /**
     * 订单配送地址 ID
     */
    private Long shippingAddressId;
    /**
     * 订单状态
     */
    private int status;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
}
