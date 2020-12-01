package io.x12fd16b.week7.thrus.assignment02.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单项商品快照信息
 *
 * @author David Liu
 */
@Data
public class OrderItemSkuSnapshot implements Serializable {
    private static final long serialVersionUID = 5580144651026955366L;
    /**
     * ID
     */
    private Long id;
    /**
     * 订单明细项 ID
     */
    private Long orderItemId;
    /**
     * 商品编码
     */
    private String skuCode;
    /**
     * 商品快照详情
     */
    private String snapshotDetail;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
}
