package com.example.coderslabdemo;


import com.example.coderslabdemo.dao.PropertyRepository;
import com.example.coderslabdemo.domain.Address;
import com.example.coderslabdemo.domain.Feedback;
import com.example.coderslabdemo.domain.Property;
import com.example.coderslabdemo.domain.PropertyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataUtils {

    @Autowired
    private PropertyRepository propertyRepository;


    public void loadTestData() {

        Address dummyAddress1 = dummyAddress(CITY1);
        Address dummyAddress2 = dummyAddress(CITY2);
        dummyFeedbacks4 = dummyFeedbacks(3, 4, 5);
        dummyFeedbacks7 = dummyFeedbacks(6, 7, 8);
        dummyFeedbacks0 = dummyFeedbacks();

        propertyFlat100Roo5Add1Fee4 = Property.of(160, 5, dummyAddress1, PropertyType.FLAT, null, dummyFeedbacks4);
        propertyHouse160Roo3Add1Fee0 = Property.of(100, 3, dummyAddress2, PropertyType.HOUSE, null, dummyFeedbacks0);
        propertyRoom20Add1Fee7 = Property.of(20, 1, dummyAddress2, PropertyType.HOUSE, null, dummyFeedbacks7);


        propertyRepository.save(propertyFlat100Roo5Add1Fee4);
        propertyRepository.save(propertyHouse160Roo3Add1Fee0);
        propertyRepository.save(propertyRoom20Add1Fee7);

    }

    public void cleanTestData() {
        propertyRepository.deleteAll();
    }


    public static final String CITY1 = "city1";
    public static final String CITY2 = "city2";

    public Property propertyFlat100Roo5Add1Fee4;
    public Property propertyHouse160Roo3Add1Fee0;
    public Property propertyRoom20Add1Fee7;


    public List<Feedback> dummyFeedbacks4;
    public List<Feedback> dummyFeedbacks7;
    public List<Feedback> dummyFeedbacks0;


    private static Address dummyAddress(String city) {
        return Address.of("dummy address line 1", null, "01-234", city);
    }

    private static List<Feedback> dummyFeedbacks(Integer... ratings) {
        List<Feedback> res = new ArrayList<>(ratings.length);
        for (Integer rating : ratings) {
            res.add(Feedback.of(rating, "some dummy feedback", "some dummy signature"));
        }
        return res;
    }
}
