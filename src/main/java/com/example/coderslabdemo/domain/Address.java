package com.example.coderslabdemo.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static java.util.Objects.requireNonNull;
import static org.apache.logging.log4j.util.Strings.trimToNull;


@Embeddable
public class Address {

    @Column(nullable = false)
    @Length(min = 1, max = 255)
    private String addressLine1;

    @Column
    @Length(max = 255)
    private String addressLine2;

    @Column(nullable = false)
    @Length(min = 5, max = 5)
    private String postcode;

    @Column(nullable = false)
    @Length(min = 1, max = 255)
    private String city;

    /*
     * Cannot be private for Hibernate support.
     */
    @SuppressWarnings("WeakerAccess")
    Address() {}

    public static Address of(final String addressLine1, final String addressLine2, final String postcode, final String city) {
        Address res = new Address();
        res.addressLine1 = requireNonNull(trimToNull(addressLine1));
        res.addressLine2 = trimToNull(addressLine2);
        res.postcode = requireNonNull(trimToNull(postcode));
        res.city = requireNonNull(trimToNull(city));
        return res;
    }


    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
