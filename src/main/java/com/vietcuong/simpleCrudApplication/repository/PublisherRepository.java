package com.vietcuong.simpleCrudApplication.repository;

import com.vietcuong.simpleCrudApplication.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findByPublisherName(String publisherName);
}
