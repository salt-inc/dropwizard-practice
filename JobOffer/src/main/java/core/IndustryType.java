package core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INDUSTRY_TYPE")
@NamedQueries(
	    {
	        @NamedQuery(
	            name = "core.IndustryType.searchResult",
	            query = "SELECT industry "
	            		+ "FROM IndustryType industry "
	            		+ "WHERE "
	            		+ "industry.industryTypeId = :industryTypeId "
	        )
	    }
	)
public class IndustryType implements Serializable {
	
	/** 業種ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "industryTypeId", nullable = false)
	private String industryTypeId;
	
	/** 業種名 */
	@Column(name = "industryTypeName", nullable = false)
	private String industryTypeName;
	
//	@OneToMany(mappedBy="industryType", fetch = FetchType.LAZY)
//	@JoinColumn(name="industryTypeId")
//	private List<JobOffer> jobOfferList;
	
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
