import java.util
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties
import scala.collection.JavaConverters._
object Consumer {
  def main(args: Array[String]): Unit = {
    CassandraDB.runDB();
    consumeFromKafka("test02")
  }
  def consumeFromKafka(topic: String) = {
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](AppConfiguration.getKafkaProp)
    consumer.subscribe(util.Arrays.asList(topic))
    while (true) {
      val record = consumer.poll(1000).asScala
      for (data <- record.iterator)
        println(data.value())
    }
  }

}