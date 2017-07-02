package resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import core.IndustryType;
import core.JobOffer;
import dao.IndustryTypeDao;
import dao.JobOfferDao;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * Resourceクラス。
 * 
 * @author Kazushige Yamaguchi
 */
@Path("/v1")
public class JobOfferResources {
	
	private final JobOfferDao jobOfferDao;
	
	private final IndustryTypeDao industryTypeDao;
	
	public JobOfferResources(JobOfferDao jobOfferDao, IndustryTypeDao industryTypeDao) {
		this.jobOfferDao = jobOfferDao;
		this.industryTypeDao = industryTypeDao;
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
	public IndustryType industryTypeRegist(@FormParam("industryTypeId") String industryTypeId, 
			@FormParam("industryTypeName") String industryTypeName) {
		
		IndustryType industryType = industryTypeDao.create(industryTypeId, industryTypeName);
		
		return industryType;
	}
	
	@POST
	@Path("/job/corporation")
	public void occupationTypeRegist() {
		
	}

}
