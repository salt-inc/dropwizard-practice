package configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class JobOfferConfiguration extends Configuration {
	
	@JsonProperty
	private String template;

}
