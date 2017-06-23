package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponce {
	
	/** 業種ID */
	private String industryTypeId;
	
	/** 職種ID */
	private String occupationTypeId;
	
	/** フリーワード */
	final String freeWord;
	
	@JsonCreator
	public SearchResponce(String industryTypeId, String occupationTypeId, String freeWord) {
		this.industryTypeId = industryTypeId;
		this.occupationTypeId = occupationTypeId;
		this.freeWord = freeWord;
	}

	@JsonProperty
	public String getIndustryTypeId() {
		return industryTypeId;
	}

	@JsonProperty
	public String getOccupationTypeId() {
		return occupationTypeId;
	}

	@JsonProperty
	public String getFreeWord() {
		return freeWord;
	}

}
