# springcloud学习

## 2024.12.19

##### 基本都是复习内容，主要是一个项目的架构

###### 1.根据脑图文件引入了视频pom，但是一堆爆红，为什么呢？

因为pom中的<dependencyManagement>是用来管理denpendency的，并不会引入maven，也就是说不会帮你自动下载没有的maven，可以将<dependencyManagement>去掉，刷新maven让其下载，然后再加回来。当然这里不管他也是可以的，以后项目中如果用到了这个maven，他也会自动下载的，只是一大堆红色看着让人不舒服。

<dependencyManagement>放在父工程中，以后继承这个父工程的子工程就不需要定义版本了，但如果子工程引入了版本号，则子工程优先用自己的

###### 2.对于前后端互传对象的一些规范

通常前端传递给后端的数据中会夹带一些例如密码的隐私信息，我们不希望这些信息直接暴漏，所以接收前端传递的对象我们一般加上DTO编号，例如CustDTO。而传递回前端的数据我们一般加上VO编号，例如CustVO

DTO表示前后端交互需要封装的信息，VO是要展示的信息

###### 3.关于自动生成的Mapper方法insertSelective和insert区别

insertSelective方法不会保存null的属性，会使用数据库默认值，而insert会保存null的属性，不会使用数据库默认值

###### 4.关于mysql驱动报错问题

![image-20241219145415719](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219145415719.png)

如果这一行报错，首先要检查自己的mysql版本，如果是mysql5，则没有cj

然后要检查依赖，用这个依赖需要![image-20241219145522933](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219145522933.png)

而不是![image-20241219145534727](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219145534727.png)

###### 5.关于显示主程序错误

原因就是内部的bean管理不行，比如今天的错误就是因为mapperscan的引入错入，应该引入以tk开头的，并且没有在括号里写basepackages。

###### 6.swagger3

首先要引入这个包![image-20241219151701703](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219151701703.png)

常用注解：

@Tag标记controller作用    @Tag(name="这个配置类总体作用",description="具体的细节")

@Schema描述模型作用以及每个属性

@Operation描述方法引用     同@Tag

###### 7.swagger3的配置文件

配置文件复制即可，没必要自己会写，会修改相应的配置就行

###### 8.对于返回数据的时间格式处理

首先可以在时间类上添加注解：![image-20241219195036841](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219195036841.png)

其次可以在yml配置文件中配置（这里报错是因为上方有spring配置了，这里只是展示一下）

![image-20241219195302604](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219195302604.png)

###### 9.如何定义一个通用的枚举类

记住三步小口诀：举值-构造-遍历

![image-20241219200714533](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219200714533.png)

这些就是举值，就是举例一些值，报错是因为还没有构造枚举的模式

然后是构造：![image-20241219201050873](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219201050873.png)

这一步结束后就不会报错了，然后完善一下：![image-20241219201257583](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219201257583.png)

遍历的两种方法：

![image-20241219201838062](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241219201838062.png)

###### 10.关于给前端返回的对象

我们在Controller中返回给前端的应该是统一的对象，而不是一会返回String一会返回实例对象，所以我们应该封装一个统一的返回对象Result<T>

###### 11.全局异常的处理以及统一返回

## 2024.12.21

###### 1.postman中出现404错误，但是通过debug发现方法的调用都没有问题

那就要看看controller类的注解标注的究竟是@Controller还是@RestController，前者会出现404错误

###### 2.通用工具module的搭建

通过代码我们发现，PayDTO以及错误枚举、ResultData在每个module中都会用到，所以我们可以新建一个module来专门存放这些通用工具类

### consul

##### 3.为什么不用Eureka？

1.Eureka目前已经取消了更新，以后一定会被淘汰

2.Eureka的自我保护机制

3.主流的服务注册中心应该单独独立出来，与业务模块解耦合，而不应该与其他的微服务混在一起，说人话就是服务注册中心不应该与其他业务module创建在一起，类似tomcat一样独立的组件，开箱即用。

4.cloud阿里巴巴的崛起，Eureka只能进行服务的发现与注册

##### 4.凡技术，查官网，那我们该怎么记住官网地址呢？

通过规律我们发现，一般的技术官网都是以.io结尾的例如spring.io  redis.io。

##### 5.关于consul的下载

下载网址：https://developer.hashicorp.com/consul/install

下载对应版本：![image-20241221151432439](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241221151432439.png)

386代表x86的处理器架构，就是32位的

amd64代表x64的处理器架构，就是64位的

##### 6.consul的作用

服务的发现和配置的注册中心

##### 7.consul的安装以及运行

1.下载完毕后解压缩

2.在exe界面打开cmd：![image-20241221155616173](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241221155616173.png)

进入终端输入consul -version（32位是consul --version）:![image-20241221155659287](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241221155659287.png)

如果可以出现版本号，则表示下载正确

然后再改文件夹下使用命令启动consul： consul agent -dev:![image-20241221155950951](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241221155950951.png)

启动后可以使用localhost:8500访问consul首页

##### 8.consul的持久化：

为什么要进行持久化呢？因为我们在consul的服务注册中心写的key/value会随着consul的关闭或者电脑重启而消失，假如没有对其进行在写，会导致服务无法使用。

###### 方法一：全自动

注册consul为windows服务

###### 方法二：手动

每次启动都在后面加上 -data-dir 文件

##### 9.为什么consul服务中心使用8500呢？

在官网中明确写出了：

![image-20241222152334518](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222152334518.png)

##### 10.关于学习新技术：

![image-20241222152416123](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222152416123.png)

我在图中标记了三个东西，从上往下，我们可以学到理论、实战代码、bug查错

##### 11.服务端入住consul

1.改pom：

```maven
<!--SpringCloud consul discovery -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
```

2.改yml：添加

```yml
####Spring Cloud Consul for Service Discovery
spring:
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        service-name: ${spring.application.name}
```

这个配置是什么意思呢？意思就是我们的这个服务将会以"${spring.application.name}"作为名字，入驻到localhost:8500

3.主启动：

###### @EnableDiscoveryClient

在主启动类上添加@EnableDiscoveryClient注解，表示开启了服务发现

4.小细节

在入住consul的时候需要等一会才会变成绿对钩，因为需要发送心跳包给consul，consul验证后才能加入

##### 12.客户端入住consul

1.改pom

![image-20241222164608546](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222164608546.png)

```maven
<!--SpringCloud consul discovery -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
```

2.改yml

```yml
server:
  port: 80

spring:
  application:
    name: cloud-consumer-order
  ####Spring Cloud Consul for Service Discovery
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        prefer-ip-address: true #优先使用服务ip进行注册
        service-name: ${spring.application.name}
```

3.主启动类

加上@EnableDiscoveryClient注解

4.修改controller写死的东西：

![image-20241222153340883](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222153340883.png)

这样不管以后需要的服务怎么变动，都不怕。

###### 为什么明明没写错，缺报了500错误呢？

查看报错信息发现：无法找到地址

因为我们的consul天生支持负载均衡，如果通过微服务的名字调用，consul会默认你的后面有好多服务，所以需要修改RestTemplate类，添加注解：@LoadBalanced来开启负载均衡，将这个服务交给consul，consul才会放心的给他地址

###### @LoadBalanced

![image-20241222153627884](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222153627884.png)

##### 13.面试题：在微服务中你用过哪些服务注册中心，有什么区别？

主要从CAP角度回答

1.什么是CAP：

![image-20241222153831115](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222153831115.png)

CAP最多只能同时满足两个

![image-20241222153908230](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222153908230.png)

2.回到问题，说这三个就够了：

![image-20241222153954041](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222153954041.png)

3.那什么是AP，什么是CP呢？

![image-20241222154034590](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222154034590.png)

![image-20241222154044732](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222154044732.png)

##### 14.服务配置与刷新

什么是全局配置呢？举个例子就是老师通知学生，通知一次，所有学生就都知道了。

为什么要全局配置呢？一个项目的微服务是很多的，如果不使用全局配置，那以后我们如果想更换配置，比如更换数据库地址，就要一个一个修改，运维就不想干了。

那怎么配置呢？既然注册进consul，自然要遵守consul的规则

1.改pom

```maven
<!--SpringCloud consul config-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-config</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

2.在consul中建造我们的key/value:

这个文件的格式路径必须是config/xxxxx-(dev,prod)/data，分别对应开发模式，生产模式，不写就是默认模式

![image-20241222154905480](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222154905480.png)

![image-20241222154915757](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222154915757.png)

![image-20241222154923954](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222154923954.png)

![image-20241222154933383](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222154933383.png)

这就是我们的全局配置。

### LoadBalance

##### 15.什么是LoadBalance？

LoadBalance是我们微服务中“服务的调用和负载均衡”中的。

LoadBalance是用来完全代替Ribbon的。

###### 为什么要代替Ribbon呢？

1.Ribbon进入了维护模式，也就是不更新了，随着技术的升级，迟早会被淘汰

##### 16.loadbalance官网在哪里？

![image-20241222155332384](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222155332384.png)

目前是挂在了springcloud commons这个包下面

##### 17.负载均衡（LB）Load Balance 是什么？

简单来说就是将用户的请求平坦的分布在多个微服务上，以此来达到系统的HA（高可用），常见的负载均衡有Nginx，LVS，硬件F5等。

##### 18.spring-cloud-starter-loadbalance组件是什么

是SpringCloud官方提供的一个开源的、简单易用的**客户端负载均衡器**。相比于Ribbon，不仅支持RestTemplate，还支持WebClient（WebClient是Spring Web Flux中提供的功能，可以实现响应式异步请求）

##### 19.面试题：客户端负载与服务端负载有什么区别？

首先功能一定是一样的，都是为了实现负载均衡。

首先Nginx是服务器负载均衡，客户端的所有请求都会交给Nginx，由Nginx来实现转发请求，即负载均衡是由服务器实现的

loadbalance是客户端负载均衡（本地负载均衡），在调用微服务接口时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用技术。举个例子，当有很多学生围绕着老师问问题，这时候又来了一个新的学生想问问题，他一看老师很忙，就换个老师去问或者干脆不问了，老师就是服务器，学生就是客户端。



## 2024.12.22

#### 1.loadbalance的轮询机制：

我们在启动了8001与8002两个服务后，使用同一个请求测试：

第一次：

![image-20241222151601057](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222151601057.png)

第二次：

![image-20241222151613326](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222151613326.png)

多试几次发现竟然是一个8001一个8002**交替进行**，得证轮询机制

#### 2.loadbalance的负载均衡算法

loadbalance支持轮询、随机、自定义。

默认的算法是轮询算法。

#### 3.loadbalance的算法切换

修改RestTemplate类：

添加这么一行：

![image-20241222161200311](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222161200311.png)

新增Bean：

![image-20241222161438459](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222161438459.png)

### OpenFeign

#### 4.既然已经有了loadbalance，为什么还要OpenFeign？这两个如何选择？

Feign是一个声明式的Web服务客户端，可以使web服务客户端变得容易，只需要使用Feign创建一个接口并对其进行注解就可以。

###### @FeignClient

Feign也提供负载平衡的http客户端，由loadbalance同样的效果。

OpenFeign基本上就是当前微服务之间调用的实施标准

**目前主流就是使用OpenFeign。**

那为什么loadbalance不主流呢？

我们都知道，当我们想要调用一个服务端的controller时，需要有一个RestTemplate类，就像这样（服务端是Pay，客户端是Order，这里是Order调用Pay的controller）：

![image-20241222162949563](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222162949563.png)

那如果明天有个财务系统Money也要调用Pay呢？那Money的controller是不是也要有一个AutoWired的RestTemplate，那就又要自己实现一个RestTemplate类，就像这样：

![image-20241222163137796](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222163137796.png)

那后果是什么，当我们的微服务一旦变得很多，那是不是各个客户端的RestTemplate满天飞，不好管理；而且不统一不规范；再之，我们服务之间的调用遵循面向接口编程，也就是我们被调用的服务端最好根据解耦和面向接口的原则暴漏一些专门用来调用的接口。

这样我们的客户端和服务端直接就多了一层接口，而这层接口就是OpenFeign接口。

客户端直接调用OpenFeign接口即可，并且OpenFeign还继承了Loadbalance，可以在使用OpenFeign时提供http的负载均衡，也可以集成阿里巴巴的Sentinel来提供服务熔断、服务降级等功能。

而且OpenFeign与Loadbalance不同的是，通过OpenFeign只需要定义服务绑定接口且以声明式的方法，优雅而简单。

#### 5.那OpenFeign怎么玩呢

首先了解一下我们的架构：

![image-20241222164238389](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222164238389.png)

1.改pom

添加：

```maven
<!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

2.写yml

```yml
server:
  port: 80

spring:
  application:
    name: cloud-consumer-openfeign-order
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        prefer-ip-address: true
        service-name: ${spring.application.name}
```

3.主启动类

![image-20241222165134868](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222165134868.png)

###### @EnableFeignClients

添加注解@EnableFeignClients表示开启并激活OpenFeign

4.修改我们的通用commons模块，因为现在我们的客户端80是要通过接口找到我们的服务端，那也有可能其他的客户端有一天也要找这个服务端，所以干脆写在commons中

在自己的通用module引入依赖

```maven
<!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

5.在通用模块新建服务接口，并配备@FeignClient注解

![image-20241222192638951](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222192638951.png)

注意，这个value是要调用的服务端的服务在consul的名字：

![image-20241222192741720](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222192741720.png)

6.选择被调用的客户端对外暴漏的接口

加入我们要暴漏这两个服务：

![image-20241222192953879](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222192953879.png)

![image-20241222193016916](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222193016916.png)

那我们在接口处就要这样写：

![image-20241222193222523](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222193222523.png)

是不是发现了什么？我们发现，我们定义的接口除了没有方法体，其他的都和被调用的服务一样

一个小细节：![image-20241222194439957](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222194439957.png)

就算请求路径的形参名一样，也不能省略这个“id”，否则会有bug

7.在我们的客户端写对应的controller：

![image-20241222200424365](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222200424365.png)

这时候我们不需要写并注入那个RestTemplate类了，而是注入我们通用模版里的标注了@FeignClient注解的接口即可。

为什么我们自动注入能注入那个接口呢？因为我们引入了通用模版的pom

#### 6.OpenFeign的高级特性

##### 1.超时控制

OpenFeign客户端的默认超时时间是60秒，服务端如果处理超过规定时间会导致Feign客户端返回报错。

为了避免这种情况，有时候我们需要设置Feign客户端的超时控制，默认60秒太长或业务时间太短都不好。

一般我们在application.yml中设置两个超时时间：

connectTimeout连接超时时间

readTimeout请求处理时间

接下里就是修改超时时间：

有两种配置：全局配置、指定配置

###### 1.全局配置

全局配置就是所有服务的配置

在yml中添加：

```yml
spring:
  cloud:
    openfeign:
      client:
        config:
          default:
            #连接超时时间，单位ms
            connectTimeout: 3000
            #读取超时时间
            readTimeout: 3000
```

###### 2.指定配置：

```yml
spring:
  cloud:
    openfeign:
      client:
        config:
          "服务在consul上的名字":
            #连接超时时间，单位ms
            connectTimeout: 3000
            #读取超时时间
            readTimeout: 3000
```

假如指定的和全局的同时存在，则指定的优先。

##### 2.重试机制

重试机制默认是关闭了的

1.首先我们要确保我们有时间限制：

![image-20241222214537975](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222214537975.png)

2.创建Config配置文件并添加Bean：

![image-20241222214617417](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222214617417.png)

这是默认的不启动重试机制的样子

开启重试机制后：

![image-20241222214853231](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241222214853231.png)

什么意思呢？就是我们重试机制的厨师间隔时间是100ms，重试简最大间隔为1s，最大请求次数是3次，也就是最多重试2次。

##### 3.默认HttpClient修改

如果不做任何配置，OpenFeign默认是使用JDK自带的HttpURLConnection发送URL请求。

由于JDK自带的默认HttpURLConnection没有连接池、性能和效率低下，如果使用默认的，性能上是不够强的

所以使用Apache HttpClient5替换OpenFeign默认的HttpURLConnection。

1.调用者80引入pom：

```maven
<!-- httpclient5-->
<dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
    <version>5.3</version>
</dependency>
<!-- feign-hc5-->
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-hc5</artifactId>
    <version>13.1</version>
</dependency>
```

2.修改调用者80yml，添加：

```yml
#  Apache HttpClient5 配置开启
spring:
  cloud:
    openfeign:
      httpclient:
        hc5:
          enabled: true
```

##### 4.请求/相应压缩

##### 5.日志打印功能
