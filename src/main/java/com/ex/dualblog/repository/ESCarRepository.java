package com.ex.dualblog.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ex.dualblog.model.Car;

import java.util.List;

public interface ESCarRepository extends ElasticsearchRepository<Car, String> {
}
