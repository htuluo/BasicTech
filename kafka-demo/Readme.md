此demo为测试redis客户端jedis和lettuce的性能所做；

mvn clean verify进行打包，target目录下会生成microbenchmarks.jar，上传到服务器
分别执行java -jar microbenchmarks.jar com.llm.redis.LettuceSyncJmhTest.get、java -jar microbenchmarks.jar com.llm.redis.JedisJmhTest.get，即可开启执行