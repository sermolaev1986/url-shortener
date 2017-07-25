package test.service;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.domain.Account;
import test.domain.UrlMapping;
import test.repository.UrlMappingRepository;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class ShortUrlService {

    @Autowired private UrlMappingRepository urlMappingRepository;

    public Optional<String> getFullUrlFromShortUrl(String shortUrl)  {
        UrlMapping urlMapping = urlMappingRepository.findDistinctFirstByShortUrl(shortUrl);
        return Optional.ofNullable(urlMapping.getFullUrl());
    }

    public String shortenFullUrl(String fullUrl)  {
        return Hashing.murmur3_32().hashString(fullUrl, StandardCharsets.UTF_8).toString();
    }

    public void saveUrlMapping(String shortUrl, String fullUrl, String accountId)   {
        urlMappingRepository.save(new UrlMapping(fullUrl, shortUrl, new Account(accountId)));
    }
}
