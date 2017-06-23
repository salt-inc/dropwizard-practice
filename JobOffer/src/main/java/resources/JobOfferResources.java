package resources;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import dto.SearchResponce;

@Path("/list")
public class JobOfferResources {
	
	/** 業種ID */
	// finalは…
	private String industryTypeId = null;
	
	/** 職種ID */
	private String occupationTypeId = null;
	
	/** フリーワード */
	private String freeWord = null;
	
	private HttpClient httpClient = null;
	
	public JobOfferResources(HttpClient httpClient) {
		this.httpClient = httpClient;
		System.out.println(httpClient.toString());
	}
	
	public JobOfferResources(String occupationTypeId, String industryTypeId, String freeWord) {
		System.out.println("JobOfferResources起動：" + occupationTypeId);
		this.industryTypeId = industryTypeId;
		this.occupationTypeId = occupationTypeId;
		this.freeWord = freeWord;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResponce fetch(
			@QueryParam("industryTypeId") String industryTypeId, 
			@QueryParam("occupationTypeId") String occupationTypeId, 
			@QueryParam("freeWord") String freeWord, 
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws ClientProtocolException, IOException {
		
		System.out.println("fetchを起動");
		
		response.setContentType("text/html; charset=UTF-8");
		// response.sendRedirect("http://localhost:8080/list");
		
		response.setHeader("occupationTypeId", industryTypeId);
		
		return new SearchResponce(industryTypeId, occupationTypeId, freeWord);
		
	}

}
