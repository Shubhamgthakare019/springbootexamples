package com.java.bootdbdata.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Customer")
public class Customer {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Customer_Id")
    private String customerId;

    @Column(name = "Customer_Name")
    private String customerName;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
