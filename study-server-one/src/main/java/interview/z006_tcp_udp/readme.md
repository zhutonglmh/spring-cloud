计算机网络


1、七层模型

模型优点
建立七层模型的主要目的是为解决异种网络互连时所遇到的兼容性问题。它的最大优点是将服务、接口和协议这三个概念明确地区分开来：
服务说明某一层为上一层提供一些什么功能，接口说明上一层如何使用下层的服务，而协议涉及如何实现本层的服务；这样各层之间具有
很强的独立性，互连网络中各实体采用什么样的协议是没有限制的，只要向上提供相同的服务并且不改变相邻层的接口就可以了。网络七
层的划分也是为了使网络的不同功能模块（不同层次）分担起不同的职责，从而带来如下好处： 　　 
● 减轻问题的复杂程度，一旦网络发生故障，可迅速定位故障所处层次，便于查找和纠错； 　　 
● 在各层分别定义标准接口，使具备相同对等层的不同网络设备能实现互操作，各层之间则相对独立，一种高层协议可放在多种低层协议上运行； 　　 
● 能有效刺激网络技术革新，因为每次更新都可以在小范围内进行，不需对整个网络动大手术； 　　 
● 便于研究和教学。

2、详细介绍

                    应用层     ：访问网络服务的接口；
                    
                                 例如：为操作系统或网络应用程序提供访问网络服务的接口
                                 常见：Telnet、FTP、HTTP、SNMP、DNS
                    
                    表示层     ：提供数据格式转换服务
                    
                                 例如：解密与加密，图片解码和编码、数据的压缩和解压缩
                                常见：URL加密、口令加密、图片解码
主机                     

                    会话层     ：建立端连接并提供访问验证和会话管理(SESSION)
                    
                                 例如：使用校验点可使会话在通信失效时从校验点恢复通信
                                 常见：服务器验证用户登录、断点续传
                               
                    
                    传输层     ：提供应用程序之间的逻辑通信                      数据段(Segment)
                    
                                例如：建立连接、处理数据包错误、数据包次序
                                常见：TCP、UDP、SPX、进程、端口(socket)






                    网络层     ：为数据在结点之间传输创建逻辑链路，并分组转发数据
                    
                                例如：对子网间的数据包进行路由选择
                                常见：路由器、多层交换机、防火墙、IP、IPX、RIP、OSPF
网络
                    
                    数据链路层  ：在通信的实体间建立数据链路连接
                    
                                例如：将数据分帧，并进行流控制、物理地址寻址、重发等
                                常见：网卡、网桥、二层交换机等
                    
                    
                    物理层      ：为数据端设备提供原始比特流的传输的通路
                    
                                例如：网络通信的数据传输介质，由电缆与设备共同构成
                                常见：中继器、集线器、网线、HUB、RJ-45标准等