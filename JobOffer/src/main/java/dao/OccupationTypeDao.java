package dao;

import org.hibernate.SessionFactory;

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

}
