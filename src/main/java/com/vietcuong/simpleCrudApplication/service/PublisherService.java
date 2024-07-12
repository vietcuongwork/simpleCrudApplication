package com.vietcuong.simpleCrudApplication.service;


import com.vietcuong.simpleCrudApplication.model.Publisher;
import com.vietcuong.simpleCrudApplication.repository.PublisherRepository;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

}
