package summary;

public class JobOfferSummary {
	
	/** 求人ID */
	private String jobOfferId;
	
	/** 求人名 */
	private String jobOfferName;
	
	/** 企業ID */
	private String corporationId;
	
	/** 企業名 */
	private String corporationName;
	
	/** 業種ID */
	private String industryTypeId;
	
	/** 業種名 */
	private String industryTypeName;
	
	/** 職種ID */
	private String occupationTypeId;
	
	/** 職種名 */
	private String occupationTypeName;
	
	/** キャッチコピー */
	private String catchCopy;
	
	/** 概要 */
	private String jobOfferOverview;
	
	public JobOfferSummary() {
		
	}
	
	public JobOfferSummary(
			String jobOfferId, 
			String jobOfferName, 
			String corporationId,
			String corporationName, 
			String industryTypeId, 
			String industryTypeName, 
			String occupationTypeId, 
			String occupationTypeName, 
			String catchCopy, 
			String jobOfferOverview) {
		this.jobOfferId = jobOfferId;
		this.corporationId = corporationId;
		this.jobOfferName = jobOfferName;
		this.corporationName = corporationName;
		this.industryTypeId = industryTypeId;
		this.industryTypeName = industryTypeName;
		this.occupationTypeId = occupationTypeId;
		this.occupationTypeName = occupationTypeName;
		this.catchCopy = catchCopy;
		this.jobOfferOverview = jobOfferOverview;
	}

	public String getJobOfferId() {
		return jobOfferId;
	}

	public String getJobOfferName() {
		return jobOfferName;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public String getIndustryTypeId() {
		return industryTypeId;
	}

	public String getIndustryTypeName() {
		return industryTypeName;
	}

	public String getOccupationTypeId() {
		return occupationTypeId;
	}

	public String getOccupationTypeName() {
		return occupationTypeName;
	}

	public String getCatchCopy() {
		return catchCopy;
	}

	public String getJobOfferOverview() {
		return jobOfferOverview;
	}

}
