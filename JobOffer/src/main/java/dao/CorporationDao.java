package dao;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import core.Corporation;

/**
 * 企業情報のDaoクラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class CorporationDao extends CommonDao<Corporation> {

	public CorporationDao(SessionFactory factory) {
		super(factory);
	}
	
	public Optional<Corporation> findById(long id) {
        return Optional.ofNullable(get(id));
    }
	
    /**
     * 企業情報の登録処理を行うメソッド。
     * 
     * @return 登録した企業情報のID
     */
	public String create(Corporation corporation) {
		
		// 登録処理
		currentSession().save(requireNonNull(corporation));
		
		return corporation.getCorporationId();
    }
	
	public String loadCorporationName(String corporationId) {
		
		Criteria criteria = criteria();
		
		criteria.add(Restrictions.eq("corporationId", corporationId));
		
		Corporation corporation = (Corporation) criteria.uniqueResult();
		
		return corporation.getCorporationName();
	}
	
	public List<Corporation> loadCorporationId(String[] freeWordItem) {
		
		Criteria criteria = criteria();
		
		// or条件のグループ化
		Disjunction disjunction = Restrictions.disjunction();
		
		for (String freeWord : freeWordItem) {
			
			String searchWord = "%" + freeWord + "%";
			
			disjunction.add(Restrictions.like("corporationName", searchWord));
		}
		
		criteria.add(disjunction);
		
		return criteria.list();
	}

}
