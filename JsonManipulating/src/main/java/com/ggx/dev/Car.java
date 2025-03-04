package com.ggx.dev;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    @JsonProperty("the_model")
    private String model;
    //private String brand;
    private int year;
    @JsonIgnore
    private int key;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String gasPercentage;
    //private double efficiency;

}
