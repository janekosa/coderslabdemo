package com.example.coderslabdemo.assertion;

import com.example.coderslabdemo.persistance.model.Property;
import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class PropertyAssert extends AbstractAssert<PropertyAssert, Property> {

    private PropertyAssert(Property property) {
        super(property, PropertyAssert.class);
    }

    public static PropertyAssert assertThat(Property actual) {
        return new PropertyAssert(actual);
    }

    public PropertyAssert hasAreaSqM(Integer areaSqM) {
        isNotNull();

        if (!Objects.equals(actual.getAreaSqM(), areaSqM)) {
            failWithMessage("Expected areaSqM to be <%d> but was <%d>", areaSqM, actual.getAreaSqM());
        }

        return this;
    }

    public PropertyAssert hasRooms(Integer rooms) {
        isNotNull();

        if (!Objects.equals(actual.getRooms(), rooms)) {
            failWithMessage("Expected rooms to be <%d> but was <%d>", rooms, actual.getRooms());
        }

        return this;
    }

    public PropertyAssert hasCity(String city) {
        isNotNull();

        if (!Objects.equals(actual.getAddress().getCity(), city)) {
            failWithMessage("Expected city to be <%s> but was <%s>", city, actual.getAddress().getCity());
        }

        return this;
    }
    public PropertyAssert hasType(Property.Type type) {
        isNotNull();

        if (!Objects.equals(actual.getType(), type)) {
            failWithMessage("Expected type to be <%s> but was <%s>", type, actual.getType());
        }

        return this;
    }
    public PropertyAssert hasAvarageRatingEqualTo(Double averageRating) {
        isNotNull();

        if (!Objects.equals(averageRating,actual.getAvgRating())) {
            failWithMessage("Expected average rating to be <%d> but was <%d>", averageRating, actual.getAvgRating());
        }

        return this;
    }
    public PropertyAssert hasRatingsCount(Integer ratingsCount) {
        isNotNull();

        if (!Objects.equals(actual.getRatings().size(), ratingsCount)) {
            failWithMessage("Expected ratings count to be <%d> but was <%d>", ratingsCount, actual.getRatings().size());
        }

        return this;
    }


}
