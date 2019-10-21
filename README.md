# 令德堂 - 山西大学论坛

项目使用 maven 配置, web容器采用 jetty, orm框架采用 hibernate

- 实体类的属性名要和ajax传入数据名一致, 才能自动构建实体对象

- hibernate
    - 二级缓存
    - 投影查询
    - hql

- intellij 
    - [创建 java web项目](https://www.cnblogs.com/yangyquin/p/5285272.html)
    - [创建 spring mvc web 项目](https://www.cnblogs.com/yangyquin/p/5286457.html)
    - [为了自动下载包 配置 maven](https://blog.csdn.net/qq_32588349/article/details/51461182)

- knowledge
    - [web.xml中的url-pattern 写法小结](https://blog.csdn.net/farawaywl/article/details/52902902)
    - [@ResponseBody返回4种数据格式的数据](https://blog.csdn.net/weixin_42189604/article/details/82179660)
    - [SpringMVC+RestFul详细示例实战教程（实现跨域访问）](https://blog.51cto.com/sihai/2127929)

- question
    - [jetty9 <welcome-file-list>不生效的解决方法](https://www.jianshu.com/p/358aae19969e)
    - [net.sf.ehcache.util.UpdateChecker.checkForUpdate问题解决方案](https://blog.csdn.net/wo541075754/article/details/79737289)
    - [java.lang.IllegalArgumentException: No converter found for return value of type](https://stackoverflow.com/questions/37841373/java-lang-illegalargumentexception-no-converter-found-for-return-value-of-type)
    - [解决Intellij IDEA Tomcat启动项目报错：java.lang.ClassNotFoundException:org.springframework.web.context.ContextLoaderListener](https://www.jianshu.com/p/18d068f47b09)
    - [springmvc添加CrossOrigin仍然无法跨域问题](https://blog.csdn.net/wfm19970/article/details/99494633)
    - [SpringMVC设置跨域mvc:cors报错The matching wildcard is strict, but no declaration can be found 解决](https://blog.csdn.net/ydk888888/article/details/83417259)
    
## server configuration

# 特色
- 使用CORS支持跨域访问
- 