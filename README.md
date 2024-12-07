### 基于SpringBoot + Vue的停车场管理系统.
 
#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios
报表：Spread.js

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件) + SpreadJS

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok

>此项目的研究内容主要是建立基于Web的停车场管理系统，该系统面向停车场管理员、停车场用户，不同的用户登录系统获得的权限不同，使用的功能也不同，获取的信息也有所差异。

停车场管理员可以通过系统登录并管理停车场的日常运营，包括车辆进出管理、停车位管理、收费管理等。停车场工作人员主要负责车辆进出管理，监控停车场的车辆进出情况，使用系统进行车辆识别、放行、收费等操作，且工作人员可以实时查看停车场的停车位情况，并在需要时进行停车位的调整和管理。停车场用户功能有通过系统注册账号并登录，进行停车预订、支付等操；可以通过系统查询停车场的空闲停车位信息，并进行预订操作，选择停车场和停车位，预定停车时间；系统根据用户停车时间和停车场收费标准计算停车费用，用户可以通过系统选择支付方式进行支付。
具体如下：
1.	用户管理：
在系统信息管理中，包括添加角色信息、管理角色信息、添加用户信息、管理用户信息、管理角色信息和管理用户信息模块可以对角色和用户进行查询、编辑和删除；会在其中设置员工的信息和权限等；注册和登录：用户可以通过注册账户并登录系统来访问其功能。
个人资料管理：用户可以编辑和更新其个人信息，如联系信息、车辆信息等。
2.	停车位管理：
主要包括添加车位信息、管理车位信息，管理车位信息模块可以对车主车位信息进行查询、编辑和删除；
停车位分配：管理人员可以将停车位分配给用户，或者系统可以根据用户需求自动分配停车位。
实时停车位状态：用户可以查看停车场内实时的停车位情况，以便选择空余的停车位。
3.	预订和付款：
预订停车位：用户可以提前预订停车位，确保有空位可用。
付款系统：用户可以使用在线支付或其他支付方式支付停车费用。
4.	进出管理：
入场/出场记录：系统记录用户车辆的进出时间，并计算停车费用。
固定车主停车管理中，可对车主的停车信息进行查询和删除；在临时车辆信息管理中，可对临时车主的停车信息进行查询和删除。
5.	费用管理：
主要包括添加收费信息、管理收费信息。可以选择是月用户还是年用户。相应的，管理收费信息可以对车主的费用进行一个收费，可以对车主的收费信息进行查询和删除；
6.	报告和统计：
停车数据分析：生成停车场使用情况的报告和统计数据，如停车位利用率、繁忙时段等。
收入报表：生成停车场的收入报表，包括总收入、各种费用的收入比例等。
7.	通知和提醒：
预约提醒：向用户发送预约成功和即将到期的提醒。
违规提醒：对于超时停车或其他违规行为，向用户发送提醒通知。
8.	打印管理：对所需要打印的信息进行打印处理，包括对固定车主和临时车主相关信息进行打印和取票机的打印。


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 默认后台账户密码
[管理员]
admin
1234qwer

[用户]
fank
1234qwer

## 项目截图

|  |  |
|---------------------|---------------------|
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428797789.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428952843.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710429285008.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428939320.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710429236895.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428922060.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710429202622.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428910448.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710429139032.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428898988.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710429110913.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428874569.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710429081402.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428843986.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710429014212.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428829118.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710429001447.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428812516.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1710428974850.jpg) | 


#### 演示视频

暂无

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.黑奴价格

> 项目部署调试不好包退！功能逻辑没讲明白包退！

#### 其它资源

[2024年答辩顺利通过](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年答辩顺利通过](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年答辩通过率100%](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)

#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)
