package com.example.coderslabdemo.service;

import com.example.coderslabdemo.domain.Property;
import com.example.coderslabdemo.domain.PropertyFilter;
import com.example.coderslabdemo.domain.PropertyType;

import java.util.List;
import java.util.Optional;

public interface PropertyService {

    List<Property> getAll();

    Optional<Property> get(Long id);

    List<Property> getAllByType(PropertyType type);

    List<Property> get(PropertyFilter filter);
}
