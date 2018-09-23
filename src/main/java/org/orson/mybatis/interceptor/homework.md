> Question: 

1. 手写Plugin,多个interceptor到底谁先执行？顺序由谁决定的？

> And I said:

1. MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：

* Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
* ParameterHandler (getParameterObject, setParameters)
* ResultSetHandler (handleResultSets, handleOutputParameters)
* StatementHandler (prepare, parameterize, batch, update, query)

这些类中方法的细节可以通过查看每个方法的签名来发现，或者直接查看 MyBatis 发行包中的源代码。 如果你想做的不仅仅是监控方法的调用，那么你最好相当了解要重写的方法的行为。 因为如果在试图修改或重写已有方法的行为的时候，你很可能在破坏 MyBatis 的核心模块。 这些都是更低层的类和方法，所以使用插件的时候要特别当心。
   

多个Interceptor的执行顺序由配置的顺序决定，可以在XMLConfigBuilder中找到答案，遍历每个XML节点，将配置的拦截器进行包装。
```java
//加载插件
org.apache.ibatis.builder.xml.XMLConfigBuilder.pluginElement

//包装插件
org.apache.ibatis.plugin.InterceptorChain.pluginAll

```
例如一下配置,都对Executor的update进行拦截：
```xml
<plugin>
    <plugin interceptor="A"></plugin>
    <plugin interceptor="B"></plugin>
    <plugin interceptor="C"></plugin>
    <plugin interceptor="D"></plugin>
</plugin>
```

则包装的顺序是：A(executor) -> B(A(executor)) -> C(B(A(executor))) -> D(C(B(A(executor))));
则执行的时候，先执行D->C->B->A->executor->A->B->C->D.
