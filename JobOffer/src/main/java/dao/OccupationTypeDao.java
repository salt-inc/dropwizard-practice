package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import core.OccupationType;
import io.dropwizard.hibernate.AbstractDAO;

/**
 * 職種情報のDaoクラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class OccupationTypeDao extends AbstractDAO<OccupationType> {

	public OccupationTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	/**
	 * 全職種情報をDBから取得し、呼び出し元に返すメソッド。
	 * 
	 * @return 登録されている職種情報のリスト
	 */
	public List<OccupationType> loadAllOccupationType() {
		
		Query query = namedQuery("core.OccupationType.getAll");
		
		return list(query);
	}

}
