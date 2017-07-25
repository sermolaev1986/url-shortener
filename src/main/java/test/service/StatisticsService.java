package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.repository.RedirectStatisticsRepository;

import java.util.Collections;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private RedirectStatisticsRepository redirectStatisticsRepository;

    public void updateStatistics(String fullUrl) {
        redirectStatisticsRepository.incrementRedirectCount(fullUrl);
    }

    public Map<String, Long> getStatisticsForAccount(String accountId) {
        redirectStatisticsRepository.getRedirectStatisticsByAccount(accountId);
        return Collections.emptyMap();
    }
}
