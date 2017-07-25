package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.StatisticsResponseDto;
import test.service.StatisticsService;

@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping(value = "/{accountId}")
    public StatisticsResponseDto getStatisticsForAccount(@PathVariable String accountId) {
        return new StatisticsResponseDto(statisticsService.getStatisticsForAccount(accountId));
    }
}
