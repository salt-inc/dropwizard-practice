package core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
//	@Column(name = "companyId", nullable = false)
//	private String companyId;
	@ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY)
    @JoinColumn(name="companyId", referencedColumnName="companyId", insertable=false, updatable=false)
    private Company company;
	
	/** 業種情報 */
	@ManyToOne(targetEntity = IndustryType.class, fetch = FetchType.LAZY)
    @JoinColumn(name="industryTypeId", referencedColumnName="industryTypeId", insertable=false, updatable=false)
    private IndustryType industryType;
	
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
	
	public JobOffer(String jobOfferId, Company company, String jobOfferName, IndustryType industryType, 
			OccupationType occupationType, String catchCopy, String jobOfferOverview) {
		this.jobOfferId = jobOfferId;
		this.company = company;
		this.jobOfferName = jobOfferName;
		this.industryType = industryType;
		this.occupationType = occupationType;
		this.catchCopy = catchCopy;
		this.jobOfferOverview = jobOfferOverview;
	}

	public String getJobOfferId() {
		return jobOfferId;
	}

	public void setJobOfferId(String jobOfferId) {
		this.jobOfferId = jobOfferId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

}
