package dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import core.IndustryType;

/**
 * 業種情報Daoクラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class IndustryTypeDao extends CommonDao<IndustryType> {

	public IndustryTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public String loadIndustryTypeName(String industryTypeId) {
		
		Criteria criteria = criteria();
		
		criteria.add(Restrictions.eq("industryTypeId", industryTypeId));
		
		IndustryType industryType = (IndustryType) criteria.uniqueResult();
		
		return industryType.getIndustryTypeName();
	}

}
