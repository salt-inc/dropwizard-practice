package dao;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import core.IndustryType;
import io.dropwizard.hibernate.AbstractDAO;

/**
 * 求人情報のDaoクラス
 * 
 * @author kazu
 *
 */
public class IndustryTypeDao extends AbstractDAO<IndustryType> {

	public IndustryTypeDao(SessionFactory factory) {
		super(factory);
	}
	
	public Optional<IndustryType> findById(Long id) {
        return Optional.ofNullable(get(id));
    }
	
    /**
     * SQLを実行し、検索結果を取得するメソッド。
     * 
     * @return 検索条件に当てはまる求人情報リスト
     */
	public IndustryType create(String industryTypeId, String industryTypeName) {
		
		IndustryType industryType = new IndustryType(industryTypeId, industryTypeName);
		currentSession().save(requireNonNull(industryType));
		
		return industryType;
    }

}
