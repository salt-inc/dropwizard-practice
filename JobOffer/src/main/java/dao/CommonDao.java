package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
	/** EntityManager */
	private final EntityManager entityManager;

	public CommonDao(SessionFactory sessionFactory, EntityManager entityManager) {
		super(sessionFactory);
		this.entityManager = entityManager;
	}

	/**
	 * エンティティクラスに対応するテーブルから全データを取得するメソッド
	 * 
	 * @return 取得したデータのリスト
	 */
	public List<E> loadAllData() {
		
		// CriteriaBuilderを取得
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		// クエリを生成
		CriteriaQuery<E> query = builder.createQuery(getEntityClass());

		Root<E> root = query.from(getEntityClass()); 
		query.select(root);

		// クエリの実行
		List<E> loadDataList = entityManager.createQuery(query).getResultList();

		return loadDataList;
	}

}
