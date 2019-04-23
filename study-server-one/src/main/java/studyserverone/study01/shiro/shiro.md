https://blog.csdn.net/weixin_38132621/article/details/80216056

一、依赖包 
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring</artifactId>
    <version>1.3.2</version>
</dependency>

二、数据库表sql

CREATE TABLE `study01-zt`.`user`  (
  `id` int(0) NOT NULL,
  `user_name` varchar(255),
  `pass_word` varchar(255),
  `role` varchar(255),
  PRIMARY KEY (`id`)
);
INSERT INTO `study01-zt`.`user`(`id`, `user_name`, `pass_word`, `role`) VALUES (1, 'zhutong', '123456', 'admin');
INSERT INTO `study01-zt`.`user`(`id`, `user_name`, `pass_word`, `role`) VALUES (2, 'limeihua', '123456', 'user');
