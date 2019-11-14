### 命名规范:
~~~
方法名/变量名 尽量驼峰式命名 专有名称全大写
数据库: 表名/字段名 多个单词只间用_分隔
~~~
### 工具类:
~~~
统一使用Hutool包中的工具类,自定义的工具类写到common项目下的util包中
~~~
### 项目端口
~~~
apollo-configservice 31010
apollo-adminservice 31011


zuul  32001
eureka  32011 / 32012
project_1  32022

usercenter  32051
ordercenter  32061

scheduled  31089
rabbitmq  31099
~~~