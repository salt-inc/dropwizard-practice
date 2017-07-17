package dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import core.OccupationType;
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

	public String loadOccupationTypeName(String occupationTypeId) {
		
		Criteria criteria = criteria();
		
		criteria.add(Restrictions.eq("occupationTypeId", occupationTypeId));
		
		OccupationType occupationType = (OccupationType) criteria.uniqueResult();
		
		return occupationType.getOccupationTypeName();
	}

}
