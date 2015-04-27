package com.anda.rssreader;

import android.app.Application;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.widget.EditText;

/**
 * Created by anda on 1/28/2015.
 */
public class UtilsTest extends InstrumentationTestCase {

    public void testConnectionToNetwork(){

       boolean checkConnection = Utils.connectedToNetwork(getInstrumentation().getTargetContext());
       assertTrue(checkConnection);
    }
    public void testFullyQualifiedName(){
        String test = "facebook.com";
        String text = Utils.fullyQualifiedName(test);
        assertEquals("www.facebook.com",text);
    }


}
