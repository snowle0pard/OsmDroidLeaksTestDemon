package com.snow.osmdemon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * @Author:snowle0pard on 16/5/14 11:14
 * @E-mial:snowle0pard@163.com
 * @describe:
 */
public class MainActivity extends Activity
{
    private boolean iscontinual = true;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Button _mapBtn = ( Button ) findViewById( R.id.btn_start_map );
        _mapBtn.setOnClickListener( new View.OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                Intent _intent = new Intent( getApplicationContext( ), MapActivity.class );
                startActivityForResult( _intent, 3 );
            }
        } );
        Button _startMapBtn = ( Button ) findViewById( R.id.btn_stop_map );
        _startMapBtn.setOnClickListener( new View.OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                iscontinual = !iscontinual;
            }
        } );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        if ( iscontinual )
        {
            Handler _handler = new Handler( );
            _handler.postDelayed( new Runnable( )
            {
                @Override
                public void run( )
                {
                    Intent _intent = new Intent( getApplicationContext( ), MapActivity.class );
                    startActivityForResult( _intent, 3 );
                }
            }, 1000 );
        }
    }
}
