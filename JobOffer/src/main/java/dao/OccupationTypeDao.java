package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import core.OccupationType;
import io.dropwizard.hibernate.AbstractDAO;

public class OccupationTypeDao extends AbstractDAO<OccupationType> {

	public OccupationTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public List<OccupationType> getAllOccupationType() {
		
		// クエリの取得
		Query query = namedQuery("core.OccupationType.getAll");
		
		return list(query);
	}

}
