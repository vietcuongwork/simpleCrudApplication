package com.vietcuong.simpleCrudApplication.service;


import com.vietcuong.simpleCrudApplication.exception.LanguageNotExistException;
import com.vietcuong.simpleCrudApplication.model.Language;
import com.vietcuong.simpleCrudApplication.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

/*    public Language findByLanguageName(String languageName) throws LanguageNotExistException {
        Optional<Language> languageOptional = languageRepository.findByLanguageName(languageName);
        if(languageOptional.isEmpty()){
            throw new LanguageNotExistException();
        }
        return languageOptional.get();
    }*/

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
