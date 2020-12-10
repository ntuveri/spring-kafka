# spring-kafka
Spring Cloud Stream demo app inspired by http://www.mokabyte.it/2018/10/microservizikafka/

List topics  
`kafka-topics.sh --bootstrap-server localhost:9092 --list`

Produce messages to a topic  
`kafka-console-producer.sh --topic Stocks --bootstrap-server localhost:9092`

Consume messages to a topic  
`kafka-console-consumer.sh --topic Quotes --from-beginning --bootstrap-server localhost:9092`