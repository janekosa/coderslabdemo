package com.example.coderslabdemo.rest.dto;

public class FeedbackDto {
    private Long id;
    private Integer rating;
    private String additionalInfo;
    private String signature;

    FeedbackDto() {

    }

    public FeedbackDto(final Long id, final Integer rating, final String additionalInfo, final String signature) {
        this.id = id;
        this.rating = rating;
        this.additionalInfo = additionalInfo;
        this.signature = signature;
    }

    public Long getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getSignature() {
        return signature;
    }
}
