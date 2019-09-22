package com.example.coderslabdemo.persistance.model;

import com.google.common.base.Preconditions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static org.apache.logging.log4j.util.Strings.trimToNull;


@Entity
public class Feedback {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(nullable = false)
    @Min(1)
    @Max(10)
    private Integer rating;

    @Column
    private String additionalFeedback;

    /**
     * In a more developed version of this app this would be a relation
     * to {@code User} class (which I'm not implementing).
     */
    @Column(nullable = false)
    private String signature;

    public static Feedback of(final Integer rating, final String additionalFeedback, final String signature) {
        Preconditions.checkArgument(rating != null && rating >= 1 && rating <= 10, "Rating not in range 1-10");
        Feedback res = new Feedback();
        res.rating = rating;
        res.additionalFeedback = trimToNull(additionalFeedback);
        res.signature = trimToNull(signature);

        return res;
    }

    public Long getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getAdditionalFeedback() {
        return additionalFeedback;
    }

    public String getSignature() {
        return signature;
    }
}
