package test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.StatisticsResponseDto;

@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticsController {

    @GetMapping(value = "/{accountId}")
    public StatisticsResponseDto getStatisticsForAccount(@PathVariable String accountId) {
        throw new UnsupportedOperationException();
    }
}
