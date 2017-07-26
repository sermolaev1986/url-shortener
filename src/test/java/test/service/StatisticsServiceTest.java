package test.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import test.domain.RedirectStatistics;
import test.repository.RedirectStatisticsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

    @InjectMocks
    private StatisticsService statisticsService = new StatisticsService();

    @Mock
    private RedirectStatisticsRepository redirectStatisticsRepository;

    @Test
    public void testGetStatisticsForAccount() throws Exception {

        List<RedirectStatistics> redirectStatistics = new ArrayList<>();
        redirectStatistics.add(new RedirectStatistics("https://www.mkyong.com/unittest/junit-how-to-test-a-map/",null, 3276L));
        redirectStatistics.add(new RedirectStatistics("https://github.com/sermolaev1986/url-shortener/blob/master/src/main/java/test/Application.java",null, 1L));
        redirectStatistics.add(new RedirectStatistics("https://www.hackerrank.com/domains/tutorials/cracking-the-coding-interview",null, 100L));


        Mockito
                .when(redirectStatisticsRepository.getRedirectStatisticsByAccount("123"))
                .thenReturn(redirectStatistics);

        Map<String, Long> actual = statisticsService.getStatisticsForAccount("123");

        Map<String, Long> expected = new HashMap<>();
        expected.put("https://www.mkyong.com/unittest/junit-how-to-test-a-map/", 3276L);
        expected.put("https://github.com/sermolaev1986/url-shortener/blob/master/src/main/java/test/Application.java", 1L);
        expected.put("https://www.hackerrank.com/domains/tutorials/cracking-the-coding-interview", 100L);

        Assert.assertThat(actual, is(expected));
    }

}