# el4j-web-framework
超轻量级模仿Spring的框架，模仿IOC，MVC。

![image](http://www.hubwiz.com/course/571f48c208ce8b3d3a143533/img/frame.jpg)

从Servlet的生命周期入手，DispatherServlet extends HttpServlet
- init()时，使用FrameworkLoader类初始化框架和应用。
  - ClassHelper,ClassUtil 加载应用basepackage下所有的类
  - BeanContainer 通过BeanFactory将ClassHelper加载获取的所有带有Controller,Service注解等需要容器管理的类，实例化并保存在容器中。没有处理scope问题
  - IocHelper 扫描容器，看对象是否有类注入的需求，即看有没有Autowired注解，有则使用 **设值注入** 方法注入
  - ControllerHelper 将Controller中带有RequestMapping注解的方法与其要处理的请求路径和请求方法建立映射关系
- DispatherServlet的service()方法，根据Request Method 和 Request Path转发所有的请求到相应的Handler处理
  - 支持JSP
  - 支持Json
  
Light weight spring-like framework. just included IOC,MVC


