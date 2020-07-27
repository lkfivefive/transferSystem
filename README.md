# transferSystem
design a simple transfer system on javaweb.

项目介绍：

本课题针对信息的安全存储问题，对信息加密技术的原理与流程进行了研究与设计。本系统是在Windows10环境下以及Eclipse，MySQL等开发工具的基础上，采用MVC模式，使用Java编程语言设计和实现的模拟转账系统。首先对比各种加密算法，从三种最具代表性的算法AES，RSA，MD5中选择了AES，MD5加以结合实现，其次设计MySQL数据表与相应的功能模块，最后进行功能模块界面的设计与链接。研究结果表明，本课题研究了转账服务的类型、过程及安全方法，和对于相关加密算法解密算法的研究与应用实现，使得用户能够在web网页上进行线上转账，实现模拟转账业务，又保障信息的保密，解决了用户信息存储泄露等不安全因素的问题，对线上转账的安全方面有十分重要的意义。

开发目标:

1、用户可实现登陆及注册功能。

2、用户可以进行查询和修改自己的信息包括余额、用户信息等。

3、用户可通过此系统客户端进行转账操作，服务器（后台）进行信息加密存储。

4、此系统在用户提出查询历史账单请求时可提供解密后相关账单信息。

5、对三大类加密算法进行对比研究，选择合适的算法结合使用。

6、系统操作界面具有美观简单，交互性好，易操作等优点。



数据库设计：

账户信息表（Account）：

字段名	数据类型	长度	说明	描述

ano	varchar	16	Primary key	账户账号

apwd	varchar	15	Not null	账户密码

amoney	float		Not null,default‘0’	账户余额

aname	varchar	16	Not null	用户名字

aId	varchar	18	Not null，unique	用户Id

asex	varchar	2	Not null	用户性别

atel	varchar	11	Not null	用户电话

amail	varchar	30	Not null	用户邮箱

address	varchar	50	Not null	用户地址


账单信息表（Bill）

字段名	数据类型 	长度	说明	描述

bno	int	10	Primary key,auto_increment	账单编号

boutno	varchar	16	Not null	付款账号

binno	varchar	16	Not null	收款账号

bname	varchar	16	Not null	收款人姓名

bmoney	varchar	16	Not null	转账金额

bdate	date		Not null	转账日期

bnote	varchar	64		备注
