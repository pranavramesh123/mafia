/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.comm.server;

/**
 *
 * @author Just1689
 */
public class Messagebox extends ServerMessage {
    
    private String type;
    private String title;
    private String text;
    private String url;
    
    public void setAsMessageBoxOk(String text) {
        this.type = "messageBoxOk";
        this.text = text;
    }
    
    public void setAsMessageBoxOkImage(String title, String text, String url) {
        this.type = "messageBoxOkImage";
        setTitleAndText(title, text);
        this.url = url;
    }
    
    public void setAsMessageBoxSuccess(String title, String text) {
        this.type = "messageBoxSuccess";
        setTitleAndText(title, text);
    }
    
    public void setAsMessageBoxTimed(String title, String text) {
        this.type = "messageBoxTimed";
        setTitleAndText(title, text);
    }
    
    public void setAsMessageBoxError(String title, String text) {
        this.type = "messageBoxError";
        setTitleAndText(title, text);
    }
    
    private void setTitleAndText(String title, String text) {
        this.title = title;
        this.text = text;
        
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}
