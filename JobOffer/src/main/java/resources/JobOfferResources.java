package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import views.JobOfferView;

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

	/** 業種ID（検索パラメータ） */
	private String industryTypeId;

	/** 職種ID（検索パラメータ） */
	private String occupationTypeId;

	/** フリーワード（検索パラメータ） */
	private String freeWord;

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
	 * 検索画面を表示する
	 *
	 * @return
	 */
	@GET
	@Path("/job/list/top")
	@Produces(MediaType.TEXT_HTML)
	public JobOfferView jobOfferView() {
		System.out.println("JobOfferViewの確認");
		return new JobOfferView("jobOffer.ftl");
	}

	/**
	 * 検索処理を行い、結果を返すメソッド。（GET）
	 * JSON形式で返却される。
	 *
	 * @param industryTypeId 業種ID
	 * @param occupationTypeId 職種ID
	 * @param freeWord フリーワード
	 * @param pageCount 表示するページ
	 * @return 検索結果
	 */
	@GET
	@Path("/job/list")
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public List<JobOfferSummary> search(
			@QueryParam("industryTypeId") String industryTypeId,
			@QueryParam("occupationTypeId") String occupationTypeId,
			@QueryParam("freeWord") String freeWord,
			@QueryParam("pageCount") int pageCount) {

		// 検索ボタン押下時のみ、検索パラメータを更新する
		if (pageCount == 0) {
			this.industryTypeId = industryTypeId;
			this.occupationTypeId = occupationTypeId;
			this.freeWord = freeWord;
		}

		// 半角・全角空白でフリーワードを分割
		String[] freeWordItem = this.freeWord.split("[ 　]+");

		// フリーワードに部分一致する企業のIDをリストで取得
		List<String> corporationIdList = loadCorporationIdFreeWordSearchResult(freeWordItem);

		// 求人情報を取得
		List<JobOffer> jobOfferList = jobOfferDao.loadSearchResult(
				this.industryTypeId, this.occupationTypeId, freeWordItem,
				corporationIdList, pageCount);

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

		// 求人概要が未入力の場合、値を持たないOptionalオブジェクトを返す
		Optional<String> jobOfferOverview = Optional.ofNullable(parameter.getJobOfferOverview()).
				filter(overview -> overview.trim().length() > 0);

		return new JobOffer(
				parameter.getJobOfferId(),
				parameter.getJobOfferName(),
				parameter.getCorporationId(),
				parameter.getIndustryTypeId(),
				parameter.getOccupationTypeId(),
				parameter.getCatchCopy(),
				jobOfferOverview.orElse(null));
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

	/**
	 * 企業名をフリーワードで部分一致検索した結果を取得する。
	 *
	 * @param freeWordItem フリーワード（String配列）
	 * @return 企業IDリスト
	 */
	private List<String> loadCorporationIdFreeWordSearchResult(String[] freeWordItem) {

		List<Corporation> corporationList = corporationDao.loadCorporationId(freeWordItem);

		List<String> corporationIdList = new ArrayList<String>();

		// 取得した企業情報リストから、企業IDのみを取り出す
		corporationList.stream().forEach(
				corporation -> corporationIdList.add(corporation.getCorporationId()));

		return corporationIdList;
	}

	/**
	 * 検索結果を作成するメソッド<br>
	 * JobOfferに存在しない項目の取得を行う<br>
	 *
	 * @param jobOfferList 求人情報リスト
	 * @return 検索結果表示用求人情報リスト
	 */
	private List<JobOfferSummary> createJobOfferSummaryList(List<JobOffer> jobOfferList) {

		List<JobOfferSummary> jobOfferSummaryList = new ArrayList<JobOfferSummary>();

		// 受け取った求人情報が空の場合、空のリストを返す
		if (jobOfferList.isEmpty()) {
			return jobOfferSummaryList;
		}

		// 受け取った求人情報を元に、必要な項目を取得して検索結果表示用の求人情報リストを作成する
		jobOfferList.stream().forEach(
				jobOffer -> jobOfferSummaryList.add(
						createJobOfferSummary(
								jobOffer,
								loadCorporationName(jobOffer.getCorporationId()),
								loadIndustryTypeName(jobOffer.getIndustryTypeId()),
								loadOccupationTypeName(jobOffer.getOccupationTypeId())
								)
						)
				);
		return jobOfferSummaryList;
	}

	/**
	 * 企業IDに紐付く企業名を取得する
	 *
	 * @param corporationId 企業ID
	 * @return 企業名
	 */
	private String loadCorporationName(String corporationId) {

		return corporationDao.loadCorporationName(corporationId);
	}

	/**
	 * 業種IDに紐付く業種名を取得する
	 *
	 * @param industryTypeId 業種ID
	 * @return 業種名
	 */
	private String loadIndustryTypeName(String industryTypeId) {

		return industryTypeDao.loadIndustryTypeName(industryTypeId);
	}

	/**
	 * 職種IDに紐付く職種名を取得する
	 *
	 * @param occupationTypeId 職種ID
	 * @return 職種名
	 */
	private String loadOccupationTypeName(String occupationTypeId) {

		return occupationTypeDao.loadOccupationTypeName(occupationTypeId);
	}

	/**
	 * 検索結果表示用の求人情報作成メソッド
	 *
	 * @param jobOffer 求人情報
	 * @param corporationName 企業名
	 * @param industryTypeName 業種名
	 * @param occupationTypeName 職種名
	 * @return 検索結果表示用求人情報リスト
	 */
	private JobOfferSummary createJobOfferSummary(
			JobOffer jobOffer, String corporationName,
			String industryTypeName, String occupationTypeName) {

		Optional<String> jobOfferOverview = Optional.ofNullable(jobOffer.getJobOfferOverview());

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
				jobOfferOverview.orElse("未入力"));
	}

}
