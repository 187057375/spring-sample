package cn.birdstudio.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {
	@Bean
	public ProducerFactory<String, Map<String, Object>> producerFactory() {
		DefaultKafkaProducerFactory<String, Map<String, Object>> producerFactory = new DefaultKafkaProducerFactory<>(
				producerConfigs());
		producerFactory.setTransactionIdPrefix("trans");
		return producerFactory;
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.1.168:9092");
		props.put(ProducerConfig.RETRIES_CONFIG, 2);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return props;
	}

	@Bean
	public KafkaTemplate<String, Map<String, Object>> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
