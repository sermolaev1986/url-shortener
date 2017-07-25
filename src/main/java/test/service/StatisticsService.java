package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.domain.RedirectStatistics;
import test.repository.RedirectStatisticsRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private RedirectStatisticsRepository redirectStatisticsRepository;

    public void updateStatistics(String fullUrl) {
        redirectStatisticsRepository.incrementRedirectCount(fullUrl);
    }

    public Map<String, Long> getStatisticsForAccount(String accountId) {
        List<RedirectStatistics> statistics = redirectStatisticsRepository.getRedirectStatisticsByAccount(accountId);
        return statistics
                .stream()
                .collect(Collectors.toMap(RedirectStatistics::getFullUrl, RedirectStatistics::getRedirectCount));
    }
}
