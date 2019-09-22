package com.example.coderslabdemo.rest.dto;

import java.util.List;

public class PropertyDto {
    private Long id;
    private Integer areaSqM;
    private Integer rooms;
    private String addressLine1;
    private String addressLine2;
    private String postcode;
    private String city;
    private String type;
    private String additionalInfo;
    private Double avgRating;
    private Integer ratingsCount;
    private List<FeedbackDto> ratings;


    public PropertyDto(final Long id, final Integer areaSqM, final Integer rooms, final String addressLine1, final String addressLine2,
                       final String postcode, final String city, final String type, final String additionalInfo, final Double avgRating,
                       final Integer ratingsCount) {
        this.id = id;
        this.areaSqM = areaSqM;
        this.rooms = rooms;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postcode = postcode;
        this.city = city;
        this.type = type;
        this.additionalInfo = additionalInfo;
        this.avgRating = avgRating;
        this.ratingsCount = ratingsCount;
    }

    public Long getId() {
        return id;
    }

    public Integer getAreaSqM() {
        return areaSqM;
    }

    public Integer getRooms() {
        return rooms;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatings(List<FeedbackDto> ratings) {
        this.ratings = ratings;
    }

    public List<FeedbackDto> getRatings() {
        return ratings;
    }
}
