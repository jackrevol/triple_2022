package com.triple.mileage.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EventAction {
    @JsonProperty("ADD") ADD,
    @JsonProperty("MOD") MOD,
    @JsonProperty("DELETE") DELETE
}
