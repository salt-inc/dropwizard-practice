package dao;

import core.JobOffer;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.Query;

/**
 * 求人情報のDaoクラス
 * 
 * @author kazu
 *
 */
public class JobOfferDao extends AbstractDAO<JobOffer> {

	public JobOfferDao(SessionFactory factory) {
		super(factory);
	}
	
	public Optional<JobOffer> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public JobOffer create(JobOffer jobOffer) {
        return persist(jobOffer);
    }
	
    /**
     * SQLを実行し、検索結果を取得するメソッド。
     * 
     * @return 検索条件に当てはまる求人情報リスト
     */
	public List<JobOffer> getSearchResult(
			String industryTypeId, String occupationTypeId, String freeWord) {
		
		// クエリの取得
		Query query = namedQuery("core.JobOffer.searchResult");
		
		System.out.println("クエリのパラメータ設定");
		
		// クエリのパラメータを設定
		query.setParameter("industryTypeId", industryTypeId);
		query.setParameter("occupationTypeId", occupationTypeId);
		
		System.out.println("クエリのパラメータ設定完了");
		
        return list(query);
    }

}
