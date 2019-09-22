package com.example.coderslabdemo.service;

import com.example.coderslabdemo.persistance.model.PropertyFilter;
import com.example.coderslabdemo.rest.dto.FeedbackDto;
import com.example.coderslabdemo.rest.dto.PropertyDto;

import java.util.List;

public interface PropertyService {

    PropertyDto get(Long id);

    List<PropertyDto> get(PropertyFilter filter);

    PropertyDto add(PropertyDto dto);

    PropertyDto modify(Long id, PropertyDto dto);

    FeedbackDto addFeedback(Long propertyId, FeedbackDto dto);

}
