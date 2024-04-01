package com.neetry.platform.book.adapter.out.repository.preference;

import com.neetry.platform.book.domain.entity.preference.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Harutyun Badeyan
 * Date: 28.03.24
 * Time: 19:58
 */

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    Preference findByUserIdAndPreferenceTypeAndValue(Long userId,String preferenceType,String value);

    @Query("""
     SELECT p.value FROM Preference p
     WHERE p.userId = :userId AND p.preferenceType = 'genre' ORDER BY p.quantity DESC
    """)
    List<String> getAllGenresByUserId(@Param("userId") long userId);
    
    @Query("SELECT p.value FROM Preference p WHERE p.userId = :userId AND p.preferenceType = 'author' ORDER BY p.quantity DESC")
    List<String> getAllAuthorsByUserId(@Param("userId") long userId);}