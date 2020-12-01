package io.x12fd16b.week7.thrus.assignment02.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单明细项
 *
 * @author David Liu
 */
@Data
public class OrderItem implements Serializable {
    private static final long serialVersionUID = -7132020259097546641L;
    /**
     * ID
     */
    private Long id;
    /**
     * 订单 ID
     */
    private Long orderId;
    /**
     * 商品编码
     */
    private String skuCode;
    /**
     * 购买数量
     */
    private Integer quantity;
    /**
     * 商品结算价格
     */
    private Integer price;
    /**
     * 订单项小计
     */
    private Integer itemAmount;
    /**
     * 订单项减免金额
     */
    private Integer cutAmount;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
}
