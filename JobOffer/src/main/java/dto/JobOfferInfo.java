package dto;

/**
 * 求人情報
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class JobOfferInfo {
	
	/** 求人ID */
	private String jobOfferId;
	
	/** 企業ID */
	private String company;
	
	/** 求人名 */
	private String jobOfferName;
	
	/** 業種ID */
	private String industryTypeId;
	
	/** 業種 */
	private String industryTypeName;
	
	/** 職種ID */
	private String occupationTypeId;
	
	/** 職種 */
	private String occupationTypeName;
	
	/** キャッチコピー */
	private String catchCopy;
	
	/** 概要 */
	private String jobOfferOverview;

	public String getJobOfferId() {
		return jobOfferId;
	}

	public void setJobOfferId(String jobOfferId) {
		this.jobOfferId = jobOfferId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobOfferName() {
		return jobOfferName;
	}

	public void setJobOfferName(String jobOfferName) {
		this.jobOfferName = jobOfferName;
	}

	public String getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(String industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getIndustryTypeName() {
		return industryTypeName;
	}

	public void setIndustryTypeName(String industryTypeName) {
		this.industryTypeName = industryTypeName;
	}

	public String getOccupationTypeId() {
		return occupationTypeId;
	}

	public void setOccupationTypeId(String occupationTypeId) {
		this.occupationTypeId = occupationTypeId;
	}

	public String getOccupationTypeName() {
		return occupationTypeName;
	}

	public void setOccupationTypeName(String occupationTypeName) {
		this.occupationTypeName = occupationTypeName;
	}

	public String getCatchCopy() {
		return catchCopy;
	}

	public void setCatchCopy(String catchCopy) {
		this.catchCopy = catchCopy;
	}

	public String getJobOfferOverview() {
		return jobOfferOverview;
	}

	public void setJobOfferOverview(String jobOfferOverview) {
		this.jobOfferOverview = jobOfferOverview;
	}

}
