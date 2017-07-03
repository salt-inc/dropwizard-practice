package resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
	public JobOfferResources(JobOfferDao jobOfferDao, CorporationDao corporationDao, 
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
		
		List<JobOffer> jobOfferList = jobOfferDao.getSearchResult(industryTypeId, occupationTypeId, freeWord);
		
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
	 * @return 登録した求人情報
	 */
	@POST
	@Path("/job")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public JobOffer jobOfferRegister(
			@FormParam("jobOfferId") String jobOfferId, 
			@FormParam("corporationId") String corporationId, 
			@FormParam("jobOfferName") String jobOfferName, 
			@FormParam("industryTypeId") String industryTypeId, 
			@FormParam("occupationTypeId") String occupationTypeId, 
			@FormParam("catchCopy") String catchCopy, 
			@FormParam("jobOfferOverview") String jobOfferOverview) {
		
		JobOffer jobOffer = jobOfferDao.create(jobOfferId, corporationId, jobOfferName, industryTypeId, 
				occupationTypeId, catchCopy, jobOfferOverview);
		
		return jobOffer;
		
	}
	
	/**
	 * 企業情報の登録を行うメソッド。（POST）
	 * 
	 * @param corporationId 企業ID
	 * @param corporationName 企業名
	 * @return 登録した企業情報
	 */
	@POST
	@Path("/job/corporation")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public Corporation corporationRegist(@FormParam("corporationId") String corporationId, 
			@FormParam("corporationName") String corporationName) {
		
		Corporation corporation = corporationDao.create(corporationId, corporationName);
		
		return corporation;
	}
	
	/**
	 * 全業種情報を取得するメソッド。（POST）
	 * 
	 * @return 業種情報リスト
	 */
	@POST
	@Path("/job/useJobOfferRegister/industry")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public List<IndustryType> getAllIndustryType() {
		
		List<IndustryType> IndustryTypeList = industryTypeDao.getAllIndustryType();
		
		return IndustryTypeList;
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
	public List<OccupationType> getAllOccupationType() {
		
		List<OccupationType> OccupationTypeList = occupationTypeDao.getAllOccupationType();
		
		return OccupationTypeList;
	}

}
