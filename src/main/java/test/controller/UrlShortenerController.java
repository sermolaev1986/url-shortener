package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.dto.RegisterUrlRequestDto;
import test.dto.RegisterUrlResponseDto;
import test.service.AccountService;
import test.service.ShortUrlService;
import test.service.StatisticsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * REST controller which exposes functionality to register short urls and follow them.
 */
@RestController
@RequestMapping("/")
public class UrlShortenerController {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private ShortUrlService shortUrlService;
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        final Optional<String> maybeFullUrl = shortUrlService.getFullUrlFromShortUrl(shortUrl);
        if (maybeFullUrl.isPresent()) {
            final String fullUrl = maybeFullUrl.get();
            statisticsService.updateStatistics(fullUrl);
            response.sendRedirect(fullUrl);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PostMapping(value = "/api/v1/url-shortener/register")
    public RegisterUrlResponseDto registerUrl(@RequestBody RegisterUrlRequestDto urlData) {
        final String shortUrl = shortUrlService.shortenFullUrl(urlData.getUrl());
        shortUrlService.saveUrlMapping(shortUrl, urlData.getUrl(), accountService.getCurrentAccountId());
        return new RegisterUrlResponseDto(shortUrl);
    }
}
