package com.example.coderslabdemo.rest.controller;


import com.example.coderslabdemo.persistance.model.PropertyFilter;
import com.example.coderslabdemo.rest.dto.FeedbackDto;
import com.example.coderslabdemo.rest.dto.PropertyDto;
import com.example.coderslabdemo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("")
    public List<PropertyDto> getProperties(PropertyFilter filter) {
        return propertyService.get(filter);
    }

    @GetMapping("/{id}")
    public PropertyDto getProperty(@PathVariable Long id) {
        return propertyService.get(id);
    }

    @PostMapping("")
    public PropertyDto addProperty(@RequestBody PropertyDto dto) {
        return propertyService.add(dto);
    }

    @PutMapping("/{id}")
    public PropertyDto modifyProperty(@PathVariable Long id, @RequestBody PropertyDto dto) {
        return propertyService.modify(id, dto);
    }

    @PostMapping("/{propertyId}/feedback")
    public FeedbackDto addFeedback(@PathVariable Long propertyId, @RequestBody FeedbackDto dto) {
        return propertyService.addFeedback(propertyId, dto);
    }
}
