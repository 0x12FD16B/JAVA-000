## 作业题目

### 周四作业

1. 搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。

> 环境搭建

1. 下载 ActiveMQ

使用 ActiveMQ 版本 [5.15.14](http://www.apache.org/dyn/closer.cgi?filename=/activemq/5.15.14/apache-activemq-5.15.14-bin.tar.gz&action=download), 下载后得到目标文件
apache-activemq-5.15.14-bin.tar.gz

2. 解压文件

```shell
tar xzvf apache-activemq-5.15.14-bin.tar.gz
```

解压后, 进入 `apache-activemq-5.15.14` 目录后, 得到目录如下:

```text
├── LICENSE
├── NOTICE
├── README.txt
├── activemq-all-5.15.14.jar
├── bin
├── conf
├── data
├── docs
├── examples
├── lib
├── webapps
└── webapps-demo
```

3. 修改配置

`ActiveMQ` 的配置文件所在的目录是 `conf`, 主要关注的配置有:

* `activemq.xml`: 这个配置文件是 `ActiveMQ` 主要的配置, 里面可以配置消息持久化的方式 (更多关于持久化相关的配置, [官方文档](http://activemq.apache.org/persistence.html)有更详细的信息.), 其中

```xml

<transportConnectors>
    <!-- DOS protection, limit concurrent connections to 1000 and frame size to 100MB -->
    <transportConnector name="openwire" uri="tcp://0.0.0.0:6161?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
    <transportConnector name="amqp" uri="amqp://0.0.0.0:5672?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
    <transportConnector name="stomp" uri="stomp://0.0.0.0:61613?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
    <transportConnector name="mqtt" uri="mqtt://0.0.0.0:1883?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
    <transportConnector name="ws" uri="ws://0.0.0.0:61614?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
</transportConnectors>
```

配置 `ActiveMQ` 使用那种协议, `jetty.xml` 管理 `ActiveMQ` 管理控制台的 web 应用配置.

> 代码编写

* 程序功能:
1. SpringBoot Web 应用, 集成 spring-boot-activemq 开放接口发送消息到 activemq queue 和 topic
2. 应用中实现 queue 和 topic 的监听

* 程序运行细节:

1. 应用中配置了 queue: assignment.queue, topic: assignment.topic; 开放了接口 `POST /jms/sendMessage2Queue` 和 `POST /jms/sendMessage2Topic`, 分别提供发送消息到 queue 和 topic 的功能
2. 程序中添加了对 queue: assignment.queue, topic: assignment.topic 的消息监听

[实现代码](./thurs_assignment01/src/main/java/io/x12fd16b/assignment/week13/thurs/assignment01/)

### 周六作业

1. 搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。

> 环境准备

1. 下载 Kafka

在 Apache Kafka [主页](http://kafka.apache.org/), 下载 Kafka 2.6.1 [安装包](https://mirrors.tuna.tsinghua.edu.cn/apache/kafka/2.6.1/kafka_2.12-2.6.1.tgz)

2. 解压文件

```shell
tar xzvf kafka_2.12-2.6.1.tgz
```

解压后, 得到目录 `kafka_2.12-2.6.1`, 将此目录复制 3 份, 分别为 `kafka_01`, `kafka_02`, `kafka_03`, 得到目录结构如下:

```text
├── kafka_01
├── kafka_02
└── kafka_03
```

3. 修改配置文件

kafka broker 相关的配置文件在 `config` 目录下, 修改 `server.properties`:

主要修改的内容是 `broker.id`, `log.dirs`, 和 `zookeeper.connect`; 不同的 kafka broker, `broker.id` 不能重复, 在同一台机器上 `log.dirs` 不能相同, 一个集群 `zookeeper.connect` 的地址应该相同.

4. 启动集群

```shell
./kafka_01/bin/kafka-server-start.sh config/server.properties &
./kafka_02/bin/kafka-server-start.sh config/server.properties &
./kafka_03/bin/kafka-server-start.sh config/server.properties &
```

5. 验证集群

使用 ZooInspector 查看 kafka 集群元数据, 观察 zookeeper 关于 kafka 集群的节点信息 `/cluster`, `/controller_epoch`, `/contoller`, `/brokers`.

* 通过发送消息到 topic 验证集群是否创建成功

使用 kafka 安装目录下 `kafka-topics.sh` 创建 topic:

```shell
./kafka-topics.sh --create --zookeeper 192.168.118.14:2181 --replication-factor 3 --partitions 3 --topic assignment
Created topic assignment.
```
`--replication-factor 2` 设置复制 3 份, `--partitions 1` 指定在 3 个 broker 中创建 3 个分区, `--topic assignment` 设置主题名为 `assignment`

使用 kafka 安装目录下 `kafka-console-producer.sh` 发送消息到 `broker0`, 使用 `kafka-console-consumer.sh` 到 `broker1` 接收消息.

```shell
./kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic assignment
```

```shell
./kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9093 --topic assignment --from-beginning
```

> 集群性能测试

使用 `kafka-producer-perf-test.sh` 测试集群发送

```shell
kafka-producer-perf-test.sh --num-records 5000000 --record-size 1000 --throughput 200000 --topic assignment --producer-props bootstrap.servers=127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094
310521 records sent, 62104.2 records/sec (59.23 MB/sec), 450.1 ms avg latency, 911.0 ms max latency.
705192 records sent, 141038.4 records/sec (134.50 MB/sec), 241.2 ms avg latency, 909.0 ms max latency.
947573 records sent, 189514.6 records/sec (180.74 MB/sec), 173.9 ms avg latency, 365.0 ms max latency.
891636 records sent, 178327.2 records/sec (170.07 MB/sec), 183.2 ms avg latency, 336.0 ms max latency.
779989 records sent, 155252.6 records/sec (148.06 MB/sec), 208.9 ms avg latency, 486.0 ms max latency.
909642 records sent, 181928.4 records/sec (173.50 MB/sec), 181.9 ms avg latency, 417.0 ms max latency.
5000000 records sent, 153600.393217 records/sec (146.48 MB/sec), 209.89 ms avg latency, 911.00 ms max latency, 252 ms 50th, 479 ms 95th, 813 ms 99th, 896 ms 99.9th.
```

使用 `kafka-consumer-perf-test.sh` 测试集群消费性能: 

```shell
kafka-consumer-perf-test.sh --bootstrap-server 127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094 --topic assignment --fetch-size 1048576 --messages 5000000 --threads 1
```

得到结果:

| start.time | end.time | data.consumed.in.MB | MB.sec | data.consumed.in.nMsg | nMsg.sec | rebalance.time.ms | fetch.time.ms | fetch.MB.sec |  fetch.nMsg.sec |
| ---------- | ---------| --------------------| -------| ----------------------| ---------| ------------------| --------------| -------------| ----------------|
| 2021-01-14 15:51:08:011 | 2021-01-14 15:51:14:263 | 4768.5642 | 762.7262 | 5000209 | 799777.5112 | 1610610668424 | -1610610662172 | -0.0000 | -0.0031 |
