package dao;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import core.IndustryType;

/**
 * 業種情報Daoクラス
 *
 * @author Kazushige Yamaguchi
 *
 */
public class IndustryTypeDao extends CommonDao<IndustryType> {

	public IndustryTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * 業種IDに紐付く業種名を取得する
	 *
	 * @param industryTypeId 業種ID
	 * @return 業種名
	 */
	public String loadIndustryTypeName(String industryTypeId) {

		Criteria criteria = criteria();

		criteria.add(Restrictions.eq("industryTypeId", industryTypeId));

		// 業種情報が存在しなかった場合を考慮し、Optionalを使用する
		// 受け取った業種IDに一致する業種情報を取り出す
		Optional<IndustryType> industryType = Optional.ofNullable((IndustryType)criteria.uniqueResult());

		// 業種名を返却する（存在しない場合は「未登録の業種」を返却する）
		return industryType.orElseGet(() -> new IndustryType("999999", "未登録の業種")).getIndustryTypeName();
	}

}
