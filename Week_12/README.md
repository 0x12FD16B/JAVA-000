## Week12 作业题目

### 1.（必做）配置 redis 的主从复制，sentinel 高可用，Cluster 集群。

> Redis 主从配置

* 准备多个 Redis 实例的配置文件

**master 节点** 配置: redis_6379.conf

```text
port 6379
daemonize yes
requirepass master
```

**slave 节点** 配置: redis_6380.conf

```text
port 6380
daemonize yes
slaveof 127.0.0.1 6379
masterauth master
```

* 使用 Redis 命令 `redis-server` 分别启动 master 节点和 slave 节点

```shell
redis-server redis_6379.conf
redis-server redis_6380.conf
```

* 验证主从

1. 验证主从节点 "角色信息"

使用 redis-cli 连接上两个 redis 实例, 执行 `info` 命令后, 在 master 节点可得到如下标识 master 节点的信息:

```text
# Replication
role:master
connected_slaves:1
slave0:ip=127.0.0.1,port=6380,state=online,offset=42,lag=1
master_replid:1412d90add1fa445e3726063293bf6af8201c51e
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:56
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:56
```

在信息中也能看到集群的 slave 节点的信息

2. 在主节点中 set 一个 key, 在从节点中也能查到
3. 从节点是只读节点, 验证在从节点中 set 一个 key, 得到一个错误提示

```text
127.0.0.1:6380> set foo bar
(error) READONLY You can't write against a read only replica.
```

> sentinel 高可用

* 配置目标

搭建一主两从三节点的 redis 主从, 搭建三节点的 sentinel 集群 (多哨兵集群的方式能够保证哨兵服务的高可用, 多哨兵之间使用 raft 协议达成分布式一致, 所以哨兵的节点数量需要在两个以上)

* 配置文件准备

1. 3 节点 Redis 集群搭建

配置文件分别为: redis_6379.conf, redis_6380.conf, redis_6381.conf

**master 节点** redis_6379.conf:

```text
port 6379
daemonize yes
```

**slave 节点** 配置: redis_6380.conf

```text
port 6380
daemonize yes
slaveof 127.0.0.1 6379
```

**slave 节点** 配置: redis_6381.conf

```text
port 6381
daemonize yes
slaveof 127.0.0.1 6379
```

**启动 Redis 实例**:

```text
redis-server redis_6379.conf
redis-server redis_6380.conf
redis-server redis_6381.conf
```

2. 3 节点哨兵实例集群搭建

配置文件分别为: sentinel_26379.conf, sentinel_26380.conf, sentinel_26381.conf

**sentinel_26379.conf**:

```text
port 26379
daemonize yes
sentinel deny-scripts-reconfig yes
sentinel monitor mymaster 127.0.0.1 6379 2
```

**sentinel_26380.conf**:

```text
port 26380
daemonize yes
sentinel deny-scripts-reconfig yes
sentinel monitor mymaster 127.0.0.1 6379 2
```

**sentinel_26381.conf**:

```text
port 26381
daemonize yes
sentinel deny-scripts-reconfig yes
sentinel monitor mymaster 127.0.0.1 6379 2
```

**启动 Redis 哨兵**:

```shell
redis-server sentinel_26379.conf --sentinel
redis-server sentinel_26380.conf --sentinel
redis-server sentinel_26381.conf --sentinel
```

* 验证 sentinel 高可用

启动完 sentinel 所有节点后, 关闭 redis master 节点, 可以观察到 sentinel 节点输出新的 master 选举日志, 选举完成之后可以观察到新主从集群的 master 节点变成了端口是 6380 的 Redis 节点. 并且 sentinel 配置文件的内容被 sentinel 进程更改为监听新的 redis master
节点.

> Cluster 搭建

哨兵模式已经能够满足 Redis 高可用的使用需求, 但是它也有个明显的缺点, 就是不能动态扩充 Redis 节点, 所以 Redis 在 3.x 版本提出了 cluster 集群模式。

* 配置目标

搭建三节点的 redis cluster 集群, 端口分别为 6379, 6380, 6381

* 配置文件准备

**redis_6379.conf**:

```text
port 6379
daemonize yes
cluster-enabled yes
cluster-config-file nodes_6379.conf
cluster-node-timeout 5000
```

**redis_6380.conf**:

```text
port 6380
daemonize yes
cluster-enabled yes
cluster-config-file nodes_6380.conf
cluster-node-timeout 5000
```

**redis_6381.conf**:

```text
port 6381
daemonize yes
cluster-enabled yes
cluster-config-file nodes_6381.conf
cluster-node-timeout 5000
```

* 编写脚本 **start-redis.sh**, 启动所有 redis 实例节点

```shell
redis-server redis_6379.conf
redis-server redis_6380.conf
redis-server redis_6381.conf
```

* 创建 cluster 集群

```shell
redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381
```

执行命令后, 得到如下输出:

```text
>>> Performing hash slots allocation on 3 nodes...
Master[0] -> Slots 0 - 5460
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
M: a567468f6b1a2ccec008d0c2ca7875388b613fbf 127.0.0.1:6379
   slots:[0-5460] (5461 slots) master
M: 957129c6f79cc269d41fb6c88868d96c4292a08b 127.0.0.1:6380
   slots:[5461-10922] (5462 slots) master
M: 0f436d5e3f26110889b9b392277897cbe25d8854 127.0.0.1:6381
   slots:[10923-16383] (5461 slots) master
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
..
>>> Performing Cluster Check (using node 127.0.0.1:6379)
M: a567468f6b1a2ccec008d0c2ca7875388b613fbf 127.0.0.1:6379
   slots:[0-5460] (5461 slots) master
M: 957129c6f79cc269d41fb6c88868d96c4292a08b 127.0.0.1:6380
   slots:[5461-10922] (5462 slots) master
M: 0f436d5e3f26110889b9b392277897cbe25d8854 127.0.0.1:6381
   slots:[10923-16383] (5461 slots) master
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.
```

* 验证 cluster 集群

1. 连接上 redis 实例节点, set 一个节点, 得到 MOVED 重定向消息

```shell
redis-cli -p 6379
127.0.0.1:6379> set foo bar
(error) MOVED 12182 127.0.0.1:6381
127.0.0.1:6379> get foo
(error) MOVED 12182 127.0.0.1:6381
```

