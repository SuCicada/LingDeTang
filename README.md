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
    - [基于Token的WEB后台认证机制](https://www.cnblogs.com/xiekeli/p/5607107.html)
    - [JWT生成token及过期处理方案](https://my.oschina.net/odetteisgorgeous/blog/1920762)
    - [WEB后台--基于Token的WEB后台登录认证机制（并讲解其他认证机制以及cookie和session机制）](https://www.jianshu.com/p/227306fa28e4)
    - [token机制完成登录状态保持/身份认证](https://www.jianshu.com/p/8d28e60af440)
    - [Java MD5](https://www.jianshu.com/p/0086b0242cd6)
    - [JWT的Java使用 (JJWT)](https://blog.csdn.net/qq_37636695/article/details/79265711)
    - [基于Java的JJWT实现JWT认证](https://blog.csdn.net/u010953880/article/details/86735797)
    
- question
    - [jetty9 <welcome-file-list>不生效的解决方法](https://www.jianshu.com/p/358aae19969e)
    - [net.sf.ehcache.util.UpdateChecker.checkForUpdate问题解决方案](https://blog.csdn.net/wo541075754/article/details/79737289)
    - [java.lang.IllegalArgumentException: No converter found for return value of type](https://stackoverflow.com/questions/37841373/java-lang-illegalargumentexception-no-converter-found-for-return-value-of-type)
    - [解决Intellij IDEA Tomcat启动项目报错：java.lang.ClassNotFoundException:org.springframework.web.context.ContextLoaderListener](https://www.jianshu.com/p/18d068f47b09)
    - [springmvc添加CrossOrigin仍然无法跨域问题](https://blog.csdn.net/wfm19970/article/details/99494633)
    - [SpringMVC设置跨域mvc:cors报错The matching wildcard is strict, but no declaration can be found 解决](https://blog.csdn.net/ydk888888/article/details/83417259)
    - [log4j:WARN No appenders could be found for logger 解决办法](https://blog.csdn.net/chw0629/article/details/80567936)
    - [【SpringMVC】与权限拦截器冲突导致的Cors跨域设置失效问题](https://segmentfault.com/a/1190000010348077)
    - [Java设置Access-Control-Allow-Origin允许多域名访问的实现方法](https://www.jb51.net/article/148573.htm)
    
## server configuration

# 特色
- 使用CORS支持跨域访问
- JWT 验证:
    - 后端生成token, 返回token, 验证token
    - 前端存储token, 发送token

# log
- 10.23
    - 尝试使用拦截器
    - 发现前端请求登录有两次请求,第一次是option
    - 决定前端使用cookie 存储token
    - 前端增加subbs js集成功能

- 10.22
    - 处理登录功能
    - log: 
        - use session directly -> i cannot, 前后端分离
        - token 貌似不错
        - jwt  很不错 -> 难以保证多端用户改密码, 登出
        - cookie, session -> 还是不想用
        - token 试试看
        - jwt + 数据库存储