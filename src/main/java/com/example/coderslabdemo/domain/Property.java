package com.example.coderslabdemo.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

import static java.util.Objects.requireNonNull;
import static org.apache.logging.log4j.util.Strings.trimToNull;

@Entity
public class Property {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(nullable = false)
    private Integer areaSqM;

    @Column(nullable = false)
    private Integer rooms;

    @Embedded
    @NotNull
    private Address address;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PropertyType type;

    @Column
    private String additionalInfo;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Feedback> ratings;

    /*
     * Cannot be private for Hibernate support.
     */
    @SuppressWarnings("WeakerAccess")
    Property() {}

    public static Property of(final Integer areaSqM, final Integer rooms, final Address address, final PropertyType type, final String additionalInfo) {
        return of(areaSqM, rooms, address, type, additionalInfo, null);
    }

    public static Property of(final Integer areaSqM, final Integer rooms, final Address address, final PropertyType type, final String additionalInfo, final List<Feedback> ratings) {
        Property res = new Property();

        res.areaSqM = requireNonNull(areaSqM);
        res.type = requireNonNull(type);
        res.rooms = requireNonNull(rooms);
        res.address = requireNonNull(address);
        res.additionalInfo = trimToNull(additionalInfo);
        res.ratings = ratings;

        return res;
    }

    public Double getAvgRating() {
        return ratings.stream().map(Feedback::getRating).mapToDouble(v->v).average().orElse(0);
    }

    private static class RatingsComparator implements Comparator<Property> {
        @Override
        public int compare(Property a, Property b) {
            Double avgA = a.getAvgRating();
            Double avgB = b.getAvgRating();
            return avgA.compareTo(avgB);
        }
    }

    public static Comparator<Property> ratingsComparator() {
        return new RatingsComparator();
    }

    public Long getId() {
        return id;
    }

    public Integer getAreaSqM() {
        return areaSqM;
    }

    public void setAreaSqM(Integer areaSqM) {
        this.areaSqM = areaSqM;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<Feedback> getRatings() {
        return ratings;
    }

    public void setRatings(List<Feedback> ratings) {
        this.ratings = ratings;
    }
}
