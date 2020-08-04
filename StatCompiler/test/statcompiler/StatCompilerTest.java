/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statcompiler;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author djp217
 */
public class StatCompilerTest {
    
    public StatCompilerTest() {
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
     * Test of main method, of class StatCompiler.
     */

    /**
     * Test of averageOfPosInts method, of class StatCompiler.
     */
    @Test
    public void testAverageOfPosInts() {
        System.out.println("averageOfPosInts");
        int a = 1;
        int b = 2;
        int c = 3;
        int expResult = 2;
        int result = StatCompiler.averageOfPosInts(a, b, c);
       // System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of median method, of class StatCompiler.
     */
    @Test
    public void testMedian() {
        System.out.println("median");
        int a = 2;
        int b = 3;
        int c = 1;
        int expResult = 2;
        int result = StatCompiler.median(a, b, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
