mybatis

1、jdbc 的弊端

    1、重复获取/关闭连接    底层没有用连接池、频繁的创建与关联，需要消耗资源
    2、写原生的jdbc 代码结构复杂，一旦我们需要修改，java 需要整体编译，不利于系统维护，比如新增了一个字段，多有的都需要改
    3、使用prepareStatment 需要写定义index，设置123数字，这样的序号不利于维护 
    4、返回值也需要进行硬编码
    
    (不好用 就出现了框架) hibernate mybatis
    
2、mybatis 

    （orm 框架） object relation mapping  对象关系映射
    前身是 ibatis，apache 
    