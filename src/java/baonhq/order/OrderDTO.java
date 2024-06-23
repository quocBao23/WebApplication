/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class OrderDTO implements Serializable{
    private String orderID;
    private Date date;
    private String customer;
    private String address;
    private String email;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, Date date, String customer, String address, String email, float total) {
        this.orderID = orderID;
        this.date = date;
        this.customer = customer;
        this.address = address;
        this.email = email;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
