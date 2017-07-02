package dao;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import core.JobOffer;
import io.dropwizard.hibernate.AbstractDAO;

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
	
    /**
     * SQLを実行し、検索結果を取得するメソッド。
     * 
     * @return 検索条件に当てはまる求人情報リスト
     */
	public List<JobOffer> getSearchResult(
			String industryTypeId, String occupationTypeId, String freeWord) {
		
		// クエリの取得
		Query query = namedQuery("core.JobOffer.searchResult");
		
		// クエリのパラメータを設定
		query.setParameter("industryTypeId", industryTypeId);
		query.setParameter("occupationTypeId", occupationTypeId);
		
		return list(query);
    }
	
	/**
	 * 求人情報登録処理メソッド
	 * 
	 * @param jobOfferId
	 * @param corporationId
	 * @param jobOfferName
	 * @param industryTypeId
	 * @param occupationTypeId
	 * @param catchCopy
	 * @param jobOfferOverview
	 * @return
	 */
	public JobOffer create(String jobOfferId,String corporationId, String jobOfferName, String industryTypeId, 
			String occupationTypeId, String catchCopy, String jobOfferOverview) {
		
		System.out.println("create_jobOffer起動");
		JobOffer jobOffer = new JobOffer(jobOfferId, corporationId, jobOfferName, industryTypeId, 
				occupationTypeId, catchCopy, jobOfferOverview);
		System.out.println("save開始");
		currentSession().save(requireNonNull(jobOffer));
		System.out.println("save完了");
		
		return jobOffer;
    }

}
