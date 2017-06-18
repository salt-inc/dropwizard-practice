package api;

import configuration.JobOfferConfiguration;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class JobOfferListApi extends Application<JobOfferConfiguration>{

	// エントリーポイント
	public static void main(String[] args) throws Exception {
		new JobOfferListApi().run(args);
	}
	
	public void initialize(Bootstrap<JobOfferConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets/", "/", "list.html"));
	}

	@Override
	public void run(JobOfferConfiguration configuration, Environment environment) throws Exception {
		System.out.println("Apiのrun起動");
	}
	
}
