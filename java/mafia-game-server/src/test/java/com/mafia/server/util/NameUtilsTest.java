/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.util;

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
public class NameUtilsTest {

    public NameUtilsTest() {
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
     * Test of isNameOk method, of class NameUtils.
     */
    @Test
    public void testIsNameOk() {
        assertEquals(NameUtils.isNameOk(""), false);
        assertEquals(NameUtils.isNameOk(" "), false);
        assertEquals(NameUtils.isNameOk(null), false);
        assertEquals(NameUtils.isNameOk(" justin"), false);
        assertEquals(NameUtils.isNameOk("Justin"), true);
        assertEquals(NameUtils.isNameOk("Kevin"), true);
        assertEquals(NameUtils.isNameOk("KYLE"), false);
        assertEquals(NameUtils.isNameOk("Justin "), false);
        assertEquals(NameUtils.isNameOk("Justin1"), false);
        assertEquals(NameUtils.isNameOk("Justin!"), false);
    }

}
