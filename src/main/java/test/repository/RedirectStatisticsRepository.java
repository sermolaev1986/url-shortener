package test.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import test.domain.RedirectStatistics;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Access layer for {@link RedirectStatistics} entity.
 */
@Transactional
public interface RedirectStatisticsRepository extends Repository<RedirectStatistics, String> {

    @Query("UPDATE RedirectStatistics rs SET rs.redirectCount = rs.redirectCount + 1 WHERE rs.fullUrl = :fullUrl")
    @Modifying
    void incrementRedirectCount(@Param("fullUrl") String fullUrl);

    @Query("FROM RedirectStatistics rs WHERE rs.urlMapping.account.id = :accountId")
    List<RedirectStatistics> getRedirectStatisticsByAccount(@Param("accountId") String accountId);

    RedirectStatistics save(RedirectStatistics statistics);
}
