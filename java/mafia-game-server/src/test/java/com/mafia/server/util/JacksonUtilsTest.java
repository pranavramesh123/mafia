/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.util;

import com.mafia.server.model.comm.server.ChatMessage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Just1689
 */
public class JacksonUtilsTest {

    public JacksonUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of stringToObject method, of class JacksonUtils.
     */
    @Test
    public void testStringToObject() {
        String json = "{\"event\":\"ChatMessage\",\"player\":\"Zog\",\"message\":\"11\"}";
        Class clazz = ChatMessage.class;
        JacksonUtils<ChatMessage> instance = new JacksonUtils();
        ChatMessage result = instance.stringToObject(json, clazz);
        assertEquals(result.getEvent(), "ChatMessage");
        assertEquals(result.getPlayer(), "Zog");
        assertEquals(result.getMessage(), "11");
    }

    /**
     * Test of objectToString method, of class JacksonUtils.
     */
    @Test
    public void testObjectToString() {
        ChatMessage chatMessage = new ChatMessage("Zog", "11");
        chatMessage.setEvent("ChatMessage");
        String result = JacksonUtils.objectToString(chatMessage);
        assertEquals("{\"event\":\"ChatMessage\",\"player\":\"Zog\",\"message\":\"11\"}", result);
    }

}
