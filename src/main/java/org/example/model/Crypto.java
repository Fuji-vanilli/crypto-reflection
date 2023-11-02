package org.example.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Crypto {
    private String id;
    private String name;
    private BigDecimal currentValue;

    public Crypto() {}
    public Crypto(String name, BigDecimal currentValue) {
        this.name= name;
        this.currentValue= currentValue;
    }
}
