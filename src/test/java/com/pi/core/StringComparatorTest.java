package com.pi.core;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class StringComparatorTest extends  TestCase{

    private List generateLists(String... strings)
    {
        List res = new ArrayList();
        for (String s: strings)
        {
            res.add(s);
        }
        return res;
    }
    @Test
    public void testGetFrequentChars() {
        String s1 = "my&friend&Paul has heavy hats! &";
        String s2 = "my friend John has many many friends &";
        StringComparator sc= new StringComparator();
        String res = sc.compareStrings(generateLists(s1,s2));
        //Test1
        assertEquals("2:nnnnn/1:aaaa/1:hhh/2:mmm/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss",res);
        //Test2
        s1 = "mmmmm m nnnnn y&friend&Paul has heavy hats! &";
        s2 = "my frie n d Joh n has ma n y ma n y frie n ds n&";
        res = sc.compareStrings(generateLists(s1,s2));
        assertEquals("1:mmmmmm/=:nnnnnn/1:aaaa/1:hhh/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss",res);
        //Test3
        s1="Are the kids at home? aaaaa fffff";
        s2="Yes they are here! aaaaa fffff";
        res = sc.compareStrings(generateLists(s1,s2));
        assertEquals( "=:aaaaaa/2:eeeee/=:fffff/1:tt/2:rr/=:hh",res);
        //Test4
        s1="Are the kids at home? aaaaa fffff";
        s2="Yes they are here! aaaaa fffff";
        String s3="56565656";
        res = sc.compareStrings(generateLists(s1,s2,s3));
        assertEquals( "1,2:aaaaaa/2:eeeee/1,2:fffff/1:tt/2:rr/1,2:hh",res);
    }
}
