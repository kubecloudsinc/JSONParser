package com.parser;

import com.domain.Address;
import com.domain.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestJSONParser {

    public List<Person> parseJSONFile(String fileName) throws IOException, ParseException{

        List<Person> persons = new ArrayList<>();

            JSONParser aParser = new JSONParser();
//            InputStream inputStream = new TestJSONParser().getClass().getResourceAsStream(fileName);
            File aFile = new File(fileName);
            Reader aFileReader = new FileReader(aFile);
            JSONObject aJSONObject = (JSONObject) aParser.parse(aFileReader);
            JSONArray aJSONArray = (JSONArray) aJSONObject.get("persons");
            Iterator<JSONObject> iterator = aJSONArray.iterator();
            Person aPerson;
            Address aAddress;

            while(iterator.hasNext()){
                aPerson = new Person();
                JSONObject personJSON =(JSONObject)iterator.next();
                aPerson.setName((String) personJSON.get("name"));
                aPerson.setOccupation((String) personJSON.get("occupation"));

                aAddress = new Address();
                JSONObject addressJSON =(JSONObject) personJSON.get("address");
                aAddress.setStreet((String) addressJSON.get("street"));
                aAddress.setCity((String) addressJSON.get("city"));
                aAddress.setProvince((String) addressJSON.get("province"));

                aPerson.setAddress(aAddress);
                persons.add(aPerson);
            }
        return persons;
    }
}
