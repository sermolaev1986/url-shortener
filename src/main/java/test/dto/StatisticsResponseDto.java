package test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class StatisticsResponseDto {
    private Map<String, Long> statisticsMap;
}
