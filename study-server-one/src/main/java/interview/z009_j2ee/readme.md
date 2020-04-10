**Servlet 总结**

        在java Web程序中，Servlet主要接收用户请求 HttpServletRequest,在doGet(),doPost() 中做相应的处理，并将回应HttpServletResponse 反馈给用户。Servlet
    可以设置初始化参数，供Servlet内部使用。一个Servlet只会有一个实例，在它初始化的时候调用init()方法，销毁时调用destroy方法。
        一个Servlet可以设置多个URL访问。Servlet 不是线程安全的，因此要谨慎使用变量。
        
**阐述Servlet 与 CGI 的区别**

        CGI:通用网关接口(Common Gateway Interface) 是一个Web 服务器主机提供信息服务的标准接口。
        CGI 就是一个接口规范，所有按照CGI 接口规范开发的程序都可以叫做CGI程序。
        
        CGI 的不足之处：
        1、需要为每一个请求启动一个操作CGI程序的系统进程。如果请求频繁，这将会带来很大的开销。
        2、需要为每个请求加载和运行一个CGI程序，这将带来很大的开销。
        3、需要重复编写处理网络协议的代码及编码，这些工作是非常耗时的。
        
        Servlet的优点:
        
        1、只需要启动一个操作系统进程以及加载一个JVM，大大降低了系统的开销
        2、如果多个气你去需要做同样的处理的时候，这时候只需要加载一个类，这也大大降低了开销
        3、所有动态加载的类可以实现对网络协议以及请求解码的共享，大大降低了工作量。
        4、Servlet 能直接和Web服务器交互，而普通的CGI程序不能，Servlet 还能在各个程序之间共享数据，使数据库连接池之类的功能很容易实现。
        
**Servlet 接口中有哪些方法及Servlet生命周期探秘**

    Servlet 接口定义了5个方法，其中前三个方法与Servlet生命周期有关:
    
    * void init(ServletConfig config) throws ServletException
    * void service(ServletRequest req,ServletResponse resp) throw ServletException,java.io.IOException
    * void destroy()
    * String getServletInfo()
    * ServletConfig getServletConfig()
    
    生命周期：Web容器加载servlet并将其实例化，Servlet生命周期开始，
    容器运行其init方法进行servlet 的初始化；请求到达时调用Servlet的service()方法，service()方法会根据调用与请求对应的doGet或doPost方法；
    当服务器关闭或项目被卸载时服务器会将servlet实例销毁，此时会调用Servlet的destroy()方法，init方法和destroy方法只会执行一次，
    service 方法客户端每次请求servlet都会执行，Servlet 有时会用到一些需要初始化与销毁的资源，因此可以吧初始化资源的代码方法init方法中，
    销毁资源的代码放入destroy方法中，这样就不需要每次处理客户端的请求都要初始化与销毁资源。
    
    
**Get 和 Post请求的区别**
     