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

	public IndustryTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	/**
	 * 全業種情報をDBから取得し、呼び出し元に返すメソッド。
	 * 
	 * @return 登録されている業種情報のリスト
	 */
	public List<IndustryType> loadAllIndustryType() {
		
		return null;
	}

}
