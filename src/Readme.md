## Binance Service

### Use case:
+ fetch current order book ask and bid prices from binance
#### Technologies
+ spring-boot
+ spring-web

#### Setup
+ clone repository: `$ git clone https://github.com/rafal-banasiewicz/binance-data-service.git`

+ run it on your computer using any IDE e.g. IntelliJ
+ get data under http://localhost:8082/orderBook endpoint (only btc and eth implemented due to changes in binance websocket)
+ get data under http://localhost:8082/orderBook/{symbol} endpoint