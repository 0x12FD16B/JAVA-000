## 作业题目 (周五)
* 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
代码工程设计思路, 使用 Spring Boot 构建工程, 使用 Spring JDBC Template 模拟一下的场景插入数据:
1. 在一个循环 100 万次, 执行 insert into `order` values (xx, xx, xx)
2. 使用 JDBC Prepared Statement 的批处理功能, 测试以下 batch size 为 1000 的数据插入性能
3. 使用 insert into `order` values (xx, xx, xx), (xx, xx, xx), ....; 方式执行, 分别测试以下多值数据列表长度为 1000 的插入性能

[测试代码](./week07_thrus_assignment_02/src/test/java/io/x12fd16b/week7/thrus/assignment02/dao/OrderDAOTest.java)

## 作业题目 (周六)
* 读写分离 - 动态切换数据源版本 1.0

[代码实现](./week07_sat_assignment_02/src/main/java/io/x12fd16b/week7/sat/assignment02/)

实现思路:
    * 结合 `org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource` 和 Spring AOP 实现动态数据源切换, 实现通过注解的方式切换数据库读操作和写操作使用的数据源
    * 自定义 `DataSource` 实现多个从库的负载均衡

* 读写分离 - 数据库框架版本 2.0