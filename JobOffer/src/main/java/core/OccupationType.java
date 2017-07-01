package core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OCCUPATION_TYPE")
public class OccupationType implements Serializable {
	
	/** 職種ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "occupationTypeId", nullable = false)
	private String occupationTypeId;
	
	/** 職種名 */
	@Column(name = "occupationTypeName", nullable = false)
	private String occupationTypeName;
	
//	@OneToMany(mappedBy="occupationType")
//	@JoinColumn(name="occupationTypeId")
//	private List<JobOffer> jobOfferList;

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

}
