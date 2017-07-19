package core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "CORPORATION")
public class Corporation implements Serializable {
	
	/** 企業ID */
	@Id
	@Column(name = "corporationId", nullable = false)
	@NotEmpty(message = "企業IDが空です")
	@Size(min = 10, max = 10, message = "企業IDは10桁です")
	private String corporationId;
	
	/** 企業名 */
	@Column(name = "corporationName", nullable = false)
	@NotEmpty(message = "企業名が空です")
	@Size(max = 255, message = "企業名は255桁までです")
	private String corporationName;
	
	/** デフォルトコンストラクタ */
	public Corporation() {
		
	}
	
	/** 登録処理用コンストラクタ */
	public Corporation(String corporationId, String corporationName) {
		this.corporationId = corporationId;
		this.corporationName = corporationName;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public String getCorporationName() {
		return corporationName;
	}

}
