package test.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import test.domain.RedirectStatistics;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RedirectStatisticsRepository extends Repository<RedirectStatistics, String> {

    @Query("UPDATE RedirectStatistics rs SET rs.redirectCount = rs.redirectCount + 1 WHERE rs.fullUrl = :fullUrl")
    void incrementRedirectCount(@Param("fullUrl") String fullUrl);

    @Query("SELECT rs.id FROM RedirectStatistics rs WHERE rs.urlMapping.account = :accountId")
    List<RedirectStatistics> getRedirectStatisticsByAccount(@Param("accountId") String accountId);
}
