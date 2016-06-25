package client;

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
public class clientProfileTest {
    
    public clientProfileTest() {
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

   
    @Test
    public void testCheckEmail() {
        System.out.println("checkEmail");
        String em = "diploma@.buslyuk.petr";
        clientProfile instance = new clientProfile();
        boolean expResult = true;
        boolean result = instance.checkEmail(em);
        assertEquals(expResult, result);
    } 
}
