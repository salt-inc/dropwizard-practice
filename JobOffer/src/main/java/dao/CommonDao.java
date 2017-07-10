package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

/**
 * Daoベースクラス
 * 
 * @author Kazushige Yamaguchi
 *
 * @param <E> エンティティクラス
 */
public class CommonDao<E> extends AbstractDAO<E> {
	
	public CommonDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * エンティティクラスに対応するテーブルから全データを取得するメソッド
	 * 
	 * @return 取得したデータのリスト
	 */
	@SuppressWarnings("unchecked")
	public List<E> loadAllData() {
		
		// Criteriaのインスタンスを生成
		Criteria criteria = criteria();
		
		// データの取得
		List<E> loadDataList = criteria.list();

		return loadDataList;
	}

}
