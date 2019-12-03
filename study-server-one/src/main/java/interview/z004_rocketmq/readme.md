rocket 
服务器地址 /opt/rocketmq-all-4.4.3....

139.199.209.216
端口：9876

Nameserver的9876端口，Broker的10911端口,VIP通道10909端口（rocketMq默认有开启，我们也可以手动关闭）,控制台的12581端口

必须要先启动服务  启动nameserver和broker

①sh mqnamesrv &  
②sh mqbroker -n localhost:9876 &
③启动 rocket-console   
java -jar rocketmq-console-ng-1.0.1.jar --server.port=12581 --rocketmq.config.namesrvAddr=139.199.209.216:9876 &
https://blog.csdn.net/qq_24313635/article/details/82885874

控制台地址：http://139.199.209.216:12581/#/topic