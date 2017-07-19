package dao;

import static java.util.Objects.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import core.JobOffer;

/**
 * 求人情報のDaoクラス
 *
 * @author Kazushige Yamaguchi
 *
 */
public class JobOfferDao extends CommonDao<JobOffer> {

	public JobOfferDao(SessionFactory factory) {
		super(factory);
	}

    /**
     * SQLを実行し、検索結果を取得するメソッド。
     *
     * @return 検索条件に当てはまる求人情報リスト
     */
	public List<JobOffer> loadSearchResult(
			String industryTypeId, String occupationTypeId,
			String[] freeWordItem, List<String> corporationIdList) {

		Criteria criteria = criteria();

		if (!industryTypeId.isEmpty()) {
			criteria.add(Restrictions.eq("industryTypeId", industryTypeId));
		}

		if (!occupationTypeId.isEmpty()) {
			criteria.add(Restrictions.eq("occupationTypeId", occupationTypeId));
		}

		if (!freeWordItem[0].isEmpty()) {

			for (String freeWord : freeWordItem) {

				String searchWord = "%" + freeWord + "%";

				// or条件のグループ化
				Disjunction disjunction = Restrictions.disjunction();

				disjunction.add(Restrictions.like("jobOfferName", searchWord));
				disjunction.add(Restrictions.like("catchCopy", searchWord));
				disjunction.add(Restrictions.like("jobOfferOverview", searchWord));

				if (!corporationIdList.isEmpty()) {

					disjunction.add(Restrictions.in("corporationId", corporationIdList));
				}

				criteria.add(disjunction);
			}
		}

		return criteria.list();
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
	public String create(JobOffer jobOffer) {

		// 登録処理
		currentSession().save(requireNonNull(jobOffer));

		return jobOffer.getJobOfferId();
    }

}
