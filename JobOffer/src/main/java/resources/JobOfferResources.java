package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import core.Corporation;
import core.IndustryType;
import core.JobOffer;
import core.OccupationType;
import dao.CorporationDao;
import dao.IndustryTypeDao;
import dao.JobOfferDao;
import dao.OccupationTypeDao;
import io.dropwizard.hibernate.UnitOfWork;
import parameter.CorporationRegisterParameter;
import parameter.JobOfferRegisterParameter;
import summary.JobOfferSummary;

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
	public List<JobOfferSummary> search(
			@QueryParam("industryTypeId") String industryTypeId, 
			@QueryParam("occupationTypeId") String occupationTypeId, 
			@QueryParam("freeWord") String freeWord) {
		
		String[] freeWordItem = freeWord.split("[ 　]+");
		
		System.out.println(freeWordItem.length);
		
		List<String> corporationIdList = loadCorporationIdFreeWordSearchResult(freeWordItem);
		
		List<JobOffer> jobOfferList = jobOfferDao.loadSearchResult(
				industryTypeId, occupationTypeId, freeWordItem, corporationIdList);
		
		List<JobOfferSummary> JobOfferSummaryList = createJobOfferSummaryList(jobOfferList);
		
		return JobOfferSummaryList;
		
	}
	
	/**
	 * 求人情報の登録処理を行うメソッド。(POST)
	 * 
	 * @param parameter 求人情報の登録パラメータ
	 * @return 求人ID
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@POST
	@Path("/job")
	@UnitOfWork
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response jobOfferRegister(@NotNull JobOfferRegisterParameter parameter) 
			throws JsonParseException, JsonMappingException, IOException {
		
		// JobOfferRegisterParameterクラスの内容をJobOfferクラスに変換する
		JobOffer jobOffer = toJobOffer(parameter);
		
		// DB登録処理を行い、登録した求人IDを取得する
		String responseMessage = jobOfferDao.create(jobOffer);
		
		// HTTPレスポンスと登録した企業IDを設定する
		Response res = Response.status(Status.OK).entity(responseMessage).build();
		
		return res;
		
	}
	
	/**
	 * 企業情報の登録を行うメソッド。（POST）
	 * 
	 * @param corporation 登録する企業情報
	 * @return 企業ID
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@POST
	@Path("/job/corporation")
	@UnitOfWork
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response corporationRegist(@NotNull CorporationRegisterParameter parameter) throws JsonParseException, JsonMappingException, IOException {
		
		// CorporationRegisterParameterクラスの内容をCorporationクラスに変換する
		Corporation corporation = toCorporation(parameter);
		
		// DB登録処理を行い、登録した企業IDを取得する
		String responseMessage = corporationDao.create(corporation);
		
		// HTTPレスポンスと登録した企業IDを設定する
		Response res = Response.status(Status.OK).entity(responseMessage).build();
		
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
	 * JobOfferRegisterParameter→JobOfferへの変換メソッド
	 * 
	 * @param parameter JobOfferRegisterParameterクラスの変数
	 * @return JobOfferクラスの変数
	 */
	private JobOffer toJobOffer(JobOfferRegisterParameter parameter) {
		
		return new JobOffer(
				parameter.getJobOfferId(), 
				parameter.getJobOfferName(), 
				parameter.getCorporationId(), 
				parameter.getIndustryTypeId(), 
				parameter.getOccupationTypeId(), 
				parameter.getCatchCopy(), 
				parameter.getJobOfferOverview());
	}
	
	/**
	 * CorporationRegisterParameter→Corporationの変換クラス
	 * 
	 * @param parameter CorporationRegisterParameterクラスの変数
	 * @return Corporationクラスの変数
	 */
	private Corporation toCorporation(CorporationRegisterParameter parameter) {
		
		return new Corporation(
				parameter.getCorporationId(), 
				parameter.getCorporationName());
	}
	
	private List<String> loadCorporationIdFreeWordSearchResult(String[] freeWordItem) {
		
		List<Corporation> corporationList = corporationDao.loadCorporationId(freeWordItem);
		
		List<String> corporationIdList = new ArrayList<String>();
		
		for (Corporation corporation : corporationList) {
			
			corporationIdList.add(corporation.getCorporationId());
		}
		
		return corporationIdList;
	}
	
	/**
	 * 検索結果を作成するメソッド
	 * JobOfferに存在しない項目の取得を行う
	 * 
	 * @param jobOfferList
	 * @return
	 */
	private List<JobOfferSummary> createJobOfferSummaryList(List<JobOffer> jobOfferList) {
		
		List<JobOfferSummary> jobOfferSummaryList = new ArrayList<JobOfferSummary>();
		
		if (jobOfferList.isEmpty()) {
			return jobOfferSummaryList;
		}
		
		for (JobOffer jobOffer : jobOfferList) {
			
			String corporationName = loadCorporationName(jobOffer.getCorporationId());
			String industryTypeName = loadIndustryTypeName(jobOffer.getIndustryTypeId());
			String occupationTypeName = loadOccupationTypeName(jobOffer.getOccupationTypeId());
			
			JobOfferSummary summary = createJobOfferSummary(
					jobOffer, corporationName, industryTypeName, occupationTypeName);
			
			jobOfferSummaryList.add(summary);
		}
		
		return jobOfferSummaryList;
	}
	
	private String loadCorporationName(String corporationId) {
		
		return corporationDao.loadCorporationName(corporationId);
	}
	
	private String loadIndustryTypeName(String industryTypeId) {
		
		return industryTypeDao.loadIndustryTypeName(industryTypeId);
	}
	
	private String loadOccupationTypeName(String occupationTypeId) {
		
		return occupationTypeDao.loadOccupationTypeName(occupationTypeId);
	}
	
	private JobOfferSummary createJobOfferSummary(
			JobOffer jobOffer, String corporationName, 
			String industryTypeName, String occupationTypeName) {
		
		return new JobOfferSummary(
				jobOffer.getJobOfferId(), 
				jobOffer.getJobOfferName(), 
				jobOffer.getCorporationId(), 
				corporationName, 
				jobOffer.getIndustryTypeId(), 
				industryTypeName, 
				jobOffer.getOccupationTypeId(),
				occupationTypeName, 
				jobOffer.getCatchCopy(), 
				jobOffer.getJobOfferOverview());
	}

}
