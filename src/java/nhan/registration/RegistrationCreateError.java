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
public class RegistrationCreateError implements Serializable{
    private String usernameLengthError;
    private String passwordLengthError;
    private String confirmNotMacthed;
    private String fullnameLengthError;
    private String usernameIsExisted;

    public RegistrationCreateError() {
    }

    public RegistrationCreateError(String usernameLengthError, String passwordLengthError, String confirmNotMacthed, String fullnameLengthError, String usernameIsExisted) {
        this.usernameLengthError = usernameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.confirmNotMacthed = confirmNotMacthed;
        this.fullnameLengthError = fullnameLengthError;
        this.usernameIsExisted = usernameIsExisted;
    }
    /**
     * @return the usernameLengthError
     */
    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    /**
     * @param usernameLengthError the usernameLengthError to set
     */
    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the confirmNotMacthed
     */
    public String getConfirmNotMacthed() {
        return confirmNotMacthed;
    }

    /**
     * @param confirmNotMacthed the confirmNotMacthed to set
     */
    public void setConfirmNotMacthed(String confirmNotMacthed) {
        this.confirmNotMacthed = confirmNotMacthed;
    }

    /**
     * @return the fullnameLengthError
     */
    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    /**
     * @param fullnameLengthError the fullnameLengthError to set
     */
    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
