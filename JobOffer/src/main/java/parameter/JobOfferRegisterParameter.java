package parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 求人情報登録時の入力パラメータ
 * 
 * @author Kazushige Yamaguchi
 */
public class JobOfferRegisterParameter {
	
	/** 求人ID */
	private String jobOfferId;
	
	/** 求人名 */
	private String jobOfferName;
	
	/** 企業ID */
	private String corporationId;
	
	/** 業種ID */
	private String industryTypeId;
	
	/** 職種ID */
	private String occupationTypeId;
	
	/** キャッチコピー */
	private String catchCopy;
	
	/** 概要 */
	private String jobOfferOverview;
	
	/** デフォルトコンストラクタ */
	public JobOfferRegisterParameter() {
		
	}
	
	/** コンストラクタ */
	@JsonCreator
	public JobOfferRegisterParameter(
			@JsonProperty("jobOfferId") String jobOfferId, 
			@JsonProperty("jobOfferName") String jobOfferName, 
			@JsonProperty("corporationId") String corporationId, 
			@JsonProperty("industryTypeId") String industryTypeId, 
			@JsonProperty("occupationTypeId") String occupationTypeId, 
			@JsonProperty("catchCopy") String catchCopy, 
			@JsonProperty("jobOfferOverview") String jobOfferOverview) {
		this.jobOfferId = jobOfferId;
		this.corporationId = corporationId;
		this.jobOfferName = jobOfferName;
		this.industryTypeId = industryTypeId;
		this.occupationTypeId = occupationTypeId;
		this.catchCopy = catchCopy;
		this.jobOfferOverview = jobOfferOverview;
	}

	@JsonProperty("jobOfferId")
	public String getJobOfferId() {
		return jobOfferId;
	}

	@JsonProperty("jobOfferName")
	public String getJobOfferName() {
		return jobOfferName;
	}

	@JsonProperty("corporationId")
	public String getCorporationId() {
		return corporationId;
	}

	@JsonProperty("industryTypeId")
	public String getIndustryTypeId() {
		return industryTypeId;
	}

	@JsonProperty("occupationTypeId")
	public String getOccupationTypeId() {
		return occupationTypeId;
	}

	@JsonProperty("catchCopy")
	public String getCatchCopy() {
		return catchCopy;
	}

	@JsonProperty("jobOfferOverview")
	public String getJobOfferOverview() {
		return jobOfferOverview;
	}

}
