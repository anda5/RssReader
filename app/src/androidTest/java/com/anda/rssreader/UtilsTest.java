package com.anda.rssreader;

import android.app.Application;
import android.content.Context;
import android.test.InstrumentationTestCase;

/**
 * Created by anda on 1/28/2015.
 */
public class UtilsTest extends InstrumentationTestCase {

    public void testConnectionToNetwork(){

       boolean checkConnection = Utils.connectedToNetwork(getInstrumentation().getTargetContext());
       assertTrue(checkConnection);
    }


}
