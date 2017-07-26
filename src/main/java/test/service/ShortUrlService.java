package test.service;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.domain.Account;
import test.domain.RedirectStatistics;
import test.domain.UrlMapping;
import test.repository.RedirectStatisticsRepository;
import test.repository.UrlMappingRepository;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Service to deal with short urls and persist them.
 */
@Service
public class ShortUrlService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;
    @Autowired
    private RedirectStatisticsRepository redirectStatisticsRepository;

    /**
     * Gets full url from given short url.
     * @param shortUrl short url
     * @return full url which corresponds to given short url, else nothing.
     */
    public Optional<String> getFullUrlFromShortUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findDistinctFirstByShortUrl(shortUrl);
        return Optional.ofNullable(urlMapping.getFullUrl());
    }

    /**
     * Gets short url from given full url.
     * @param fullUrl full url which should be "shortened"
     * @return short url
     */
    public String shortenFullUrl(String fullUrl) {
        return Hashing.murmur3_32().hashString(fullUrl, StandardCharsets.UTF_8).toString();
    }

    /**
     * Persists url mapping given all input parameters.
     * @param shortUrl short url which should be mapped to given full url
     * @param fullUrl full url which should be mapped to given short url
     * @param accountId id of account which is considered to be the owner of the mapping
     */
    public void saveUrlMapping(String shortUrl, String fullUrl, String accountId) {
        UrlMapping urlMapping = new UrlMapping(fullUrl, shortUrl, new Account(accountId));
        urlMappingRepository.save(urlMapping);
        redirectStatisticsRepository.save(new RedirectStatistics(fullUrl, urlMapping, 0L));
    }
}
