package com.snow.osmdemon;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @Author:snowle0pard on 16/6/1 19:59
 * @E-mial:snowle0pard@163.com
 * @describe:
 */
public class BaseApplication extends Application
{
    @Override
    public void onCreate( )
    {
        super.onCreate( );
        LeakCanary.install( this );
    }
}
