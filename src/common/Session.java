/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author gvt48
 */
public class Session {
    
    public static final String LOGIN_TYPE_ORGANISER = "organiser";
    public static final String LOGIN_TYPE_USER = "user";
    
    private int myID;
    private String loginType;
    private String name;
    private double money;
    
    public int getMyID() {
        return this.myID;
    }
    
    public void setMyID(int id) {
        this.myID = id;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
}
