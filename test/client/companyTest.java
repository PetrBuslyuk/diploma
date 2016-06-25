/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class companyTest {
    
    public companyTest() {
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
     * Test of set_name method, of class company.
     */
    @Test
    public void testSet_name() {
        System.out.println("set_name");
        String _name = "gugkh";
        company instance = new company();
        instance.set_name(_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_depo method, of class company.
     */
    @Test
    public void testSet_depo() {
        System.out.println("set_depo");
        String _depo = "hulhj";
        company instance = new company();
        instance.set_depo(_depo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_persent method, of class company.
     */
    @Test
    public void testSet_persent() {
        System.out.println("set_persent");
        String _persent = "76";
        company instance = new company();
        instance.set_persent(_persent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_period method, of class company.
     */
    @Test
    public void testSet_period() {
        System.out.println("set_period");
        String _period = "7";
        company instance = new company();
        instance.set_period(_period);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_plus method, of class company.
     */
    @Test
    public void testSet_plus_String() {
        System.out.println("set_plus");
        String _plus = "87";
        company instance = new company();
        instance.set_plus(_plus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_minus method, of class company.
     */
    @Test
    public void testSet_minus_String() {
        System.out.println("set_minus");
        String _minus = "";
        company instance = new company();
        instance.set_minus(_minus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_reinvesting method, of class company.
     */
    @Test
    public void testSet_reinvesting_String() {
        System.out.println("set_reinvesting");
        String _reinvesting = "";
        company instance = new company();
        instance.set_reinvesting(_reinvesting);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_plus method, of class company.
     */
    @Test
    public void testSet_plus_int_String() {
        System.out.println("set_plus");
        int i = 0;
        String _plus = "";
        company instance = new company();
        instance.set_plus(i, _plus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_minus method, of class company.
     */
    @Test
    public void testSet_minus_int_String() {
        System.out.println("set_minus");
        int i = 0;
        String _minus = "";
        company instance = new company();
        instance.set_minus(i, _minus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_reinvesting method, of class company.
     */
    @Test
    public void testSet_reinvesting_int_String() {
        System.out.println("set_reinvesting");
        int i = 0;
        String _reinvesting = "";
        company instance = new company();
        instance.set_reinvesting(i, _reinvesting);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_depofirst method, of class company.
     */
    @Test
    public void testSet_depofirst() {
        System.out.println("set_depofirst");
        int i = 0;
        double df = 0.0;
        company instance = new company();
        instance.set_depofirst(i, df);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_depolast method, of class company.
     */
    @Test
    public void testSet_depolast() {
        System.out.println("set_depolast");
        int i = 0;
        double dl = 0.0;
        company instance = new company();
        instance.set_depolast(i, dl);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_resultbuffer method, of class company.
     */
    @Test
    public void testSet_resultbuffer() {
        System.out.println("set_resultbuffer");
        int i = 0;
        double rb = 0.0;
        company instance = new company();
        instance.set_resultbuffer(i, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_depolast method, of class company.
     */
    @Test
    public void testGet_depolast_0args() {
        System.out.println("get_depolast");
        company instance = new company();
        ArrayList expResult = null;
        ArrayList result = instance.get_depolast();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_depolast method, of class company.
     */
    @Test
    public void testGet_depolast_int() {
        System.out.println("get_depolast");
        int i = 0;
        company instance = new company();
        double expResult = 0.0;
        double result = instance.get_depolast(i);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_depofirst method, of class company.
     */
    @Test
    public void testGet_depofirst_int() {
        System.out.println("get_depofirst");
        int i = 0;
        company instance = new company();
        double expResult = 0.0;
        double result = instance.get_depofirst(i);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_resultbuffer method, of class company.
     */
    @Test
    public void testGet_resultbuffer() {
        System.out.println("get_resultbuffer");
        int i = 0;
        company instance = new company();
        double expResult = 0.0;
        double result = instance.get_resultbuffer(i);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_depofirst method, of class company.
     */
    @Test
    public void testGet_depofirst_0args() {
        System.out.println("get_depofirst");
        company instance = new company();
        ArrayList expResult = null;
        ArrayList result = instance.get_depofirst();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_name method, of class company.
     */
    @Test
    public void testGet_name() {
        System.out.println("get_name");
        company instance = new company();
        String expResult = "";
        String result = instance.get_name();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_persent method, of class company.
     */
    @Test
    public void testGet_persent() {
        System.out.println("get_persent");
        company instance = new company();
        String expResult = "";
        String result = instance.get_persent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_period method, of class company.
     */
    @Test
    public void testGet_period() {
        System.out.println("get_period");
        company instance = new company();
        String expResult = "";
        String result = instance.get_period();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_depo method, of class company.
     */
    @Test
    public void testGet_depo() {
        System.out.println("get_depo");
        company instance = new company();
        String expResult = "";
        String result = instance.get_depo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_plus method, of class company.
     */
    @Test
    public void testGet_plus_int() {
        System.out.println("get_plus");
        int i = 0;
        company instance = new company();
        String expResult = "";
        String result = instance.get_plus(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_minus method, of class company.
     */
    @Test
    public void testGet_minus_int() {
        System.out.println("get_minus");
        int i = 0;
        company instance = new company();
        String expResult = "";
        String result = instance.get_minus(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_reinvesting method, of class company.
     */
    @Test
    public void testGet_reinvesting_int() {
        System.out.println("get_reinvesting");
        int i = 0;
        company instance = new company();
        boolean expResult = false;
        boolean result = instance.get_reinvesting(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_plus method, of class company.
     */
    @Test
    public void testGet_plus_0args() {
        System.out.println("get_plus");
        company instance = new company();
        ArrayList expResult = null;
        ArrayList result = instance.get_plus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_minus method, of class company.
     */
    @Test
    public void testGet_minus_0args() {
        System.out.println("get_minus");
        company instance = new company();
        ArrayList expResult = null;
        ArrayList result = instance.get_minus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_reinvesting method, of class company.
     */
    @Test
    public void testGet_reinvesting_0args() {
        System.out.println("get_reinvesting");
        company instance = new company();
        ArrayList expResult = null;
        ArrayList result = instance.get_reinvesting();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_company_to_string method, of class company.
     */
    @Test
    public void testGet_company_to_string() {
        System.out.println("get_company_to_string");
        company instance = new company();
        String expResult = "";
        String result = instance.get_company_to_string();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_company_to_object method, of class company.
     */
    @Test
    public void testGet_company_to_object() {
        System.out.println("get_company_to_object");
        company instance = new company();
        Object[] expResult = null;
        Object[] result = instance.get_company_to_object();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_company_to_array_in_report method, of class company.
     */
    @Test
    public void testGet_company_to_array_in_report() {
        System.out.println("get_company_to_array_in_report");
        company instance = new company();
        String[] expResult = null;
        String[] result = instance.get_company_to_array_in_report();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCalculateCompany method, of class company.
     */
    @Test
    public void testGetCalculateCompany() {
        System.out.println("getCalculateCompany");
        company instance = new company();
        company expResult = null;
        company result = instance.getCalculateCompany();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of log method, of class company.
     */
    @Test
    public void testLog() {
        System.out.println("log");
        Object o = null;
        company.log(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
