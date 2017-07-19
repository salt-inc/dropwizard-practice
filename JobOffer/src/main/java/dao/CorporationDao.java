package dao;

import static java.util.Objects.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import core.Corporation;

/**
 * 企業情報のDaoクラス
 *
 * @author Kazushige Yamaguchi
 *
 */
public class CorporationDao extends CommonDao<Corporation> {

	public CorporationDao(SessionFactory factory) {
		super(factory);
	}

	public Optional<Corporation> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    /**
     * 企業情報の登録処理を行うメソッド。
     *
     * @return 登録した企業情報のID
     */
	public String create(Corporation corporation) {

		// 登録処理
		currentSession().save(requireNonNull(corporation));

		return corporation.getCorporationId();
    }

	/**
	 * 企業IDに紐付く企業名を取得する
	 *
	 * @param corporationId 企業ID
	 * @return 企業名
	 */
	public String loadCorporationName(String corporationId) {

		Criteria criteria = criteria();

		criteria.add(Restrictions.eq("corporationId", corporationId));

		// 企業情報が存在しなかった場合を考慮し、Optionalを使用する
		// 受け取った企業IDに一致する企業情報を取り出す
		Optional<Corporation> corporation = Optional.ofNullable((Corporation) criteria.uniqueResult());

		// 企業名を返却する（存在しない場合は「未登録の企業」を返却する）
		return corporation.orElseGet(() -> new Corporation("9999999999", "未登録の企業")).getCorporationName();
	}

	/**
	 * フリーワードに一致する企業リストを取得する
	 *
	 * @param freeWordItem フリーワード（String配列）
	 * @return 企業リスト
	 */
	@SuppressWarnings("unchecked")
	public List<Corporation> loadCorporationId(String[] freeWordItem) {

		Criteria criteria = criteria();

		// or条件のグループ化
		Disjunction disjunction = Restrictions.disjunction();

		// 企業名の部分一致検索
		Stream.of(freeWordItem).forEach(
				word -> disjunction.add(Restrictions.like("corporationName", "%" + word + "%"))
				);

		criteria.add(disjunction);

		return criteria.list();
	}

}
