package core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 求人情報
 * 
 * @author Kazushige Yamaguchi
 *
 */
@Entity
@Table(name = "JOB_OFFER")
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
	
	/** 業種ID */
	@Column(name = "industryTypeId", nullable = false)
	@NotEmpty(message = "業種IDが空です")
	@Size(min = 6, max = 6, message = "業種IDは6桁です")
	private String industryTypeId;
	
	/** 職種ID */
	@Column(name = "occupationTypeId", nullable = false)
	@NotEmpty(message = "職種IDが空です")
	@Size(min = 6, max = 6, message = "職種IDは10桁です")
	private String occupationTypeId;
	
	/** キャッチコピー */
	@Column(name = "catchCopy", nullable = false)
	@NotEmpty(message = "キャッチコピーが空です")
	@Size(max = 255, message = "キャッチコピーは255桁までです")
	private String catchCopy;
	
	/** 概要 */
	@Column(name = "jobOfferOverview", nullable = true)
	@Size(max = 500, message = "概要は500桁までです")
	private String jobOfferOverview;
	
	/** デフォルトコンストラクタ */
	public JobOffer() {
		
	}
	
	/** 登録処理用コンストラクタ */
	public JobOffer(
			String jobOfferId, 
			String jobOfferName, 
			String corporationId, 
			String industryTypeId, 
			String occupationTypeId, 
			String catchCopy, 
			String jobOfferOverview) {
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

	public String getIndustryTypeId() {
		return industryTypeId;
	}

	public String getOccupationTypeId() {
		return occupationTypeId;
	}

	public String getCatchCopy() {
		return catchCopy;
	}

	public String getJobOfferOverview() {
		return jobOfferOverview;
	}

}
