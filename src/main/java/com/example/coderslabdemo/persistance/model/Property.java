package com.example.coderslabdemo.persistance.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Property.Type type;

    @Column
    private String additionalInfo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Feedback> ratings = new HashSet<>();

    /*
     * Cannot be private for Hibernate support.
     */
    @SuppressWarnings("WeakerAccess")
    Property() {}

    public static Property of(final Integer areaSqM, final Integer rooms, final Address address, final Type type, final String additionalInfo, final Set<Feedback> ratings) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Set<Feedback> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Feedback> ratings) {
        this.ratings = ratings;
    }

    public enum Type {
        ROOM,
        HOUSE,
        FLAT;

        public static Set<String> stringValues() {
            return Arrays.stream(Type.values()).map(Type::toString).collect(Collectors.toSet());
        }
    }
}
