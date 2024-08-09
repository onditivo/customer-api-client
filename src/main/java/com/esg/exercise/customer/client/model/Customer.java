package com.esg.exercise.customer.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Customer(
        @JsonProperty("customer_ref") String customerRef,
        @JsonProperty("customer_name") String customerName,
        @JsonProperty("address_line_1") String addressLine1,
        @JsonProperty("address_line_2") String addressLine2,
        @JsonProperty("town") String town,
        @JsonProperty("county") String county,
        @JsonProperty("country") String country,
        @JsonProperty("postcode") String postcode) {
}