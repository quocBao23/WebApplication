/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.cart;

import baonhq.product.ProductDAO;
import baonhq.product.ProductDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class CartBean implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String item, int quantity){
        if ( item == null){
            return;
        }
        if ( item.trim().isEmpty() ){
            return;
        }
        //1. check exitsted items/cart
        if (this.items == null){
            this.items = new HashMap<>();
        }
        
        //2. check exitested item
        int itemQuantity = quantity;
        if (this.items.containsKey(item)){
            itemQuantity = this.items.get(item) + quantity;
        }
        //3. drop item to items
        this.items.put(item, itemQuantity);
    }
    
    public void removeItemFromCart(String item){
        if ( item == null){
            return;
        }
        if ( item.trim().isEmpty() ){
            return;
        }
        //1. check existed items
        if ( this.items == null){
            return;
        }
        //2. check exitsted item
        if ( this.items.containsKey(item)){
            //3. remove
            this.items.remove(item);
            if ( this.items.isEmpty()){
                this.items = null;
            }
        }
        
    }

    public float totalPayment(CartBean cart) throws SQLException, NamingException{
        float total = 0;
        //get each key
        for (String key : cart.getItems().keySet()) {
            ProductDTO dto = null;
            ProductDAO dao = new ProductDAO();
            //get dto
            dto = dao.getProduct(key);
            //total = price * quantity
            total += dto.getPrice() * cart.getItems().get(key);
        }
        return total ;
    }
   
    
}
