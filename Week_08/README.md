## 作业题目 (周四)

* （必做）设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

> 配置文件

```properties
# shardingsphere
spring.shardingsphere.datasource.names=ds0,ds1
spring.shardingsphere.datasource.common.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.common.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.common.username=root
spring.shardingsphere.datasource.common.password=
spring.shardingsphere.datasource.ds0.jdbc-url=jdbc:mysql://localhost:3308/ds_0?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
spring.shardingsphere.datasource.ds1.jdbc-url=jdbc:mysql://localhost:3308/ds_1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
spring.shardingsphere.rules.sharding.default-database-strategy.standard.sharding-column=user_id
spring.shardingsphere.rules.sharding.default-database-strategy.standard.sharding-algorithm-name=database-inline
spring.shardingsphere.rules.sharding.binding-tables=order,order_item
spring.shardingsphere.rules.sharding.tables.order.actual-data-nodes=ds$->{0..1}.order_$->{0..15}
spring.shardingsphere.rules.sharding.tables.order.table-strategy.standard.sharding-column=id
spring.shardingsphere.rules.sharding.tables.order.table-strategy.standard.sharding-algorithm-name=order-inline
spring.shardingsphere.rules.sharding.tables.order.key-generate-strategy.column=id
spring.shardingsphere.rules.sharding.tables.order.key-generate-strategy.key-generator-name=snowflake
spring.shardingsphere.rules.sharding.tables.order_item.actual-data-nodes=ds$->{0..1}.order_item_$->{0..15}
spring.shardingsphere.rules.sharding.tables.order_item.table-strategy.standard.sharding-column=order_id
spring.shardingsphere.rules.sharding.tables.order_item.table-strategy.standard.sharding-algorithm-name=order-item-inline
spring.shardingsphere.rules.sharding.tables.order_item.key-generate-strategy.column=id
spring.shardingsphere.rules.sharding.tables.order_item.key-generate-strategy.key-generator-name=snowflake
spring.shardingsphere.rules.sharding.sharding-algorithms.database-inline.type=INLINE
spring.shardingsphere.rules.sharding.sharding-algorithms.database-inline.props.algorithm-expression=ds$->{user_id % 2}
spring.shardingsphere.rules.sharding.sharding-algorithms.order-inline.type=INLINE
spring.shardingsphere.rules.sharding.sharding-algorithms.order-inline.props.algorithm-expression=order_$->{id % 16}
spring.shardingsphere.rules.sharding.sharding-algorithms.order-item-inline.type=INLINE
spring.shardingsphere.rules.sharding.sharding-algorithms.order-item-inline.props.algorithm-expression=order_item_$->{order_id % 16}
spring.shardingsphere.rules.sharding.key-generators.snowflake.type=snowflake
spring.shardingsphere.rules.sharding.key-generators.snowflake.props.worker-id=1
spring.shardingsphere.props.sql-show=true
# mybatis
mybatis.mapper-locations=classpath:mapper/**.xml
```

> SQL

```sql
CREATE TABLE IF NOT EXISTS `order`
(
    `id`                  bigint(20) primary key comment '订单 ID',
    `code`                varchar(40) not null comment '订单编码',
    `merchant_id`         bigint(20)  not null comment '卖家 ID',
    `user_id`             bigint(20)  not null comment '买家 ID',
    `amount`              int(10)     not null comment '订单金额',
    `cut_amount`          int(10)     not null comment '订单优惠金额',
    `shipping_address_id` bigint(20)  not null comment '订单配送地址',
    `status`              int(2)      not null comment '订单状态',
    `gmt_create`          timestamp   not null default current_timestamp comment '创建时间',
    `gmt_modified`        timestamp   not null default current_timestamp on update current_timestamp comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '订单';

CREATE TABLE IF NOT EXISTS `order_item`
(
    `id`           bigint(20) primary key comment '订单项 ID',
    `order_id`     bigint(20)  not null comment '订单 ID',
    `user_id`      bigint(20)  not null comment '用户 ID',
    `sku_code`     varchar(10) not null comment 'SKU 编码',
    `quantity`     int(4)      not null comment 'SKU 数量',
    `price`        int(10)     not null comment '商品价格',
    `item_amount`  int(10)     not null comment '订单项金额',
    `cut_amount`   int(10)     not null comment '订单项优惠金额',
    `gmt_create`   timestamp   not null default current_timestamp comment '创建时间',
    `gmt_modified` timestamp   not null default current_timestamp on update current_timestamp comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '订单项';
```

通过 shardingsphere jdbc 将创建表和删除表的方法入口:

1. 启动项目
2. 接口:
    * 调用 `curl -X POST http://127.0.0.1:8080/order/prepareDAOResource` 完成表创建;
    * 调用 `curl -X POST http://127.0.0.1:8080/order/destroyDAOResource` 完成表删除

通过 shardingsphere jdbc 实现订单表的增删查改的方法入口:

1. 启动项目
2. 接口:
    * 调用 `http://127.0.0.1:8080/order/create` 创建订单
    * 调用 `http://127.0.0.1:8080/order/cancel` 取消订单
    * 调用 `http://127.0.0.1:8080/order/delete` 删除订单
    * 调用 `http://127.0.0.1:8080/order/userOrders` 获取用户订单
   
> 接口入口

[代码入口](./week08_thurs_assignment_02/src/main/java/io/x12fd16b/week8/thurs/assignment02/api)

## 作业题目 (周六)

* （必做）基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。