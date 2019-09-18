package com.example.coderslabdemo.rest;


import com.example.coderslabdemo.domain.Property;
import com.example.coderslabdemo.domain.PropertyFilter;
import com.example.coderslabdemo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/properties")
    public List<Property> getProperties(PropertyFilter filter) {
        return propertyService.get(filter);
    }


}
