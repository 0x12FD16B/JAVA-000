## 作业题目 (周五)
* 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
代码工程设计思路, 使用 Spring Boot 构建工程, 使用 Spring JDBC Template 模拟一下的场景插入数据:
1. 在一个循环 100 万次, 执行 insert into `order` values (xx, xx, xx)
2. 使用 JDBC Prepared Statement 的批处理功能, 测试以下 batch size 为 1000 的数据插入性能
3. 使用 insert into `order` values (xx, xx, xx), (xx, xx, xx), ....; 方式执行, 分别测试以下多值数据列表长度为 1000 的插入性能

[测试代码](./week07_thrus_assignment_02/src/test/java/io/x12fd16b/week7/thrus/assignment02/dao/OrderDAOTest.java)

> case1

| 运行描述 | 耗时 | 测试方法 |
| ------- | ----- | ----- | 
| 写入 100 万条订单数据和订单明细 (一条订单数据包含一条订单明细)<br>循环执行 `insert into ....` | 165847 ms | `testInsertWithLoop` |

> case2

| 运行描述 | 耗时 | 测试方法 |
| ------- | ----- | ------ |
| 写入 100 万条订单数据和订单明细 (一条订单数据包含一条订单明细)<br>使用 `PreparedStatement` Batch 处理方式 `batchSize` 设置为 500 | 20043 ms | `testPrepareStatementAddBatch_with_batchSize500`|
| 写入 100 万条订单数据和订单明细 (一条订单数据包含一条订单明细)<br>使用 `PreparedStatement` Batch 处理方式 `batchSize` 设置为 1000 | 20513 ms | `testPrepareStatementAddBatch_with_batchSize1000`|

> case3

| 运行描述 | 耗时 | 测试方法 |
| ------- | ----- | ------ |
| 写入 100 万条订单数据和订单明细 (一条订单数据包含一条订单明细)<br>使用 `insert into ... values (xxx), (xxxxx);` 多值写入, 只列表数量设置为 500 | 19398 ms | `testInsertBatch_with_valueSize500`|
| 写入 100 万条订单数据和订单明细 (一条订单数据包含一条订单明细)<br>使用 `insert into ... values (xxx), (xxxxx);` 多值写入, 只列表数量设置为 1000 | 20446 ms | `testInsertBatch_with_valueSize1000`|
| 写入 100 万条订单数据和订单明细 (一条订单数据包含一条订单明细)<br>使用 `insert into ... values (xxx), (xxxxx);` 多值写入, 只列表数量设置为 5000 | 22608 ms | `testInsertBatch_with_valueSize5000`|

 
## 作业题目 (周六)
* 读写分离 - 动态切换数据源版本 1.0

[代码实现](./week07_sat_assignment_02/src/main/java/io/x12fd16b/week7/sat/assignment02/)

[测试代码](./week07_sat_assignment_02/src/test/java/io/x12fd16b/week7/sat/assignment02/)

> 实现思路

1. 结合 `org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource` 和 Spring AOP 实现动态数据源切换, 实现通过注解的方式切换数据库读操作和写操作使用的数据源
2. 自定义 `DataSource` 实现多个从库的数据源实现轮询策略负载均衡

> 结果验证

1. 切换数据源的切面逻辑在选择数据源的时候会输出日志, 执行写入方法时, 会在日志中输出: `切换数据源到 master`
2. 在执行数据库读取操作的时候会使用到 `slave` 数据源, 多个 `slave` 通过输出数据源的名字得到区分: 在测试用例中有一个用例会执行 4 次查询操作, 结果中会看到多个从库数据源切换 

* 读写分离 - 数据库框架版本 2.0

> 实现说明

1. 使用 3 个数据库模拟数据库一主两从, master: db, slave: db1, db2
2. 设计测试用例, 验证数据写入到 master 库, 数据的读取按照配置的负载方式(轮询)到预期的数据源中执行查询

> 验证说明

[测试代码](./week07_sat_assignment_03/src/test/java/io/x12fd16b/week7/sat/assignment03/dao/UserDaoTest.java)

1. 使用 week6 作业中设计的 user 表进行验证, 在多个数据库中执行 user 表的建表 DDL 语句。
2. 执行测试代码中的创建用户的用例方法，执行通过后去 master 数据源中的 user 表中确认数据是否写入。
3. 在 slave 数据源中插入 user 数据, 在 `brief` 列中写入区分 slave 数据源的信息 (比如: db1 中的 user 表的 `brief` 字段写入 "user in db1"; db2 中的 user 表的 `brief` 字段写入 "user in db2"),
用例方法中会循环多次执行查询并输出查询结果, 执行完成后确认输出的查询结果slave 数据源是否按照预期的多个 slave 数据源轮询的方式在工作。 


> 应用配置

```properties
# 设置数据源
spring.shardingsphere.datasource.names=primary-ds,replica-ds-0,replica-ds-1
spring.shardingsphere.datasource.common.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.common.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.common.username=root
spring.shardingsphere.datasource.common.password=
# 配置 master
spring.shardingsphere.datasource.primary-ds.url=jdbc:mysql://127.0.0.1:3306/db?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
# 配置 slave
spring.shardingsphere.datasource.replica-ds-0.url=jdbc:mysql://127.0.0.1:3306/db1?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
spring.shardingsphere.datasource.replica-ds-1.url=jdbc:mysql://127.0.0.1:3306/db2?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
# ShardingSphere replica-query
spring.shardingsphere.enabled=true
spring.shardingsphere.rules.replica-query.data-sources.pr_ds.primary-data-source-name=primary-ds
spring.shardingsphere.rules.replica-query.data-sources.pr_ds.replica-data-source-names=replica-ds-0,replica-ds-1
spring.shardingsphere.rules.replica-query.data-sources.pr_ds.load-balancer-name=round-robin
spring.shardingsphere.rules.replica-query.load-balancers.round-robin.type=ROUND_ROBIN
spring.shardingsphere.rules.replica-query.load-balancers.round-robin.props.key=value
# 配置输出 SQL
spring.shardingsphere.props.sql.show=true
# 配置 Mybatis
mybatis.mapper-locations=classpath:mapper/**.xml

```

### 作业总结

1. 在批量写入数据时, 优先考虑使用 `Insert INTO table` 多值列表的方式, 合理设计值列表长度, 能提升写入效率。
2. 从读写分离 1.0 (Spring AOP 实现多数据源读写分离: 一主一从)，1.1 (Spring AOP 实现多数据源读写分离: 一主多从)，1.2 (Spring AOP 实现多数据源读写分离: 一主多从, 多从数据源实现负载均衡)，到 2.0 (使用 ShardingSphere-JDBC 实现读写分离)，
实现同样的需求(实现多数据源读写分离), 1.x 系列的实现对代码有明显的侵入性, 2.0 只需要做相应的配置

### 作业中遇到的问题
> 测试不同方式 Insert 性能

在 JDBC URL 上是否加入参数 `rewriteBatchedStatements=true` 对 PrepareStatement UpdateBatch 影响特别大

MySQL官方文档对 `` 参数的说明: 

> rewriteBatchedStatements
  
  Should the driver use multiqueries (irregardless of the setting of "allowMultiQueries") as well as rewriting of prepared statements for INSERT into multi-value inserts when executeBatch() is called? Notice that this has the potential for SQL injection if using plain java.sql.Statements and your code doesn't sanitize input correctly. Notice that for prepared statements, if you don't specify stream lengths when using PreparedStatement.set*Stream(), the driver won't be able to determine the optimum number of parameters per batch and you might receive an error from the driver that the resultant packet is too large. Statement.getGeneratedKeys() for these rewritten statements only works when the entire batch includes INSERT statements. Please be aware using rewriteBatchedStatements=true with INSERT .. ON DUPLICATE KEY UPDATE that for rewritten statement server returns only one value as sum of all affected (or found) rows in batch and it isn't possible to map it correctly to initial statements; in this case driver returns 0 as a result of each batch statement if total count was 0, and the Statement.SUCCESS_NO_INFO as a result of each batch statement if total count was > 0.
  
  Default: false
  
  Since version: 3.1.13

> 读写分离 2.0 作业

1. 配置问题

* `Invalid characters: '_'`
 
shardingsphere-jdbc-example 中 sharding-spring-boot-mybatis-example 中示例的配置文件 `application-replica-query.properties` 如下:

```properties
spring.shardingsphere.datasource.names=primary_ds,replica_ds_0,replica_ds_1

spring.shardingsphere.datasource.common.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.common.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.common.username=root
spring.shardingsphere.datasource.common.password=

spring.shardingsphere.datasource.primary_ds.jdbc-url=jdbc:mysql://localhost:3306/demo_primary_ds?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8

spring.shardingsphere.datasource.replica_ds_0.jdbc-url=jdbc:mysql://localhost:3306/demo_replica_ds_0?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8

spring.shardingsphere.datasource.replica_ds_1.jdbc-url=jdbc:mysql://localhost:3306/demo_replica_ds_1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8

spring.shardingsphere.rules.replica-query.load-balancers.round_robin.type=ROUND_ROBIN
spring.shardingsphere.rules.replica-query.data-sources.pr_ds.primary-data-source-name=primary_ds
spring.shardingsphere.rules.replica-query.data-sources.pr_ds.replica-data-source-names=replica_ds_0,replica_ds_1
spring.shardingsphere.rules.replica-query.data-sources.pr_ds.load-balancer-name=round_robin
```

按照上面的配置启动工程, 得到的错误信息如下:

```text
Description:

Configuration property name 'spring.shardingsphere.rules.replica-query.load-balancers.round_robin.props' is not valid:

    Invalid characters: '_'
    Reason: Canonical names should be kebab-case ('-' separated), lowercase alpha-numeric characters and must start with a letter

Action:

Modify 'spring.shardingsphere.rules.replica-query.load-balancers.round_robin.props' so that it conforms to the canonical names requirements.
```

**解决方法**: 提示`配置内容命名`问题, 将配置文件中所有配置内容中的 '_' 改成 '-'

* `Caused by: java.util.NoSuchElementException: No value bound`

配置内容如下:
```properties
# 配置数据源名称
spring.shardingsphere.datasource.names=primary-ds,replica-ds-0,replica-ds-1
spring.shardingsphere.datasource.common.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.common.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.common.username=root
spring.shardingsphere.datasource.common.password=1q2w3e4r
# 配置 master
spring.shardingsphere.datasource.primary-ds.url=jdbc:mysql://127.0.0.1:3306/db?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
# 配置 slave
spring.shardingsphere.datasource.replica-ds-0.url=jdbc:mysql://127.0.0.1:3306/db1?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
spring.shardingsphere.datasource.replica-ds-1.url=jdbc:mysql://127.0.0.1:3306/db2?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
# ShardingSphere
spring.shardingsphere.enabled=true
spring.shardingsphere.rules.replica-query.data-sources.pr_ds.primary-data-source-name=primary-ds
spring.shardingsphere.rules.replica-query.data-sources.pr_ds.replica-data-source-names=replica-ds-0,replica-ds-1
spring.shardingsphere.rules.replica-query.data-sources.pr_ds.load-balancer-name=round_robin
spring.shardingsphere.rules.replica-query.load-balancers.round_robin.type=ROUND_ROBIN
# 配置输出 SQL
spring.shardingsphere.props.sql.show=true
# 配置 Mybatis
mybatis.mapper-locations=classpath:mapper/**.xml
```

错误信息如下:

```text
java.lang.IllegalStateException: Failed to load ApplicationContext

	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:132)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:123)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:118)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:83)
	at org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener.prepareTestInstance(SpringBootDependencyInjectionTestExecutionListener.java:43)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:244)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.createTest(SpringJUnit4ClassRunner.java:227)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner$1.runReflectiveCall(SpringJUnit4ClassRunner.java:289)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.methodBlock(SpringJUnit4ClassRunner.java:291)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:246)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:97)
	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:190)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:69)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:220)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:53)
Caused by: java.lang.reflect.UndeclaredThrowableException
	at org.springframework.util.ReflectionUtils.rethrowRuntimeException(ReflectionUtils.java:147)
	at org.springframework.boot.SpringApplication.handleRunFailure(SpringApplication.java:821)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:120)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:99)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:124)
	... 26 more
Caused by: java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.shardingsphere.spring.boot.util.PropertyUtil.v2(PropertyUtil.java:111)
	at org.apache.shardingsphere.spring.boot.util.PropertyUtil.handle(PropertyUtil.java:75)
	at org.apache.shardingsphere.spring.boot.registry.AbstractAlgorithmProvidedBeanRegistry.lambda$registerBean$1(AbstractAlgorithmProvidedBeanRegistry.java:57)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at org.apache.shardingsphere.spring.boot.registry.AbstractAlgorithmProvidedBeanRegistry.registerBean(AbstractAlgorithmProvidedBeanRegistry.java:55)
	at org.apache.shardingsphere.replicaquery.spring.boot.algorithm.ReplicaQueryAlgorithmProvidedBeanRegistry.postProcessBeanDefinitionRegistry(ReplicaQueryAlgorithmProvidedBeanRegistry.java:43)
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors(PostProcessorRegistrationDelegate.java:280)
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:126)
	at org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:707)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:533)
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:758)
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:750)
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:315)
	... 29 more
Caused by: java.util.NoSuchElementException: No value bound
	at org.springframework.boot.context.properties.bind.BindResult.get(BindResult.java:55)
	... 47 more
```

错误原因: 目前版本的 BUG, [Github Issue](https://github.com/apache/shardingsphere/issues/8459)

**解决方法**: 在配置文件中加入相关的属性让 `PropertyUtil.handle` 解析配置内容通过, 添加的配置项如下:

```properties
spring.shardingsphere.rules.replica-query.load-balancers.round-robin.props.key=value
```

* `Cannot load connection class because of underlying exception: com.mysql.cj.exceptions.WrongArgumentException: The database URL cannot be null.`

将配置中的

```properties
spring.shardingsphere.datasource.primary_ds.jdbc-url=jdbc:mysql://localhost:3306/demo_primary_ds?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
spring.shardingsphere.datasource.replica_ds_0.jdbc-url=jdbc:mysql://localhost:3306/demo_replica_ds_0?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
spring.shardingsphere.datasource.replica_ds_1.jdbc-url=jdbc:mysql://localhost:3306/demo_replica_ds_1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
```

更改为:

```properties
spring.shardingsphere.datasource.primary_ds.url=jdbc:mysql://localhost:3306/demo_primary_ds?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
spring.shardingsphere.datasource.replica_ds_0.url=jdbc:mysql://localhost:3306/demo_replica_ds_0?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
spring.shardingsphere.datasource.replica_ds_1.url=jdbc:mysql://localhost:3306/demo_replica_ds_1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
```