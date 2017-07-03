package core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 職種情報クラス
 * 
 * @author kazu
 *
 */
@Entity
@Table(name = "OCCUPATION_TYPE")
@NamedQueries(
	    {
	        @NamedQuery(
	            name = "core.OccupationType.getAll",
	            query = "SELECT occupation "
	            		+ "FROM OccupationType occupation "
	        )
	    }
	)
public class OccupationType implements Serializable {
	
	/** 職種ID */
	@Id
	@Column(name = "occupationTypeId", nullable = false)
	private String occupationTypeId;
	
	/** 職種名 */
	@Column(name = "occupationTypeName", nullable = false)
	private String occupationTypeName;
	
	/** デフォルトコンストラクタ */
	public OccupationType() {
		
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

}
