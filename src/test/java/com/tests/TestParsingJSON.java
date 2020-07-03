package com.tests;

import com.domain.Person;
import com.parser.TestJSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class TestParsingJSON {
    TestJSONParser personObject;
    String fileName;


    @BeforeTest
    @Parameters({"testFileName"})
    public void setUp(String testFileName){
        System.out.println("RUNNING SETUP");
        personObject=new TestJSONParser();
        fileName=testFileName;
        System.out.println(fileName);
    }

    @Test
//    @Parameters({"testFileName", "totalPersons"})
    @Parameters({"totalPersons"})
    public void testParseJSONCount(String total) throws IOException, ParseException {
        List<Person> personList = personObject.parseJSONFile(fileName);
        Assert.assertEquals(Integer.valueOf(personList.size()).toString(),total);
    }

    @Test
//    @Parameters({ "testFileName", "personName" })
    @Parameters({ "personName" })
    public void testParseJSONName(String name) throws IOException, ParseException {

        List<Person> personList = personObject.parseJSONFile(fileName);
        Person testPerson=null;

        for( Person aPerson : personList){
            if(aPerson.getName().equals(name)){
                testPerson =  aPerson;
            }
        }
        if(testPerson!=null) {
            Assert.assertEquals(name, testPerson.getName());
        }else{
            Assert.assertTrue(false);
        }
    }
//
//    @Test
//    @Parameters("inputStreet")
//    public void testParseJSONStreet(String street) throws IOException, ParseException {
//        Iterator<Address> itrStreet;
//        itrStreet=(Address)personObject.parseJSONFile(street).iterator();
//
//        while( itrStreet.hasNext() )
//            System.out.println( itrStreet.next() );
//        Assert.assertEquals(street,itrStreet.equals(street));
//
//    }
}
