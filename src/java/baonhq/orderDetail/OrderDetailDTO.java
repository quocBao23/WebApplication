/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.orderDetail;

/**
 *
 * @author Admin
 */
public class OrderDetailDTO {
    private int id;
    private int productID;
    private float unitPrice;
    private int quantity;
    private String orderID;
    private float total;

    public OrderDetailDTO() {
        
    }

    public OrderDetailDTO(int id, int productID, float unitPrice, int quantity, String orderID, float total) {
        this.id = id;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.orderID = orderID;
        this.total = total;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
    
}
