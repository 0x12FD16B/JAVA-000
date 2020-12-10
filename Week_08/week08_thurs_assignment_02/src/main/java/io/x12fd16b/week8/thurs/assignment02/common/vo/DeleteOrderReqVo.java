package io.x12fd16b.week8.thurs.assignment02.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单删除请求参数
 *
 * @author David Liu
 */
@Data
public class DeleteOrderReqVo implements Serializable {
    private static final long serialVersionUID = 3821712223003455160L;
    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 订单 ID
     */
    private Long orderId;
}
