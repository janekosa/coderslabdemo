package com.example.coderslabdemo.persistance.model;

public class PropertyFilter {
    private Integer areaSqMFrom;
    private Integer areaSqMTo;
    private Integer roomsFrom;
    private Integer roomsTo;
    private String city;
    private Double ratingsFrom;
    private Property.Type type;

    public PropertyFilter() {

    }

    public Integer getAreaSqMFrom() {
        return areaSqMFrom;
    }

    public Integer getAreaSqMTo() {
        return areaSqMTo;
    }

    public Integer getRoomsFrom() {
        return roomsFrom;
    }

    public Integer getRoomsTo() {
        return roomsTo;
    }

    public String getCity() {
        return city;
    }

    public Double getRatingsFrom() {
        return ratingsFrom;
    }

    public Property.Type getType() {
        return type;
    }

    public void setAreaSqMFrom(Integer areaSqMFrom) {
        this.areaSqMFrom = areaSqMFrom;
    }

    public void setAreaSqMTo(Integer areaSqMTo) {
        this.areaSqMTo = areaSqMTo;
    }

    public void setRoomsFrom(Integer roomsFrom) {
        this.roomsFrom = roomsFrom;
    }

    public void setRoomsTo(Integer roomsTo) {
        this.roomsTo = roomsTo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRatingsFrom(Double ratingsFrom) {
        this.ratingsFrom = ratingsFrom;
    }

    public void setType(Property.Type type) {
        this.type = type;
    }

}
