package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import core.IndustryType;
import io.dropwizard.hibernate.AbstractDAO;

public class IndustryTypeDao extends AbstractDAO<IndustryType> {

	public IndustryTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public List<IndustryType> getAllIndustryType() {
		
		// クエリの取得
		Query query = namedQuery("core.IndustryType.getAll");
		
		return list(query);
	}

}
