package api;

import org.apache.http.client.HttpClient;

import configuration.JobOfferConfiguration;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.JobOfferResources;

public class JobOfferListApi extends Application<JobOfferConfiguration>{

	// エントリーポイント
	public static void main(String[] args) throws Exception {
		System.out.println("メインメソッド起動");
		new JobOfferListApi().run(args);
	}
	
	public void initialize(Bootstrap<JobOfferConfiguration> bootstrap) {
		
		// Memo（引数の役割） ソース配置場所 , url , htmlファイル名
		// htmlファイルの読み込み
		bootstrap.addBundle(new AssetsBundle("/assets/", "/list", "list.html"));
		// bootstrap.addBundle(new AssetsBundle("/assets/", "/api/search", "searchResult.html"));
		// bootstrap.addBundle(new ViewBundle<JobOfferConfiguration>());
		// javaScriptファイルの読み込み
		bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
	}

	@Override
	public void run(JobOfferConfiguration configuration, Environment environment) throws Exception {
		System.out.println("Apiのrun起動");
		String occupationTypeId = configuration.getOccupationTypeId();
		System.out.println("読み込んだ値：" + occupationTypeId);
		final JobOfferResources resource = new JobOfferResources(configuration.getIndustryTypeId(), 
				configuration.getOccupationTypeId(), configuration.getCatchCopy());
		System.out.println("ApiのJobOfferResources生成完了");
		environment.jersey().register(resource);
		final HttpClient httpClient = new HttpClientBuilder(environment)
				.using(configuration.getHttpClientConfiguration()).build(getName());
		environment.jersey().register(new JobOfferResources(httpClient));
	}
	
}
