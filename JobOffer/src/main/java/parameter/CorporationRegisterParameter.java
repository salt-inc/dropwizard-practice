package parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 企業情報登録時の入力パラメータ
 * 
 * @author Kazushige Yamaguchi
 */
public class CorporationRegisterParameter {
	
	/** 企業ID */
	private String corporationId;

	/** 企業名 */
	private String corporationName;
	
	/** デフォルトコンストラクタ */
	public CorporationRegisterParameter() {
		
	}
	
	/** 登録処理用コンストラクタ */
	@JsonCreator
	public CorporationRegisterParameter(
			@JsonProperty("corporationId") String corporationId,
			@JsonProperty("corporationName") String corporationName) {
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
