package com.neetry.platform.book.adapter.out.repository.book;

import com.neetry.platform.book.adapter.out.repository.book.model.FakeItBookDTO;
import com.neetry.platform.book.adapter.out.repository.book.model.FakeItBookPagedDTO;
import com.neetry.platform.book.domain.entity.book.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Harutyun Badeyan
 * Date: 31.03.24
 * Time: 21:48
 */
@Component
public class FakeItBookRepository {
    private final Logger logger = LoggerFactory.getLogger(FakeItBookRepository.class);
    private final static String URL = "https://fakerapi.it/api/v1/books?_quantity=100&_locale=en_US";
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public List<Book> getAll() {
        logger.debug("Querying fake book source");
        FakeItBookPagedDTO result = restTemplate.getForEntity(URL, FakeItBookPagedDTO.class).getBody();
        if (result == null) {
            throw new IllegalStateException("Cannot fetch fake data from %s".formatted(URL));
        }
        logger.debug("Successfully fetched books from - {}, count - {}", URL, result.data().size());
        return result.data().stream().map(this::mapFakeBookData).toList();
    }

    private Book mapFakeBookData(FakeItBookDTO dto) {
        return new Book(
                dto.title(), 
                dto.author(), 
                dto.genre(),
                dto.description(),
                dto.isbn(),
                dto.image(),
                dto.published().toString(), 
                dto.publisher()
        );
    }
}
