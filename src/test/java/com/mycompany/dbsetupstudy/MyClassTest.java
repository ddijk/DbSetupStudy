
package com.mycompany.dbsetupstudy;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dickdijk
 */
public class MyClassTest {
    
    public MyClassTest() {
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
    
     @Before
    public void prepare() throws Exception {
             String createProc = "create or replace procedure abc_dbsetup as \nbegin delete from job; end abc_dbsetup;";
    
        Operation operation =
            sequenceOf(
             Operations.deleteAllFrom("JOB"),
//                CommonOperations.INSERT_REFERENCE_DATA,
                insertInto("JOB")
                    .columns("ID", "NAME", "SCHEDULE")
                    .values(1L, "AMA", "Amazon")
                    .values(2L, "PMI", "PriceMinister")
                        
                    .build(), Operations.sql(createProc));
                    
          //  DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
            // or without DataSource:
            // DbSetup dbSetup = new DbSetup(new DriverManagerDestination(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD), operation);
       
        
//        String TEST_DB_URL="jdbc:derby://localhost:1527/pearl1";
//         String TEST_DB_USER="dick";
//         String TEST_DB_PASSWORD ="dick";
        
           String TEST_DB_URL="jdbc:oracle:thin:@//Parijs:1521/orcl";
        String TEST_DB_USER="oe";
        String TEST_DB_PASSWORD ="oracle";
         
         
                 DbSetup dbSetup = new DbSetup(new DriverManagerDestination(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD), operation);
            dbSetup.launch();
        }
    
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
       
        
        Assert.assertEquals("My first test", true, false);
    }
    
}
