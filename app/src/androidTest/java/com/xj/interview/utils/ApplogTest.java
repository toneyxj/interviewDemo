package com.xj.interview.utils;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ApplogTest extends TestCase {
    @Test
    public void TestLog() {
        // Context of the app under test.
        Applog.e("打印一下....");
    }
}