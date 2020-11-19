package com.lambdaschool.foundation.models;

import javax.validation.constraints.Email;

/**
 * A model used to create a new user. The minimum information needed to create a user.
 * Note the role will default to USER.
 */
public class UserMinimum
{
    /**
     * The username (String)
     */
    private String username;

    /**
     * The user's password (String)
     */
    private String password;

    /**
     * The user's primary email address (String)
     */
    @Email
    private String primaryemail;

    /**
     * The user's first name (String)
     */
    private String firstName;


    /**
     * The user's last name (String)
     */
    private String lastName;


    /**
     * The user's phone number (String)
     */
    private String phone;

    /**
     * Getters and Setters
     */

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPrimaryemail()
    {
        return primaryemail;
    }

    public void setPrimaryemail(String primaryemail)
    {
        this.primaryemail = primaryemail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
