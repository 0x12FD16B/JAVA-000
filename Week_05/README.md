## 作业题目 (周四)
### 1. 使 Java 里的动态代理，实现一个简单的 AOP。
* [代码实现](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_01/src/main/java/io/x16fd16b/assignment01)
### 2. 写代码实现 Spring Bean 的装配，方式越多越好
* 基于注解 `@ComponentScan` 和 `@Autowire` 实现 Bean 的自动装配, 使用 `AnnotationConfigApplicationContext` 初始化 Spring 容器。 [代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_02/src/main/java/io/x16fd16b/assignment02/beanware/auto)
* 基于注解 `@Bean` 使用 Java 代码实现 Bean 的装配, 使用 `AnnotationConfigApplicationContext` 初始化 Spring 容器。[代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_02/src/main/java/io/x16fd16b/assignment02/beanware/config)
* 基于 `xml` 文件完成 Bean 的装配, 使用 `ClassPathXmlApplicationContext` 初始化 Spring 容器。[代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_02/src/main/java/io/x16fd16b/assignment02/beanware/xml)

## 作业题目 (周六)
### 4. 给前面课程提供的 Student/Klass/School 实现自动配置和 Starter
* [实现代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_03/src/main/java/io/x16fd16b/assignment03/school/starter)
* [测试代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_03/src/test)

### 6. 研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
* 使用 JDBC 原生接口，实现数据库的增删改查操作。
    * [实现代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_04/plain_jdbc/src/main/java/io/x16fd16b/assignment04/plain/jdbc/)
    * [测试代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_04/plain_jdbc/src/test/java/io/x16fd16b/assignment04/plain/jdbc/)
* 使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
    * [实现代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_04/jdbc_prepared_statement/src/main/java/io/x16fd16b/assignment04/jdbc/prepared/statement/)
    * [测试代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_04/jdbc_prepared_statement/src/test/java/io/x16fd16b/assignment04/jdbc/prepared/statement/)
* 配置 Hikari 连接池，改进上述操作。提交代码到 Github。
    * [实现代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_04/jdbc_hikari/src/main/java/io/x16fd16b/assignment04/jdbc/hikari/)
    * [测试代码](https://github.com/0x12FD16B/JAVA-000/tree/main/Week_05/week05_assignment_04/jdbc_hikari/src/test/java/io/x16fd16b/assignment04/jdbc/hikari/)