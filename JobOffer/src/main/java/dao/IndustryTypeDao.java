package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;

import core.IndustryType;
import io.dropwizard.hibernate.AbstractDAO;

/**
 * 業種情報Daoクラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class IndustryTypeDao extends AbstractDAO<IndustryType> {
	
	/** EntityManager */
	private final EntityManager entityManager;

	public IndustryTypeDao(SessionFactory sessionFactory, EntityManager entityManager) {
		super(sessionFactory);
		this.entityManager = entityManager;
	}
	
	/**
	 * 全業種情報をDBから取得し、呼び出し元に返すメソッド。
	 * 
	 * @return 登録されている業種情報のリスト
	 */
	public List<IndustryType> loadAllIndustryType() {
		
		// CriteriaBuilderを取得
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		// クエリを生成
		CriteriaQuery<IndustryType> query = builder.createQuery(IndustryType.class);
		
		Root<IndustryType> root = query.from(IndustryType.class); 
		query.select(root);
		
		// クエリの実行
		List<IndustryType> industryTypeList = entityManager.createQuery(query).getResultList();
		
		return industryTypeList;
	}

}
