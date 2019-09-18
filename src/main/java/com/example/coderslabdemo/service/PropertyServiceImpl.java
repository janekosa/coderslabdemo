package com.example.coderslabdemo.service;

import com.example.coderslabdemo.dao.PropertyRepository;
import com.example.coderslabdemo.domain.Property;
import com.example.coderslabdemo.domain.PropertyFilter;
import com.example.coderslabdemo.domain.PropertyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public List<Property> getAll() {
        List<Property> result = propertyRepository.findAll();
        result.sort(Property.ratingsComparator());
        return result;
    }

    @Override
    public Optional<Property> get(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> getAllByType(PropertyType type) {
        List<Property> result = propertyRepository.findByType(type);
        result.sort(Property.ratingsComparator());
        return result;
    }

    @Override
    public List<Property> get(PropertyFilter filter) {
        List<Property> result = propertyRepository.findAll(PropertyRepository.Specs.getFromFilter(filter));
        result.sort(Property.ratingsComparator());
        return result;
    }


}
