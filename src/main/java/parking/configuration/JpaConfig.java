package parking.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import parking.domain.AbstractEntity;

/**
 * 
 * @author gustavojotz
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "parking.repository")
@EntityScan(basePackageClasses = AbstractEntity.class)
public class JpaConfig {

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate5Module());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}

}
