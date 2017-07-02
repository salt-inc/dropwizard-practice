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
	
	private final JobOfferDao jobOfferDao;
	
	private final CorporationDao corporationDao;
	
	private final IndustryTypeDao industryTypeDao;
	
	private final OccupationTypeDao occupationTypeDao;
	
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
	
	@POST
	@Path("/job")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
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
	
	@POST
	@Path("/job/corporation")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Corporation corporationRegist(@FormParam("corporationId") String corporationId, 
			@FormParam("corporationName") String corporationName) {
		
		Corporation corporation = corporationDao.create(corporationId, corporationName);
		
		return corporation;
	}
	
	@POST
	@Path("/job/useJobOfferRegister/industry")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public List<IndustryType> getAllIndustryType() {
		
		List<IndustryType> IndustryTypeList = industryTypeDao.getAllIndustryType();
		
		return IndustryTypeList;
	}
	
	@POST
	@Path("/job/useJobOfferRegister/occupation")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public List<OccupationType> getAllOccupationType() {
		
		List<OccupationType> OccupationTypeList = occupationTypeDao.getAllOccupationType();
		
		return OccupationTypeList;
	}

}
