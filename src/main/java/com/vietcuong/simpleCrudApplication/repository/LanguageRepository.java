package com.vietcuong.simpleCrudApplication.repository;

import com.vietcuong.simpleCrudApplication.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Language findByLanguageName(String languageName);
}
