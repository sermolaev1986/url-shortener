package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.domain.RedirectStatistics;
import test.repository.RedirectStatisticsRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service to encapsulate statistics functionality.
 */
@Service
public class StatisticsService {

    @Autowired
    private RedirectStatisticsRepository redirectStatisticsRepository;

    /**
     * Updates redirect statistics in underlying storage. Should be called when new redirect occurs.
     * @param fullUrl full url which was hit by redirect
     */
    public void updateStatistics(String fullUrl) {
        redirectStatisticsRepository.incrementRedirectCount(fullUrl);
    }

    /**
     * Fetches redirect statistics for given account.
     * @param accountId id of account
     * @return
     */
    public Map<String, Long> getStatisticsForAccount(String accountId) {
        List<RedirectStatistics> statistics = redirectStatisticsRepository.getRedirectStatisticsByAccount(accountId);
        return statistics
                .stream()
                .collect(Collectors.toMap(RedirectStatistics::getFullUrl, RedirectStatistics::getRedirectCount));
    }
}
