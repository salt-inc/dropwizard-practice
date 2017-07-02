package api;

import configuration.JobOfferConfiguration;
import core.Corporation;
import core.IndustryType;
import core.JobOffer;
import core.OccupationType;
import dao.CorporationDao;
import dao.IndustryTypeDao;
import dao.JobOfferDao;
import dao.OccupationTypeDao;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.JobOfferResources;

public class JobOfferListApi extends Application<JobOfferConfiguration>{

	// エントリーポイント
	public static void main(String[] args) throws Exception {
		new JobOfferListApi().run(args);
	}
	
	/** 
	 * hibernate
	 * 
	 * Entityの登録を行う。
	 */
	private final HibernateBundle<JobOfferConfiguration> hibernate =
	        new HibernateBundle<JobOfferConfiguration>(
	        		JobOffer.class, IndustryType.class, OccupationType.class, Corporation.class) {
	            @Override
	            public DataSourceFactory getDataSourceFactory(JobOfferConfiguration configuration) {
	                return configuration.getDataSourceFactory();
	            }
	        };
	
	@Override
	public void initialize(Bootstrap<JobOfferConfiguration> bootstrap) {
		
		// htmlファイルの読み込み
		bootstrap.addBundle(new AssetsBundle("/assets/", "/view/job/", "list.html"));
		// javaScriptファイルの読み込み
		bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
		// cssファイルの読み込み
		bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
		
		// Migration
		bootstrap.addBundle(new MigrationsBundle<JobOfferConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(JobOfferConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
		
		bootstrap.addBundle(hibernate);
		
	}

	@Override
	public void run(JobOfferConfiguration configuration, Environment environment) throws Exception {
		
		// Daoクラスのインスタンスを生成
		final JobOfferDao jobOfferDao = new JobOfferDao(hibernate.getSessionFactory());
		
		final CorporationDao corporationDao = new CorporationDao(hibernate.getSessionFactory());
				
		final IndustryTypeDao industryTypeDao = new IndustryTypeDao(hibernate.getSessionFactory());
		
		final OccupationTypeDao occupationTypeDao = new OccupationTypeDao(hibernate.getSessionFactory());
		
		// Resourcesクラスのインスタンス生成
		// JobOfferDaoクラスのインスタンスを渡す
		final JobOfferResources resource = new JobOfferResources(jobOfferDao, corporationDao, industryTypeDao, occupationTypeDao);
		
		// Resourcesクラスの登録
		environment.jersey().register(resource);
		
	}
	
}
