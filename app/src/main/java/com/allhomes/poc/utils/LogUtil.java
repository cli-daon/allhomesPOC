package com.allhomes.poc.utils;

import android.util.Log;

import com.allhomes.poc.BuildConfig;

/**
 * Created by cli on 12/01/2017.
 */

public class LogUtil
{
    public static final boolean DEBUG= BuildConfig.DEBUG;

    public static void v(String tag, String msg)
    {
        if(DEBUG)
        {
            if(msg != null)
            {
                Log.v(tag, msg);
            }
            else
            {
                Log.v(tag, "is NULL");
            }
        }
    }

    public static void d(String tag, String msg)
    {
        if(DEBUG)
        {
            if(msg != null)
            {
                Log.d(tag, msg);
            }
            else
            {
                Log.d(tag, "is NULL");
            }
        }
    }

    public static void i(String tag, String msg)
    {
        if(DEBUG)
        {
            if(msg != null)
            {
                Log.i(tag, msg);
            }
            else
            {
                Log.i(tag, "is NULL");
            }
        }
    }

    public static void w(String tag, String msg)
    {
        if(DEBUG)
        {
            if(msg != null)
            {
                Log.w(tag, msg);
            }
            else
            {
                Log.w(tag, "is NULL");
            }
        }
    }

    public static void e(String tag, String msg)
    {
        if (DEBUG)
        {
            if (msg != null)
            {
                Log.e(tag, msg);
            }
            else
            {
                Log.e(tag, "is NULL");
            }
        }
    }
}
