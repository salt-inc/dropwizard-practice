package dao;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import core.IndustryType;

/**
 * 業種情報Daoクラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class IndustryTypeDao extends CommonDao<IndustryType> {

	public IndustryTypeDao(SessionFactory sessionFactory, EntityManager entityManager) {
		super(sessionFactory, entityManager);
	}

}
