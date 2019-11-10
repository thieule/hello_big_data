import java.util.Properties
import org.apache.kafka.clients.producer._
object Producer {
  def main(args: Array[String]): Unit = {
    writeToKafka("test02")
  }
  def writeToKafka(topic: String): Unit = {
    val producer = new KafkaProducer[String, String](AppConfiguration.getKafkaProp)
    val record = new ProducerRecord[String, String](topic, "key", "test")
    producer.send(record)
    producer.close()
  }
}