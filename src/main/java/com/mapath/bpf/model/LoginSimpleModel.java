package com.mapath.bpf.model;

/**
 * Created by Administrator on 2017/2/24.
 */
public class LoginSimpleModel {

    public static final Integer OK=0;
    private String name;
    private String password;
    private String messages;

    public LoginSimpleModel() {
    }

    public LoginSimpleModel(String name, String password, String messages) {
        this.name = name;
        this.password = password;
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public static Integer getOK() {
        return OK;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
