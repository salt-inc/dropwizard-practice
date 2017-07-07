package dao;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import core.Corporation;
import io.dropwizard.hibernate.AbstractDAO;

/**
 * 企業情報のDaoクラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class CorporationDao extends AbstractDAO<Corporation> {

	public CorporationDao(SessionFactory factory) {
		super(factory);
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
	
	/**
	 * 全企業情報をDBから取得し、呼び出し元に返すメソッド。
	 * 
	 * @return 登録されている企業情報のリスト
	 */
	public List<Corporation> loadAllCorporation() {
		
		Query query = namedQuery("core.Corporation.getAll");
		
		return list(query);
	}

}
