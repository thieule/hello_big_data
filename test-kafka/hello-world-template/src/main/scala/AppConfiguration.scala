import java.util
import java.util.Properties
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration.Duration

object AppConfiguration {
  val config = ConfigFactory.load()

  // Kafka Config
  val kafkaTopic=config.getString("kafka.topic")
  val dbHost = config.getString("database.host")
  val dbUser = config.getString("database.user")
  val dbPassword = config.getString("database.password")


  //Define kafka properties
  def getKafkaProp():Properties={
    // create instance for properties to access producer configs
    val props = new Properties()
    //Assign localhost id
    props.put("bootstrap.servers", "localhost:9092")
    //Set acknowledgements for producer requests.
    props.put("acks", "all")
    //If the request fails, the producer can automatically retry,
    props.put("retries", 0)
    //Specify buffer size in config
    props.put("batch.size", 16384)
    //Reduce the no of requests less than 0
    props.put("linger.ms", 1)
    //The buffer.memory controls the total amount of memory available to the producer for buffering.
    props.put("buffer.memory", 33554432)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("auto.offset.reset", "latest")
    props.put("group.id", "consumer-group")
    return props
  }
}
