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