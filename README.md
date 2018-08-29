# springcloud 
# springcloud-parent
#集成LCN控制微服务之间的事物时需要依赖于TxManager以及redis服务
#测试LCN事物控制时仅需要启动springcloud，springcloud-Eureka，tx-manager，springcloud-product，springcloud-order，访问链接为http://localhost:3334/addOrderInfo?name=B&amount=74 ,初始化sql在springcloud-product项目下面的resources/ddl/init.sql
