package core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 求人情報
 * 
 * @author Kazushige Yamaguchi
 *
 */
@Entity
@Table(name = "JOB_OFFER")
@NamedQueries(
    {
        @NamedQuery(
            name = "core.JobOffer.searchResult",
            query = "SELECT job "
            		+ "FROM JobOffer job "
            		+ "WHERE "
            		+ "job.industryType.industryTypeId = "
            		+ "CASE WHEN :industryTypeId = '' THEN job.industryType.industryTypeId "
            		+ "ELSE :industryTypeId END "
            		+ "AND job.occupationType.occupationTypeId = "
            		+ "CASE WHEN :occupationTypeId = '' THEN job.occupationType.occupationTypeId "
            		+ "ELSE :occupationTypeId END "
        )
    }
)
public class JobOffer implements Serializable {
	
	/** 求人ID(pk) */
	@Id
	@Column(name = "jobOfferId", nullable = false)
	private String jobOfferId;
	
	/** 求人名 */
	@Column(name = "jobOfferName", nullable = false)
	private String jobOfferName;
	
	/** 企業ID */
	@Column(name = "corporationId", nullable = false)
	private String corporationId;
	
	/** 企業情報 */
	@ManyToOne(targetEntity = Corporation.class, fetch = FetchType.LAZY)
    @JoinColumn(name="corporationId", referencedColumnName="corporationId", insertable=false, updatable=false)
    private Corporation corporation;
	
	/** 業種ID */
	@Column(name = "industryTypeId", nullable = false)
	private String industryTypeId;
	
	/** 業種情報 */
	@ManyToOne(targetEntity = IndustryType.class, fetch = FetchType.LAZY)
    @JoinColumn(name="industryTypeId", referencedColumnName="industryTypeId", insertable=false, updatable=false)
    private IndustryType industryType;
	
	/** 職種ID */
	@Column(name = "occupationTypeId", nullable = false)
	private String occupationTypeId;
	
	/** 職種情報 */
	@ManyToOne(targetEntity = OccupationType.class, fetch = FetchType.LAZY)
    @JoinColumn(name="occupationTypeId", referencedColumnName="occupationTypeId", insertable=false, updatable=false)
    private OccupationType occupationType;
	
	/** キャッチコピー */
	@Column(name = "catchCopy", nullable = false)
	private String catchCopy;
	
	/** 概要 */
	@Column(name = "jobOfferOverview", nullable = false)
	private String jobOfferOverview;
	
	public JobOffer() {
		
	}
	
	/** 検索処理用コンストラクタ */
	public JobOffer(String jobOfferId, Corporation corporation, String jobOfferName, IndustryType industryType, 
			OccupationType occupationType, String catchCopy, String jobOfferOverview) {
		this.jobOfferId = jobOfferId;
		this.corporation = corporation;
		this.jobOfferName = jobOfferName;
		this.industryType = industryType;
		this.occupationType = occupationType;
		this.catchCopy = catchCopy;
		this.jobOfferOverview = jobOfferOverview;
	}
	
	/** 登録処理用コンストラクタ */
	public JobOffer(String jobOfferId,String corporationId, String jobOfferName, String industryTypeId, 
			String occupationTypeId, String catchCopy, String jobOfferOverview) {
		this.jobOfferId = jobOfferId;
		this.corporationId = corporationId;
		this.jobOfferName = jobOfferName;
		this.industryTypeId = industryTypeId;
		this.occupationTypeId = occupationTypeId;
		this.catchCopy = catchCopy;
		this.jobOfferOverview = jobOfferOverview;
	}

	public String getJobOfferId() {
		return jobOfferId;
	}

	public void setJobOfferId(String jobOfferId) {
		this.jobOfferId = jobOfferId;
	}

	public Corporation getcorporation() {
		return corporation;
	}

	public void setcorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public String getJobOfferName() {
		return jobOfferName;
	}

	public void setJobOfferName(String jobOfferName) {
		this.jobOfferName = jobOfferName;
	}

	public IndustryType getIndustryType() {
		return industryType;
	}

	public void setIndustryType(IndustryType industryType) {
		this.industryType = industryType;
	}

	public OccupationType getOccupationType() {
		return occupationType;
	}

	public void setOccupationType(OccupationType occupationType) {
		this.occupationType = occupationType;
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

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public void setIndustryTypeId(String industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public void setOccupationTypeId(String occupationTypeId) {
		this.occupationTypeId = occupationTypeId;
	}

}
