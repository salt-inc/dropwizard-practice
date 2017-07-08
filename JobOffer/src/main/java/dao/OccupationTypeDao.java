package dao;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import core.OccupationType;

/**
 * 職種情報のDaoクラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class OccupationTypeDao extends CommonDao<OccupationType> {

	public OccupationTypeDao(SessionFactory sessionFactory, EntityManager entityManager) {
		super(sessionFactory, entityManager);
	}

}
