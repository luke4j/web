[TOC]


linux 启动 spring boot app

```$shell
nohup 命令

用途：不挂断地运行命令。

语法：nohup Command [ Arg … ][ & ]

描述：nohup 命令运行由 Command 参数和任何相关的 Arg 参数指定的命令，忽略所有挂断（SIGHUP）信号。在注销后使用 nohup 命令运行后台中的程序。要运行后台中的 nohup 命令，添加 &到命令的尾部。
```

例

```shell
nohup java -jar xxx.jar &

```
``这样执行后，nohup会把执行结果中的日志输出到当前文件夹下面的nohup.out文件中，通常情况下我们使用以上命令即可。 
  我们也可以手动指定一个参数来规定日志文件的输出地点，如：``
  
  ```sehll
  nohup java -jar xxx.jar > catalina.out  2>&1 &

```