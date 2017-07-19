package dao;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import core.OccupationType;

/**
 * 職種情報のDaoクラス
 *
 * @author Kazushige Yamaguchi
 *
 */
public class OccupationTypeDao extends CommonDao<OccupationType> {

	public OccupationTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * 職種IDに紐付く職種名を取得する
	 *
	 * @param occupationTypeId 職種ID
	 * @return 職種名
	 */
	public String loadOccupationTypeName(String occupationTypeId) {

		Criteria criteria = criteria();

		criteria.add(Restrictions.eq("occupationTypeId", occupationTypeId));

		// 職種情報が存在しなかった場合を考慮し、Optionalを使用する
		// 受け取った職種IDに一致する職種情報を取り出す
		Optional<OccupationType> occupationType = Optional.ofNullable((OccupationType)criteria.uniqueResult());

		// 職種名を返却する（存在しない場合は「未登録の職種」を返却する）
		return occupationType.orElseGet(() -> new OccupationType("999999", "未登録の職種")).getOccupationTypeName();
	}

}
