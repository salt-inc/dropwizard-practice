package api;

import configuration.JobOfferConfiguration;
import core.JobOffer;
import dao.JobOfferDao;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.JobOfferResources;

public class JobOfferListApi extends Application<JobOfferConfiguration>{

	// エントリーポイント
	public static void main(String[] args) throws Exception {
		new JobOfferListApi().run(args);
	}
	
	/** hibernate */
	private final HibernateBundle<JobOfferConfiguration> hibernate =
	        new HibernateBundle<JobOfferConfiguration>(JobOffer.class) {
	            @Override
	            public DataSourceFactory getDataSourceFactory(JobOfferConfiguration configuration) {
	                return configuration.getDataSourceFactory();
	            }
	        };
	
	public void initialize(Bootstrap<JobOfferConfiguration> bootstrap) {
		
		// htmlファイルの読み込み
		bootstrap.addBundle(new AssetsBundle("/assets/", "/list", "list.html"));
		// javaScriptファイルの読み込み
		bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
		
		bootstrap.addBundle(hibernate);
		
	}

	@Override
	public void run(JobOfferConfiguration configuration, Environment environment) throws Exception {
		
		// Daoクラスのインスタンスを生成
		final JobOfferDao dao = new JobOfferDao(hibernate.getSessionFactory());
		
		// Resourcesクラスのインスタンス生成
		// JobOfferDaoクラスのインスタンスを渡す
		final JobOfferResources resource = new JobOfferResources(dao);
		
		// Resourcesクラスの登録
		environment.jersey().register(resource);
		
	}
	
}
