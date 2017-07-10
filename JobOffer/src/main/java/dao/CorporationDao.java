package dao;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.hibernate.SessionFactory;

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
	
	public Optional<Corporation> findById(Long id) {
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

}
