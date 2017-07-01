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
//@SecondaryTables(
//		{
//			@SecondaryTable(name = "INDUSTRY_TYPE", 
//					pkJoinColumns=@PrimaryKeyJoinColumn(name="industryTypeId", referencedColumnName="industryTypeId")), 
//			@SecondaryTable(name = "OCCUPATION_TYPE", 
//					pkJoinColumns=@PrimaryKeyJoinColumn(name="occupationTypId", referencedColumnName="occupationTypeId"))
//		}
//		)
@NamedQueries(
    {
        @NamedQuery(
            name = "core.JobOffer.searchResult",
            query = "SELECT job, job.industryType.industryTypeName "
            		+ "FROM JobOffer job "
//            		+ "INNER JOIN job.industryType ind "
//            		+ "ON job.industryTypeId = ind.industryTypeId "
//            		+ "INNER JOIN OCCUPATION_TYPE occupation "
//            		+ "ON job.occupationTypeId = occupation.occupationTypeId "
            		+ "WHERE "
            		+ "job.industryTypeId = "
            		+ "CASE WHEN :industryTypeId = '' THEN job.industryTypeId "
            		+ "ELSE :industryTypeId END "
            		+ "AND job.occupationTypeId = "
            		+ "CASE WHEN :occupationTypeId = '' THEN job.occupationTypeId "
            		+ "ELSE :occupationTypeId END "
        )
    }
)
public class JobOffer implements Serializable {
	
	/** 求人ID(pk) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jobOfferId", nullable = false)
	private String jobOfferId;
	
	/** 企業ID */
	@Column(name = "companyId", nullable = false)
	private String company;
	
	/** 求人名 */
	@Column(name = "jobOfferName", nullable = false)
	private String jobOfferName;
	
	/** 業種ID */
	@Column(name = "industryTypeId", nullable = false)
	private String industryTypeId;
	
	@ManyToOne(targetEntity = IndustryType.class, fetch = FetchType.LAZY)
    @JoinColumn(name="industryTypeId", referencedColumnName="industryTypeId", insertable=false, updatable=false)
    private IndustryType industryType;
	
	/** 職種ID */
	@Column(name = "occupationTypeId", nullable = false)
	private String occupationTypeId;
	
	@ManyToOne(targetEntity = OccupationType.class)
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
	
	public JobOffer(String jobOfferId, String companyId, String jobOfferName, String industryTypeId, 
			IndustryType industryType, String occupationTypeId, OccupationType occupationType, String catchCopy, 
			String jobOfferOverview) {
		this.jobOfferId = jobOfferId;
		this.company = companyId;
		this.jobOfferName = jobOfferName;
		this.industryTypeId = industryTypeId;
		this.industryType = industryType;
		this.occupationTypeId = occupationTypeId;
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

	public String getOccupationTypeId() {
		return occupationTypeId;
	}

	public void setOccupationTypeId(String occupationTypeId) {
		this.occupationTypeId = occupationTypeId;
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

}
