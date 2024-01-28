package com.EmreAydin.ecommerceapi.Api.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductBody {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String shortDescription;

    @NotNull
    @NotBlank
    private String longDescription;

    @Positive
    @NotNull
    private Double price;

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public Double getPrice() {
        return price;
    }
}
