package io.x12fd16b.week8.sat.assignment02.common.api;

import io.x12fd16b.week8.sat.assignment02.common.dto.LockStockDTO;

/**
 * 库存 Service
 *
 * @author David Liu
 */
public interface StockService {
    
    /**
     * 锁定商品库存
     *
     * @param lockStockDTO 锁定商品库存请求参数
     */
    void lockSkuStock(LockStockDTO lockStockDTO);
    
}
