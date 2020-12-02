package io.x12fd16b.week7.thrus.assignment02.dao;

import cn.hutool.core.collection.CollectionUtil;
import io.x12fd16b.week7.thrus.assignment02.Application;
import io.x12fd16b.week7.thrus.assignment02.dao.entity.Order;
import io.x12fd16b.week7.thrus.assignment02.dao.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback
@Slf4j
public class OrderDAOTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 耗时: 165847 ms
    @Test
    public void testInsertWithLoop() {
        log.info("into method testInsertWithLoop");
        StopWatch sw = StopWatch.createStarted();
        long count = 1_000_000;
        long orderIdStart = 1, orderItemIdStart = 1;
        for (long i = 0; i < count; i++) {
            Long orderId = orderIdStart + i;
            String orderCode = "order_code_" + i;
            Long merchantId = 1L;
            Long userId = 1L;
            Integer amount = 1000000;
            Integer cutAmount = 0;
            Long shippingAddressId = 1L;
            int orderStatus = 0;
            jdbcTemplate.update("INSERT INTO `order` (`id`, `code`, `merchant_id`, `user_id`, `amount`, `cut_amount`, `shipping_address_id`, `status`) values" +
                    " (?, ?, ?, ?, ?, ?, ?, ?)", orderId, orderCode, merchantId, userId, amount, cutAmount, shippingAddressId, orderStatus);
            Long orderItemId = orderItemIdStart + i;
            String skuCode = "100001";
            Integer quantity = 1;
            Integer price = 1000000;
            jdbcTemplate.update("INSERT INTO `order_item` (`id`, `order_id`, `sku_code`, `quantity`, `price`, `item_amount`, `cut_amount`) values" +
                    " (?, ?, ?, ?, ?, ?, ?)", orderItemId, orderId, skuCode, quantity, price, amount, cutAmount);
        }
        sw.stop();
        log.info("insert data with loop cost {} ms", sw.getTime());
    }

    // 耗时: 142654 ms batchSize = 500
    @Test
    public void testPrepareStatementAddBatch_with_batchSize500() {
        doTestPrepareStatementAddWithBatchSize(500);
    }

    // 耗时: 135429 ms batchSize = 1000
    @Test
    public void testPrepareStatementAddBatch_with_batchSize1000() {
        doTestPrepareStatementAddWithBatchSize(1000);
    }

    private void doTestPrepareStatementAddWithBatchSize(int batchSize) {
        log.info("into method testPrepareStatementAddBatch");
        StopWatch sw = StopWatch.createStarted();
        TestSuiteDataSet testSuiteDataSet = this.buildBatchInsertTestSuiteDataSet(1000000);
        List<Order> orders = testSuiteDataSet.orders;
        List<OrderItem> orderItems = testSuiteDataSet.orderItems;
        List<List<Order>> orderPieces = CollectionUtil.split(orders, batchSize);
        List<List<OrderItem>> orderItemPieces = CollectionUtil.split(orderItems, batchSize);
        for (int i = 0; i < orderPieces.size(); i++) {
            this.executePrepareStatementAddBatch(orderPieces.get(i), orderItemPieces.get(i), batchSize);
        }

        sw.stop();
        log.info("insert data with prepare statement cost {} ms", sw.getTime());
    }


    // 耗时: 19398 ms batchSize 500
    @Test
    public void testInsertBatch_with_valueSize500() {
        long count = 1_000_000;
        int batchSize = 500;
        doTestInsertBatchWithValueSize(count, batchSize);

    }


    // 耗时: 20446 ms batchSize 1000
    @Test
    public void testInsertBatch_with_valueSize1000() {
        long count = 1_000_000;
        int batchSize = 1000;
        doTestInsertBatchWithValueSize(count, batchSize);
    }

    // 耗时: 22608 ms batchSize 5000
    @Test
    public void testInsertBatch_with_valueSize5000() {
        long count = 1_000_000;
        int batchSize = 5000;
        doTestInsertBatchWithValueSize(count, batchSize);
    }

    private void doTestInsertBatchWithValueSize(long count, int batchSize) {
        log.info("into method testInsertBatch");
        StopWatch sw = StopWatch.createStarted();
        int pieces = (int) count / batchSize;
        TestSuiteDataSet testSuiteDataSet = this.buildBatchInsertTestSuiteDataSet(count);

        String orderInsertSqlSegmentPrefix = "INSERT INTO `order` (`id`, `code`, `merchant_id`, `user_id`, `amount`, `cut_amount`, `shipping_address_id`, `status`) values ";
        String orderInsertSqlSegmentValueTpl = "(%s, '%s', %s, %s, %s, %s, %s, %s)";
        List<String> orderInsertSqlValueSegments = new ArrayList<>(batchSize);

        String orderItemInsertSqlSegmentPrefix = "INSERT INTO `order_item` (`id`, `order_id`, `sku_code`, `quantity`, `price`, `item_amount`, `cut_amount`) values ";
        String orderItemInsertSqlSegmentValueTpl = "(%s, %s, '%s', %s, %s, %s, %s)";
        List<String> orderItemInsertSqlValueSegments = new ArrayList<>(batchSize);

        List<List<Order>> orderPieces = CollectionUtil.split(testSuiteDataSet.orders, batchSize);
        List<List<OrderItem>> orderItemPieces = CollectionUtil.split(testSuiteDataSet.orderItems, batchSize);

        for (int i = 0; i < pieces; i++) {
            for (Order order : orderPieces.get(i)) {
                orderInsertSqlValueSegments.add(String.format(orderInsertSqlSegmentValueTpl, order.getId(), order.getCode(), order.getMerchantId(), order.getUserId(), order.getAmount(),
                        order.getCutAmount(), order.getShippingAddressId(), order.getStatus()));
            }
            jdbcTemplate.execute(orderInsertSqlSegmentPrefix + StringUtils.join(orderInsertSqlValueSegments, ",") + ";");
            orderInsertSqlValueSegments.clear();
            for (OrderItem orderItem : orderItemPieces.get(i)) {
                orderItemInsertSqlValueSegments.add(String.format(orderItemInsertSqlSegmentValueTpl, orderItem.getId(), orderItem.getOrderId(),
                        orderItem.getSkuCode(), orderItem.getQuantity(), orderItem.getPrice(), orderItem.getItemAmount(), orderItem.getCutAmount()));
            }
            jdbcTemplate.execute(orderItemInsertSqlSegmentPrefix + StringUtils.join(orderItemInsertSqlValueSegments, ",") + ";");
            orderItemInsertSqlValueSegments.clear();
        }

        sw.stop();
        log.info("insert data with batch segment cost {} ms", sw.getTime());
    }

    private void executePrepareStatementAddBatch(List<Order> orders, List<OrderItem> orderItems, int size) {
        jdbcTemplate.batchUpdate("INSERT INTO `order` (`id`, `code`, `merchant_id`, `user_id`, `amount`, `cut_amount`, `shipping_address_id`, `status`) values" +
                " (?, ?, ?, ?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Order order = orders.get(i);
                ps.setObject(1, order.getId());
                ps.setObject(2, order.getCode());
                ps.setObject(3, order.getMerchantId());
                ps.setObject(4, order.getUserId());
                ps.setObject(5, order.getAmount());
                ps.setObject(6, order.getCutAmount());
                ps.setObject(7, order.getShippingAddressId());
                ps.setObject(8, order.getStatus());
            }

            @Override
            public int getBatchSize() {
                return size;
            }
        });

        jdbcTemplate.batchUpdate("INSERT INTO `order_item` (`id`, `order_id`, `sku_code`, `quantity`, `price`, `item_amount`, `cut_amount`) values" +
                " (?, ?, ?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                OrderItem orderItem = orderItems.get(i);
                ps.setObject(1, orderItem.getId());
                ps.setObject(2, orderItem.getOrderId());
                ps.setObject(3, orderItem.getSkuCode());
                ps.setObject(4, orderItem.getQuantity());
                ps.setObject(5, orderItem.getPrice());
                ps.setObject(6, orderItem.getItemAmount());
                ps.setObject(7, orderItem.getCutAmount());
            }

            @Override
            public int getBatchSize() {
                return size;
            }
        });

    }

    private TestSuiteDataSet buildBatchInsertTestSuiteDataSet(long count) {
        long orderIdStart = 1, orderItemIdStart = 1;
        List<Order> orders = new ArrayList<>((int) count);
        List<OrderItem> orderItems = new ArrayList<>((int) count);
        for (long i = 0; i < count; i++) {
            Long orderId = orderIdStart + i;
            String orderCode = "order_code_" + i;
            Long merchantId = 1L;
            Long userId = 1L;
            Integer amount = 1000000;
            Integer cutAmount = 0;
            Long shippingAddressId = 1L;
            int orderStatus = 0;
            // 创建订单
            Order order = new Order();
            order.setId(orderId);
            order.setCode(orderCode);
            order.setMerchantId(merchantId);
            order.setUserId(userId);
            order.setAmount(amount);
            order.setCutAmount(cutAmount);
            order.setShippingAddressId(shippingAddressId);
            order.setStatus(orderStatus);
            orders.add(order);
            // 创建订单项
            Long orderItemId = orderItemIdStart + i;
            String skuCode = "100001";
            Integer quantity = 1;
            Integer price = 1000000;
            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemId);
            orderItem.setOrderId(orderId);
            orderItem.setSkuCode(skuCode);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);
            orderItem.setItemAmount(amount);
            orderItem.setCutAmount(cutAmount);
            orderItems.add(orderItem);
        }
        TestSuiteDataSet testSuiteDataSet = new TestSuiteDataSet();
        testSuiteDataSet.orders = orders;
        testSuiteDataSet.orderItems = orderItems;
        return testSuiteDataSet;
    }

    private static class TestSuiteDataSet {

        public List<Order> orders;

        public List<OrderItem> orderItems;
    }

}