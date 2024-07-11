package com.vietcuong.simpleCrudApplication.service;

import com.vietcuong.simpleCrudApplication.model.Language;
import com.vietcuong.simpleCrudApplication.repository.LanguageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language findByLanguageName(String languageName) {
        return languageRepository.findByLanguageName(languageName);
    }

    @Transactional
    public Language findOrCreateLanguageByName(String languageName) {
        Language language = findByLanguageName(languageName);
        if (language == null) {
            language = new Language();
            language.setLanguageName(languageName);
            language = languageRepository.save(language);
        }
        return language;
    }
}
