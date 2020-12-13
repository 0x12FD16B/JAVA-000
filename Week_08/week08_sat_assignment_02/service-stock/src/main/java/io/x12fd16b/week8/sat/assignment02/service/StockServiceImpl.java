package io.x12fd16b.week8.sat.assignment02.service;

import io.x12fd16b.week8.sat.assignment02.common.api.StockService;
import io.x12fd16b.week8.sat.assignment02.common.dto.LockStockDTO;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.transaction.annotation.Transactional;

/**
 * Stock Service
 *
 * @author David Liu
 */
@Slf4j
public class StockServiceImpl implements StockService {
    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void lockSkuStock(LockStockDTO lockStockDTO) {
        log.info("lock sku stock, skuCode: {}, quantity: {}", lockStockDTO.getSkuCode(), lockStockDTO.getQuantity());
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void confirm(LockStockDTO lockStockDTO) {
        log.info("confirm lock sku stock, skuCode: {}, quantity: {}", lockStockDTO.getSkuCode(), lockStockDTO.getQuantity());
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void cancel(LockStockDTO lockStockDTO) {
        log.info("cancel lock sku stock, skuCode: {}, quantity: {}", lockStockDTO.getSkuCode(), lockStockDTO.getQuantity());
    }
}
