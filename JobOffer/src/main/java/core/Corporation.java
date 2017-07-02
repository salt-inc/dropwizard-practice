package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CORPORATION")
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
