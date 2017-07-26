package test.repository;

import org.springframework.data.repository.Repository;
import test.domain.UrlMapping;

import javax.transaction.Transactional;

/**
 * Access layer for {@link UrlMapping} entity.
 */
@Transactional
public interface UrlMappingRepository extends Repository<UrlMapping, String> {
    UrlMapping save(UrlMapping urlMapping);

    UrlMapping findDistinctFirstByShortUrl(String shortUrl);
}
