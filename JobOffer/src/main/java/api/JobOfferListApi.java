package api;

import configuration.JobOfferConfiguration;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.JobOfferResources;

public class JobOfferListApi extends Application<JobOfferConfiguration>{

	// エントリーポイント
	public static void main(String[] args) throws Exception {
		new JobOfferListApi().run(args);
	}
	
	public void initialize(Bootstrap<JobOfferConfiguration> bootstrap) {
		
		// htmlファイルの読み込み
		bootstrap.addBundle(new AssetsBundle("/assets/", "/list", "list.html"));
		// javaScriptファイルの読み込み
		bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
	}

	@Override
	public void run(JobOfferConfiguration configuration, Environment environment) throws Exception {
		
		// Resourcesクラスのインスタンス生成
		final JobOfferResources resource = new JobOfferResources();
		
		// Resourcesクラスの登録
		environment.jersey().register(resource);
	}
	
}
