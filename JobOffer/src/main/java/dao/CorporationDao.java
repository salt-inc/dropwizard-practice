package dao;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.hibernate.SessionFactory;

import core.Corporation;
import io.dropwizard.hibernate.AbstractDAO;

/**
 * 求人情報のDaoクラス
 * 
 * @author kazu
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
     * SQLを実行し、検索結果を取得するメソッド。
     * 
     * @return 検索条件に当てはまる求人情報リスト
     */
	public Corporation create(String companyId, String companyName) {
		
		Corporation company = new Corporation(companyId, companyName);
		currentSession().save(requireNonNull(company));
		
		return company;
    }

}
