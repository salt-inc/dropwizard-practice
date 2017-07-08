package dao;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import core.Corporation;

/**
 * 企業情報のDaoクラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class CorporationDao extends CommonDao<Corporation> {

	public CorporationDao(SessionFactory factory, EntityManager entityManager) {
		super(factory, entityManager);
	}
	
	public Optional<Corporation> findById(Long id) {
        return Optional.ofNullable(get(id));
    }
	
    /**
     * 企業情報の登録処理を行うメソッド。
     * 
     * @return 登録した企業情報
     */
	public String create(String companyId, String companyName) {
		
		Corporation company = new Corporation(companyId, companyName);
		
		// 登録処理
		currentSession().save(requireNonNull(company));
		
		return company.getcorporationId();
    }

}
