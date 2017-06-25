package resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import core.JobOffer;
import dao.JobOfferDao;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * Resourceクラス。
 * 
 * @author Kazushige Yamaguchi
 */
@Path("/list")
public class JobOfferResources {
	
	private final JobOfferDao dao;
	
	public JobOfferResources(JobOfferDao dao) {
		this.dao = dao;
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
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public List<JobOffer> search(
			@QueryParam("industryTypeId") String industryTypeId, 
			@QueryParam("occupationTypeId") String occupationTypeId, 
			@QueryParam("freeWord") String freeWord) {
		
		return dao.getSearchResult(industryTypeId, occupationTypeId, freeWord);
		
	}

}
