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

    public static Messagebox createMessageBoxOk(String text) {
        Messagebox messagebox = new Messagebox();
        messagebox.setType("messageBoxOk");
        messagebox.setText(text);
        return messagebox;
    }

    public static Messagebox createMessageBoxOkImage(String title, String text, String url) {
        Messagebox messagebox = new Messagebox();
        messagebox.setType("messageBoxOkImage");
        messagebox.setTitleAndText(title, text);
        messagebox.setUrl(url);
        return messagebox;
    }

    public static Messagebox createMessageBoxSuccess(String title, String text) {
        Messagebox messagebox = new Messagebox();
        messagebox.setType("messageBoxSuccess");
        messagebox.setTitleAndText(title, text);
        return messagebox;
    }

    public static Messagebox createMessageBoxTimed(String title, String text) {
        Messagebox messagebox = new Messagebox();
        messagebox.setType("messageBoxTimed");
        messagebox.setTitleAndText(title, text);
        return messagebox;
    }

    public static Messagebox createMessageBoxError(String title, String text) {
        Messagebox messagebox = new Messagebox();
        messagebox.setType("messageBoxError");
        messagebox.setTitleAndText(title, text);
        return messagebox;
    }

    private String type;
    private String title;
    private String text;
    private String url;

    public void setTitleAndText(String title, String text) {
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
