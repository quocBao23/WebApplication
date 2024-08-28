/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.registration;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationCreateError implements Serializable{
    private String userNameLength;
    private String userPasswordLength;
    private String userFullNameLength;
    private String confirmNotMatched;
    private String userNameIsExisted;

    public RegistrationCreateError() {
    }
    
    

    public String getUserNameLength() {
        return userNameLength;
    }

    public void setUserNameLength(String userNameLength) {
        this.userNameLength = userNameLength;
    }

    public String getUserPasswordLength() {
        return userPasswordLength;
    }

    public void setUserPasswordLength(String userPasswordLength) {
        this.userPasswordLength = userPasswordLength;
    }

    public String getUserFullNameLength() {
        return userFullNameLength;
    }

    public void setUserFullNameLength(String userFullNameLength) {
        this.userFullNameLength = userFullNameLength;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getUserNameIsExisted() {
        return userNameIsExisted;
    }

    public void setUserNameIsExisted(String userNameIsExisted) {
        this.userNameIsExisted = userNameIsExisted;
    }
    
    
}
