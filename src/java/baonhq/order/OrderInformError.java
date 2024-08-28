/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.order;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class OrderInformError implements Serializable{
    private String userNameError;
    private String userAddressError;
    private String userEmailFormatError; 

   
    
    
    

    public OrderInformError() {
    }
    
    

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getUserAddressError() {
        return userAddressError;
    }

    public void setUserAddressError(String userAddressError) {
        this.userAddressError = userAddressError;
    }

     public String getUserEmailFormatError() {
        return userEmailFormatError;
    }

    public void setUserEmailFormatError(String userEmailFormatError) {
        this.userEmailFormatError = userEmailFormatError;
    }
    
    
}
