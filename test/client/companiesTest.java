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
public class companiesTest {
    
    public companiesTest() {
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
     * Test of log method, of class companies.
     */
    @Test
    public void testLog() {
        System.out.println("log");
        Object o = null;
        companies.log(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of input method, of class companies.
     */
    @Test
    public void testInput() {
        System.out.println("input");
        String title = "";
        String massage = "";
        String expResult = "";
        String result = companies.input(title, massage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of show method, of class companies.
     */
    @Test
    public void testShow() {
        System.out.println("show");
        String massage = "";
        companies.show(massage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculate_selected method, of class companies.
     */
    @Test
    public void testCalculate_selected() {
        System.out.println("calculate_selected");
        int[] sr = null;
        String[] expResult = null;
        String[] result = companies.calculate_selected(sr);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCompanies method, of class companies.
     */
    @Test
    public void testDeleteCompanies() {
        System.out.println("deleteCompanies");
        companies.deleteCompanies();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_current_dir method, of class companies.
     */
    @Test
    public void testGet_current_dir() {
        System.out.println("get_current_dir");
        String expResult = "";
        String result = companies.get_current_dir();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save_companies method, of class companies.
     */
    @Test
    public void testSave_companies() {
        System.out.println("save_companies");
        companies.save_companies();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_updated_company method, of class companies.
     */
    @Test
    public void testSet_updated_company() {
        System.out.println("set_updated_company");
        company c1 = null;
        companies.set_updated_company(c1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of show_companies method, of class companies.
     */
    @Test
    public void testShow_companies() {
        System.out.println("show_companies");
        companies.show_companies();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showSelectedCompany method, of class companies.
     */
    @Test
    public void testShowSelectedCompany() {
        System.out.println("showSelectedCompany");
        String selectedCName = "";
        companies.showSelectedCompany(selectedCName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class companies.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        companies.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompaniesToObject method, of class companies.
     */
    @Test
    public void testGetCompaniesToObject() {
        System.out.println("getCompaniesToObject");
        ArrayList<company> expResult = null;
        ArrayList<company> result = companies.getCompaniesToObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompanies method, of class companies.
     */
    @Test
    public void testSetCompanies() {
        System.out.println("setCompanies");
        companiesToSent v = null;
        companies.setCompanies(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
