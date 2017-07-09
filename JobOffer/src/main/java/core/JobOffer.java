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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.jackson.JsonSnakeCase;

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
            		+ "AND "
            		+ "(job.jobOfferName LIKE :freeWord "
            		+ "OR job.corporation.corporationName LIKE :freeWord "
            		+ "OR job.catchCopy LIKE :freeWord "
            		+ "OR job.jobOfferOverview LIKE :freeWord) "
            		+ "ORDER BY job.jobOfferId "
        )
    }
)
public class JobOffer implements Serializable {
	
	/** 求人ID(pk) */
	@Id
	@Column(name = "jobOfferId", nullable = false)
	@NotEmpty(message = "求人IDが空です")
	@Size(min = 10, max = 10, message = "求人IDは10桁です")
	private String jobOfferId;
	
	/** 求人名 */
	@Column(name = "jobOfferName", nullable = false)
	@NotEmpty(message = "求人名が空です")
	@Size(max = 255, message = "求人名は255桁までです")
	private String jobOfferName;
	
	/** 企業ID */
	@Column(name = "corporationId", nullable = false)
	@NotEmpty(message = "企業IDが空です")
	@Size(min = 10, max = 10, message = "企業IDは10桁です")
	private String corporationId;
	
	/** 企業情報 */
	@ManyToOne(targetEntity = Corporation.class, fetch = FetchType.LAZY)
    @JoinColumn(name="corporationId", referencedColumnName="corporationId", insertable=false, updatable=false)
    private Corporation corporation;
	
	/** 業種ID */
	@Column(name = "industryTypeId", nullable = false)
	@NotEmpty(message = "業種IDが空です")
	@Size(min = 6, max = 6, message = "業種IDは6桁です")
	private String industryTypeId;
	
	/** 業種情報 */
	@ManyToOne(targetEntity = IndustryType.class, fetch = FetchType.LAZY)
    @JoinColumn(name="industryTypeId", referencedColumnName="industryTypeId", insertable=false, updatable=false)
    private IndustryType industryType;
	
	/** 職種ID */
	@Column(name = "occupationTypeId", nullable = false)
	@NotEmpty(message = "職種IDが空です")
	@Size(min = 6, max = 6, message = "職種IDは10桁です")
	private String occupationTypeId;
	
	/** 職種情報 */
	@ManyToOne(targetEntity = OccupationType.class, fetch = FetchType.LAZY)
    @JoinColumn(name="occupationTypeId", referencedColumnName="occupationTypeId", insertable=false, updatable=false)
    private OccupationType occupationType;
	
	/** キャッチコピー */
	@Column(name = "catchCopy", nullable = false)
	@NotEmpty(message = "キャッチコピーが空です")
	@Size(max = 255, message = "キャッチコピーは255桁までです")
	private String catchCopy;
	
	/** 概要 */
	@Column(name = "jobOfferOverview", nullable = false)
	@NotEmpty(message = "概要が空です")
	@Size(max = 500, message = "概要は500桁までです")
	private String jobOfferOverview;
	
	/** デフォルトコンストラクタ */
	public JobOffer() {
		
	}
	
	/** 登録処理用コンストラクタ */
	@JsonCreator
	public JobOffer(
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

	public String getJobOfferId() {
		return jobOfferId;
	}

	public String getJobOfferName() {
		return jobOfferName;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public String getIndustryTypeId() {
		return industryTypeId;
	}

	public IndustryType getIndustryType() {
		return industryType;
	}

	public String getOccupationTypeId() {
		return occupationTypeId;
	}

	public OccupationType getOccupationType() {
		return occupationType;
	}

	public String getCatchCopy() {
		return catchCopy;
	}

	public String getJobOfferOverview() {
		return jobOfferOverview;
	}

}
