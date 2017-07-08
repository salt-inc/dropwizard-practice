package resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import core.Corporation;
import core.IndustryType;
import core.JobOffer;
import core.OccupationType;
import dao.CorporationDao;
import dao.IndustryTypeDao;
import dao.JobOfferDao;
import dao.OccupationTypeDao;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * Resourceクラス。
 * 
 * @author Kazushige Yamaguchi
 */
@Path("/v1")
public class JobOfferResources {
	
	/** 求人情報Dao */
	private final JobOfferDao jobOfferDao;
	
	/** 企業情報Dao */
	private final CorporationDao corporationDao;
	
	/** 業種情報Dao */
	private final IndustryTypeDao industryTypeDao;
	
	/** 職種情報Dao */
	private final OccupationTypeDao occupationTypeDao;
	
	/**
	 * Resourceクラスのコンストラクタ。
	 * 各種Daoクラスを設定する。
	 * 
	 * @param jobOfferDao 求人情報Dao
	 * @param corporationDao 企業情報Dao
	 * @param industryTypeDao 業種情報Dao
	 * @param occupationTypeDao 職種情報Dao
	 */
	public JobOfferResources(
			JobOfferDao jobOfferDao, CorporationDao corporationDao, 
			IndustryTypeDao industryTypeDao, OccupationTypeDao occupationTypeDao) {
		this.jobOfferDao = jobOfferDao;
		this.corporationDao = corporationDao;
		this.industryTypeDao = industryTypeDao;
		this.occupationTypeDao = occupationTypeDao;
	}
	
	/**
	 * 検索処理を行い、結果を返すメソッド。（GET）
	 * JSON形式で返却される。
	 * 
	 * @param industryTypeId 業種ID
	 * @param occupationTypeId 職種ID
	 * @param freeWord フリーワード
	 * @return 検索結果
	 */
	@GET
	@Path("/job/list")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public List<JobOffer> search(
			@QueryParam("industryTypeId") String industryTypeId, 
			@QueryParam("occupationTypeId") String occupationTypeId, 
			@QueryParam("freeWord") String freeWord) {
		
		List<JobOffer> jobOfferList = jobOfferDao.loadSearchResult(industryTypeId, occupationTypeId, freeWord);
		
		return jobOfferList;
		
	}
	
	/**
	 * 求人情報の登録処理を行うメソッド。(POST)
	 * 
	 * @param jobOfferId 求人ID
	 * @param corporationId 企業ID
	 * @param jobOfferName 求人名
	 * @param industryTypeId 業種ID
	 * @param occupationTypeId 職種ID
	 * @param catchCopy キャッチコピー
	 * @param jobOfferOverview 求人概要
	 * @return 求人ID（正常）/エラーメッセージ（異常発生時）
	 */
	@POST
	@Path("/job")
	@UnitOfWork
	public Response jobOfferRegister(
			@FormParam("jobOfferId") String jobOfferId, 
			@FormParam("corporationId") String corporationId, 
			@FormParam("jobOfferName") String jobOfferName, 
			@FormParam("industryTypeId") String industryTypeId, 
			@FormParam("occupationTypeId") String occupationTypeId, 
			@FormParam("catchCopy") String catchCopy, 
			@FormParam("jobOfferOverview") String jobOfferOverview) {
		
		// 入力されていない項目がないかチェック
		checkEmpty(412, jobOfferId, corporationId, jobOfferName, industryTypeId, occupationTypeId, catchCopy, jobOfferOverview);
		
		// 求人IDの桁数チェック
		checkEqualsNumberLength(jobOfferId, "求人ID", 10, 412);
		
		// 企業IDの桁数チェック
		checkEqualsNumberLength(corporationId, "企業ID", 10, 412);
		
		// 求人名の桁数上限チェック
		checkOverNumberLength(jobOfferName, "求人名", 255, 412);
		
		// キャッチコピーの桁数上限チェック
		checkOverNumberLength(catchCopy, "キャッチコピー", 255, 412);
		
		// 求人概要の桁数上限チェック
		checkOverNumberLength(jobOfferOverview, "求人概要", 500, 412);
		
		// DB登録処理を行い、登録した求人IDを取得する
		String responseMessage = jobOfferDao.create(jobOfferId, corporationId, jobOfferName, industryTypeId, 
				occupationTypeId, catchCopy, jobOfferOverview);
		
		// HTTPレスポンスと登録した企業IDを設定する
		Response res = Response.status(200).entity(responseMessage).build();
		
		return res;
		
	}
	
	/**
	 * 企業情報の登録を行うメソッド。（POST）
	 * 
	 * @param corporationId 企業ID
	 * @param corporationName 企業名
	 * @return 企業ID（正常）/エラーメッセージ（異常発生時）
	 */
	@POST
	@Path("/job/corporation")
	@UnitOfWork
	public Response corporationRegist(@FormParam("corporationId") String corporationId, 
			@FormParam("corporationName") String corporationName) {
		
		// 入力されていない項目がないかチェック
		checkEmpty(412, corporationId, corporationName);
		
		// 企業IDの桁数チェック
		checkEqualsNumberLength(corporationId, "企業ID", 10, 412);
		
		// 企業名の桁数上限チェック
		checkOverNumberLength(corporationName, "企業名", 255, 412);
		
		// DB登録処理を行い、登録した企業IDを取得する
		String responseMessage = corporationDao.create(corporationId, corporationName);
		
		// HTTPレスポンスと登録した企業IDを設定する
		Response res = Response.status(200).entity(responseMessage).build();
		
		return res;
	}
	
	/**
	 * 全企業情報を取得するメソッド。（POST）
	 * 
	 * @return 企業情報リスト
	 */
	@POST
	@Path("/job/useJobOfferRegister/corporation")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public List<Corporation> loadAllCorporation() {
		
		List<Corporation> corporationList = corporationDao.loadAllData();
		
		return corporationList;
	}
	
	/**
	 * 全業種情報を取得するメソッド。（POST）
	 * 
	 * @return 業種情報リスト
	 */
	@POST
	@Path("/job/useJobOfferRegister/industry")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork()
	public List<IndustryType> loadAllIndustryType() {
		
		List<IndustryType> industryTypeList = industryTypeDao.loadAllData();
		
		return industryTypeList;
	}
	
	/**
	 * 全職種情報を取得するメソッド。（POST）
	 * 
	 * @return 職種情報リスト
	 */
	@POST
	@Path("/job/useJobOfferRegister/occupation")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public List<OccupationType> loadAllOccupationType() {
		
		List<OccupationType> OccupationTypeList = occupationTypeDao.loadAllData();
		
		return OccupationTypeList;
	}
	
	/**
	 * カラムの長さチェックメソッド。
	 * 長さが0の場合、エラー処理を行う。
	 * 
	 * @param HttpStatus エラー発生時のHTTPレスポンス
	 * @param column カラム（可変長引数）
	 */
	private void checkEmpty(int HttpStatus, String... column) {
		
		for (String col : column) {
			
			// カラムに何も入っていない場合
			if (col.isEmpty()) {
				
				// HTTPレスポンスとメッセージを設定する
				Response res = Response.status(HttpStatus).entity("入力されていない項目があります").build();
				throw new WebApplicationException(res);
			}
		}
	}
	
	/**
	 * 桁数一致チェックメソッド。
	 * 
	 * @param column カラム
	 * @param columnName カラム名
	 * @param columnLength カラムの長さ
	 * @param httpStatus エラー発生時のHTTPレスポンス
	 */
	private void checkEqualsNumberLength(String column, String columnName, int columnLength, int httpStatus) {
		
		// カラムの桁数が指定の桁数でない場合
		if (column.length() != columnLength) {
			
			// エラー発生時のレスポンスメッセージ
			String responseMessage = columnName + "は" + columnLength + "桁です。";
			
			// レスポンスを作成し、WebApplicationExceptionを発生させる
			Response res = Response.status(httpStatus).entity(responseMessage).build();
			throw new WebApplicationException(res);
		}
	}
	
	/**
	 * 桁数上限チェックメソッド。
	 * 
	 * @param column カラム
	 * @param columnName カラム名
	 * @param columnMaxLength カラムの桁数上限値
	 * @param httpStatus エラー発生時のHTTPレスポンス
	 */
	private void checkOverNumberLength(String column, String columnName, int columnMaxLength, int httpStatus) {
		
		// カラムの桁数が上限を超えている場合
		if (columnMaxLength < column.length()) {
			
			// エラー発生時のレスポンスメッセージ
			String responseMessage = columnName + "は" + columnMaxLength + "桁までです。";
			
			// レスポンスを作成し、WebApplicationExceptionを発生させる
			Response res = Response.status(httpStatus).entity(responseMessage).build();
			throw new WebApplicationException(res);
		}
	}

}
