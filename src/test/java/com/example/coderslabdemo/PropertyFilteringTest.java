package com.example.coderslabdemo;

import com.example.coderslabdemo.assertions.PropertyAssert;
import com.example.coderslabdemo.dao.PropertyRepository;
import com.example.coderslabdemo.domain.Property;
import com.example.coderslabdemo.domain.PropertyFilter;
import com.example.coderslabdemo.service.PropertyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyFilteringTest {

    @Autowired
    private TestDataUtils dataUtils;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyRepository propertyRepository;

    @Before
    public void loadData() {
        dataUtils.loadTestData();
    }

    @After
    public void cleanData() {
        dataUtils.cleanTestData();
    }

    @Test
    public void testFilterByCity() {
        PropertyFilter filter = PropertyFilter.builder().withCity(TestDataUtils.CITY1).build();
        List<Property> filteredProperties = propertyService.get(filter);
        assertThat(filteredProperties).hasSize(1);
        PropertyAssert.assertThat(filteredProperties.get(0)).hasCity(TestDataUtils.CITY1);
    }

    @Test
    public void testFilterByArea() {
        PropertyFilter filter = PropertyFilter.builder().withSqMFrom(90).withAreaSqMTo(110).build();
        List<Property> filteredProperties = propertyService.get(filter);
        assertThat(filteredProperties).hasSize(1);
        PropertyAssert.assertThat(filteredProperties.get(0)).hasAreaSqM(100);
    }

    @Test
    public void testFilterByRooms() {
        PropertyFilter filter = PropertyFilter.builder().withRoomsFrom(3).withRoomsTo(5).build();
        List<Property> filteredProperties = propertyService.get(filter);
        assertThat(filteredProperties).hasSize(2);
        filteredProperties.sort(Comparator.comparing(Property::getRooms));
        PropertyAssert.assertThat(filteredProperties.get(0)).hasRooms(3);
        PropertyAssert.assertThat(filteredProperties.get(1)).hasRooms(5);
    }

    @Test
    public void testFilterByRatings() {
        PropertyFilter filter = PropertyFilter.builder().withRatingsFrom(3.9).build();
        List<Property> filteredProperties = propertyService.get(filter);
        assertThat(filteredProperties).hasSize(2);
        PropertyAssert.assertThat(filteredProperties.get(0)).hasRatingsCount(3);
        PropertyAssert.assertThat(filteredProperties.get(0)).hasAvarageRatingEqualTo(4.0);
        PropertyAssert.assertThat(filteredProperties.get(1)).hasRatingsCount(3);
        PropertyAssert.assertThat(filteredProperties.get(1)).hasAvarageRatingEqualTo(7.0);
    }






}
