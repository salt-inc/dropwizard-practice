package dao;

import static java.util.Objects.requireNonNull;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import core.JobOffer;
import io.dropwizard.hibernate.AbstractDAO;

/**
 * 求人情報のDaoクラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class JobOfferDao extends AbstractDAO<JobOffer> {

	public JobOfferDao(SessionFactory factory) {
		super(factory);
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
		
		// フリーワードは部分一致検索なので、前後に%を追加
		query.setParameter("freeWord", "%" + freeWord + "%");
		
		return list(query);
    }
	
	/**
	 * 求人情報登録処理メソッド
	 * 
	 * @param jobOfferId 求人ID
	 * @param corporationId 企業ID
	 * @param jobOfferName 求人名
	 * @param industryTypeId 業種ID
	 * @param occupationTypeId 職種ID
	 * @param catchCopy キャッチコピー
	 * @param jobOfferOverview 求人概要
	 * @return 求人ID
	 */
	public String create(String jobOfferId,String corporationId, String jobOfferName, String industryTypeId, 
			String occupationTypeId, String catchCopy, String jobOfferOverview) {
		
		// 登録処理用のコンストラクタを用いてインスタンスを生成
		JobOffer jobOffer = new JobOffer(jobOfferId, corporationId, jobOfferName, industryTypeId, 
				occupationTypeId, catchCopy, jobOfferOverview);
		
		// 登録処理
		currentSession().save(requireNonNull(jobOffer));
		
		return jobOffer.getJobOfferId();
    }

}
