package com.example.coderslabdemo;

import com.example.coderslabdemo.assertion.PropertyAssert;
import com.example.coderslabdemo.persistance.dao.PropertyRepository;
import com.example.coderslabdemo.persistance.model.Property;
import com.example.coderslabdemo.persistance.model.PropertyFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyFilteringTest {

    @Autowired
    private TestDataUtils dataUtils;

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
        PropertyFilter filter = new PropertyFilter();
        filter.setCity(TestDataUtils.CITY1);
        List<Property> filteredProperties = propertyRepository.findAll(PropertyRepository.Specs.getFromFilter(filter));
        assertThat(filteredProperties).hasSize(1);
        PropertyAssert.assertThat(filteredProperties.get(0)).hasCity(TestDataUtils.CITY1);
    }

    @Test
    public void testFilterByArea() {
        PropertyFilter filter = new PropertyFilter();
        filter.setAreaSqMFrom(90);
        filter.setAreaSqMTo(110);
        List<Property> filteredProperties = propertyRepository.findAll(PropertyRepository.Specs.getFromFilter(filter));
        assertThat(filteredProperties).hasSize(1);
        PropertyAssert.assertThat(filteredProperties.get(0)).hasAreaSqM(100);
    }

    @Test
    public void testFilterByRooms() {
        PropertyFilter filter = new PropertyFilter();
        filter.setRoomsFrom(3);
        filter.setRoomsTo(5);
        List<Property> filteredProperties = propertyRepository.findAll(PropertyRepository.Specs.getFromFilter(filter));
        assertThat(filteredProperties).hasSize(2);
        filteredProperties.sort(Comparator.comparing(Property::getRooms));
        PropertyAssert.assertThat(filteredProperties.get(0)).hasRooms(3);
        PropertyAssert.assertThat(filteredProperties.get(1)).hasRooms(5);
    }

    @Test
    public void testFilterByRatings() {
        PropertyFilter filter = new PropertyFilter();
        filter.setRatingsFrom(3.9);
        List<Property> filteredProperties = propertyRepository.findAll(PropertyRepository.Specs.getFromFilter(filter));
        assertThat(filteredProperties).hasSize(2);
        PropertyAssert.assertThat(filteredProperties.get(0)).hasRatingsCount(3);
        PropertyAssert.assertThat(filteredProperties.get(0)).hasAvarageRatingEqualTo(4.0);
        PropertyAssert.assertThat(filteredProperties.get(1)).hasRatingsCount(3);
        PropertyAssert.assertThat(filteredProperties.get(1)).hasAvarageRatingEqualTo(7.0);
    }

    @Test
    public void testFilterByType() {
        PropertyFilter filter = new PropertyFilter();
        filter.setType(Property.Type.HOUSE);
        List<Property> filteredProperties = propertyRepository.findAll(PropertyRepository.Specs.getFromFilter(filter));
        assertThat(filteredProperties).hasSize(2);
        for (Property p : filteredProperties) {
            PropertyAssert.assertThat(p).hasType(Property.Type.HOUSE);
        }

    }

}
