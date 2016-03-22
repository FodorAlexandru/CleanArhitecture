package com.example.shade_000.cleanarhitecture.data.models;

import com.j256.ormlite.field.DatabaseField;

import common.base.ModelBase;

/**
 * Created by shade_000 on 3/20/2016.
 */
public class User extends ModelBase{
    //region Fields
    @DatabaseField (canBeNull = true)
    String email;
    @DatabaseField(canBeNull = true)
    String alias;
    @DatabaseField(canBeNull = true)
    String address;
    @DatabaseField(canBeNull = true)
    String phoneNumber;
    //endregion

    //region Constructors

    public User() {
    }

    public User(String email, String alias, String address, String phoneNumber) {
        this.email = email;
        this.alias = alias;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //endregion

    //region Get Methods

    public String getEmail() {
        return email;
    }

    public String getAlias() {
        return alias;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    //endregion

    //region Set Methods

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    //endregion
}
