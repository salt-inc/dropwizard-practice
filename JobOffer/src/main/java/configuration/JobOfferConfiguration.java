package configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class JobOfferConfiguration extends Configuration {
	
	/** 業種ID */
	private String industryTypeId;
	
	/** 職種ID */
	private String occupationTypeId;
	
	/** キャッチコピー */
	private String catchCopy;
	
	@Valid
	@NotNull
	private HttpClientConfiguration httpClient = new HttpClientConfiguration();
	
	@Valid
	@NotNull
	@JsonProperty("database")
	private DataSourceFactory database = new DataSourceFactory();

	@JsonProperty
	public String getIndustryTypeId() {
		return industryTypeId;
	}

	@JsonProperty
	public void setIndustryTypeId(String industryTypeId) {
		this.industryTypeId = industryTypeId;
	}
	
	@JsonProperty
	public String getOccupationTypeId() {
		System.out.println("getOccupationTypeId起動");
		return occupationTypeId;
	}

	@JsonProperty
	public void setOccupationTypeId(String occupationTypeId) {
		System.out.println("setOccupationTypeId起動：" + occupationTypeId);
		this.occupationTypeId = occupationTypeId;
	}

	@JsonProperty
	public String getCatchCopy() {
		return catchCopy;
	}

	@JsonProperty
	public void setCatchCopy(String catchCopy) {
		this.catchCopy = catchCopy;
	}
	
	@JsonProperty("httpClient")
    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }
	
	@JsonProperty("httpClient")
    public void setHttpClientConfiguration(HttpClientConfiguration httpClient) {
        this.httpClient = httpClient;
    }
	
	public DataSourceFactory getDataSourceFactory() {
        return database;
    }
	
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }

}
