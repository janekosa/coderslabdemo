package com.example.coderslabdemo.domain;

public class PropertyFilter {
    private final Integer areaSqMFrom;
    private final Integer areaSqMTo;
    private final Integer roomsFrom;
    private final Integer roomsTo;
    private final String city;
    private final Double ratingsFrom;
    private final PropertyType type;

    private PropertyFilter(final Integer areaSqMFrom, final Integer areaSqMTo, final Integer roomsFrom, final Integer roomsTo, final String city, final Double ratingsFrom, final PropertyType type) {
        this.areaSqMFrom = areaSqMFrom;
        this.areaSqMTo = areaSqMTo;
        this.roomsFrom = roomsFrom;
        this.roomsTo = roomsTo;
        this.city = city;
        this.ratingsFrom = ratingsFrom;
        this.type = type;
    }

    public static Builder builder() {
        return new Builder();
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

    public PropertyType getType() {
        return type;
    }

    public static class Builder {
        private Integer areaSqMFrom;
        private Integer areaSqMTo;
        private Integer roomsFrom;
        private Integer roomsTo;
        private String city;
        private Double ratingsFrom;
        private PropertyType type;

        public Builder withSqMFrom(Integer areaSqMFrom) {
            this.areaSqMFrom = areaSqMFrom;
            return this;
        }

        public Builder withAreaSqMTo(Integer areaSqMTo) {
            this.areaSqMTo = areaSqMTo;
            return this;
        }

        public Builder withRoomsFrom(Integer roomsFrom) {
            this.roomsFrom = roomsFrom;
            return this;
        }

        public Builder withRoomsTo(Integer roomsTo) {
            this.roomsTo = roomsTo;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withRatingsFrom(Double ratingsFrom) {
            this.ratingsFrom = ratingsFrom;
            return this;
        }

        public Builder withType(PropertyType type) {
            this.type = type;
            return this;
        }

        public PropertyFilter build() {
            return new PropertyFilter(areaSqMFrom, areaSqMTo, roomsFrom, roomsTo, city, ratingsFrom, type);
        }

    }

}
