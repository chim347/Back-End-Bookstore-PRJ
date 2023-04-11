/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.registration;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class RegistrationLoginError implements Serializable{
    private String userNameEmpty;
    private String passwordEmpty;
    private String acountNotFound;

    public RegistrationLoginError() {
    }

    public RegistrationLoginError(String userNameEmpty, String passwordEmpty, String acountNotFound) {
        this.userNameEmpty = userNameEmpty;
        this.passwordEmpty = passwordEmpty;
        this.acountNotFound = acountNotFound;
    }
    
    /**
     * @return the userNameEmpty
     */
    public String getUserNameEmpty() {
        return userNameEmpty;
    }

    /**
     * @param userNameEmpty the userNameEmpty to set
     */
    public void setUserNameEmpty(String userNameEmpty) {
        this.userNameEmpty = userNameEmpty;
    }

    /**
     * @return the passwordEmpty
     */
    public String getPasswordEmpty() {
        return passwordEmpty;
    }

    /**
     * @param passwordEmpty the passwordEmpty to set
     */
    public void setPasswordEmpty(String passwordEmpty) {
        this.passwordEmpty = passwordEmpty;
    }

    /**
     * @return the acountNotFound
     */
    public String getAcountNotFound() {
        return acountNotFound;
    }

    /**
     * @param acountNotFound the acountNotFound to set
     */
    public void setAcountNotFound(String acountNotFound) {
        this.acountNotFound = acountNotFound;
    }
    
    
}
