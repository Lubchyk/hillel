package com.hillel.finalWork.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transaction {
    private String name;
    private BigDecimal price;
    private Date created;
    private Status status;

}
