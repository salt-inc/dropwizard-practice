package core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 職種情報クラス
 *
 * @author kazu
 *
 */
@Entity
@Table(name = "OCCUPATION_TYPE")
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

	public OccupationType(String occupationTypeId, String occupationTypeName) {
		this.occupationTypeId = occupationTypeId;
		this.occupationTypeName = occupationTypeName;
	}

	public String getOccupationTypeId() {
		return occupationTypeId;
	}

	public String getOccupationTypeName() {
		return occupationTypeName;
	}

}
