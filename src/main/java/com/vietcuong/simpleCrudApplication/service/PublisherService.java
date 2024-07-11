package com.vietcuong.simpleCrudApplication.service;

import com.vietcuong.simpleCrudApplication.model.Publisher;
import com.vietcuong.simpleCrudApplication.repository.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    public Publisher findByPublisherName(String publisherName) {
        return publisherRepository.findByPublisherName(publisherName);
    }

    @Transactional
    public Publisher findOrCreatePublisherByName(String publisherName) {
        Publisher publisher = findByPublisherName(publisherName);
        if (publisher == null) {
            publisher = new Publisher();
            publisher.setPublisherName(publisherName);
            publisher = publisherRepository.save(publisher);
        }
        return publisher;
    }
}
