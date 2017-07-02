package core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "INDUSTRY_TYPE")
public class IndustryType implements Serializable {
	
	/** 業種ID */
	@Id
	@Column(name = "industryTypeId", nullable = false)
	private String industryTypeId;
	
	/** 業種名 */
	@Column(name = "industryTypeName", nullable = false)
	private String industryTypeName;
	
	// デフォルトコンストラクタは必須！
	public IndustryType() {
		
	}
	
	public IndustryType(String industryTypeId, String industryTypeName) {
		this.industryTypeId = industryTypeId;
		this.industryTypeName = industryTypeName;
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

}
