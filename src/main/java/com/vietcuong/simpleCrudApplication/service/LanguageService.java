package com.vietcuong.simpleCrudApplication.service;


import com.vietcuong.simpleCrudApplication.model.Language;
import com.vietcuong.simpleCrudApplication.repository.LanguageRepository;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language findByLanguageName(String languageName) {
        return languageRepository.findByLanguageName(languageName);
    }

}
