package com.ryantablada.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ryantablada.entities.PhotoPost;

public interface PhotoPostRepository extends CrudRepository<PhotoPost, String> { }
