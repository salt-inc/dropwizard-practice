package dao;

import java.util.List;

import org.hibernate.Query;
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
	public List<IndustryType> getAllIndustryType() {
		
		Query query = namedQuery("core.IndustryType.getAll");
		
		return list(query);
	}

}
