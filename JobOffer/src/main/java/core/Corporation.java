package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CORPORATION")
@NamedQueries(
	    {
	        @NamedQuery(
	            name = "core.Corporation.getAll",
	            query = "SELECT corporation "
	            		+ "FROM Corporation corporation "
	        )
	    }
	)
public class Corporation {
	
	/** 企業ID */
	@Id
	@Column(name = "corporationId", nullable = false)
	private String corporationId;
	
	/** 企業名 */
	@Column(name = "corporationName", nullable = false)
	private String corporationName;
	
	/** デフォルトコンストラクタ */
	public Corporation() {
		
	}
	
	/** 登録処理用コンストラクタ */
	public Corporation(String corporationId, String corporationName) {
		this.corporationId = corporationId;
		this.corporationName = corporationName;
	}

	public String getcorporationId() {
		return corporationId;
	}

	public void setcorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getcorporationName() {
		return corporationName;
	}

	public void setcorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

}
