## 作业和学习笔记

* 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？
  写出你的方法，越多越好，提交到 Github。
  
    * 定义任务线程: 定义一个任务线程, 通过 `Thread#join` 方法来控制主线程在任务线程执行完成之后通过任务线程结果的 `getter` 方法获取执行结果。 [代码](./thread/src/main/java/course/java/assignment/useThreadJoin)
    * 使用 `Future`: 提交 `Callable` 任务到线程池。[代码](./thread/src/main/java/course/java/assignment/useFuture)
    * 使用 `Synchronized`: 使用 `Synchronized` 关键字让主线程中获取计算结果的方法和异步线程中计算的方法同步。[代码](./thread/src/main/java/course/java/assignment/useSynchronized)
    * 使用 `Semaphore`: 使用 `Semaphore`, 异步任务持有 `permits` 为 1 的 `Semaphore`, 让主线程中获取计算结果的方法和异步线程中计算的方法同步。[代码](./thread/src/main/java/course/java/assignment/useSemaphore)
    * 使用 `Lock` 和 `Condition`: 使用 `Lock` 和 `Condition`, 让主线程中获取计算结果的方法和异步线程中计算的方法同步。[代码](./thread/src/main/java/course/java/assignment/useLockCondition)
    * 使用 `CountDownLatch`: [代码](./thread/src/main/java/course/java/assignment/useCountDownLatch)
    * 使用 `CyclicBarrier`: [代码](./thread/src/main/java/course/java/assignment/useCyclicBarrier)
    * 使用 `CompletableFuture`: [代码](./thread/src/main/java/course/java/assignment/useCompletableFuture)