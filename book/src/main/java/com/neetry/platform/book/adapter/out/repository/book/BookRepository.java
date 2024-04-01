package com.neetry.platform.book.adapter.out.repository.book;

import com.neetry.platform.book.domain.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 19:24
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByAuthorAndPublishAndTitle(String author, String publish, String title);

    boolean existsByIdNotNull();

    @Query(value = """ 
            SELECT *
            FROM book b
            ORDER BY IF(genre IN (:genres), 0, 1),
                     FIELD(genre, :genres),
                     IF(author IN (:authors), 0, 1),
                     FIELD(author, :authors), rand() DESC;
            """, nativeQuery = true)
    Page<Book> getBooksByPreference(@Param("genres") List<String> genres,
                                    @Param("authors") List<String> authors,
                                    Pageable pageable);

}
