package com.snow.osmdemon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapActivity extends AppCompatActivity
{

    private MyLocationNewOverlay mMyLocationNewOverlay;
    private MapView mMapView;
    private ScaleBarOverlay mScaleBarOverlay;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_map );
        mMapView = ( MapView ) findViewById( R.id.mapview );
        assert mMapView != null;
        mMapView.setTileSource( TileSourceFactory.MAPQUESTAERIAL_US );

        addMyLocationNewOverlay( );
        addScaleBarOverlay( );
        mMapView.setMultiTouchControls( true );
        mMapView.getController( ).setZoom( 10 );

        Marker _marker = new Marker( mMapView );
        _marker.setPosition( new GeoPoint( 43.2075682, 86.916293 ) );

        Marker _marker1 = new Marker( mMapView );
        _marker1.setPosition( new GeoPoint( 43.2075474, 86.9169691 ) );
        mMapView.getOverlays( ).add( _marker );
        mMapView.getOverlays( ).add( _marker1 );
        mMapView.getController( ).setCenter( new GeoPoint( 53.8075682, 86.416293 ) );
    }

    @Override
    protected void onResume( )
    {
        super.onResume( );
        mMyLocationNewOverlay.enableMyLocation( );
        Handler _handler = new Handler( );
        _handler.postDelayed( new Runnable( )
        {
            @Override
            public void run( )
            {
                finish( );
            }
        }, 2000 );
    }

    protected void addScaleBarOverlay( )
    {
        if ( mScaleBarOverlay == null )
        {
            mScaleBarOverlay = new ScaleBarOverlay( mMapView );
            mScaleBarOverlay.setAlignBottom( true );
            mScaleBarOverlay.setMaxLength( 1 );
            mScaleBarOverlay.setScaleBarOffset( 20, 20 );
        }
        mMapView.getOverlays( ).add( mScaleBarOverlay );
    }

    protected void addMyLocationNewOverlay( )
    {
        if ( mMyLocationNewOverlay == null )
        {
            mMyLocationNewOverlay =
                    new MyLocationNewOverlay( new GpsMyLocationProvider( getApplicationContext( ) ),
                                              mMapView );
            mMyLocationNewOverlay.setEnabled( true );
            mMyLocationNewOverlay.setDrawAccuracyEnabled( true );
            Bitmap personIcon =
                    BitmapFactory.decodeResource( getResources( ), R.drawable.person );
            mMyLocationNewOverlay.setPersonHotspot( personIcon.getWidth( ) / 2,
                                                    personIcon.getHeight( ) / 2 );
            mMyLocationNewOverlay.setPersonIcon( personIcon );
        }
        mMapView.getOverlays( ).add( mMyLocationNewOverlay );
    }

    @Override
    protected void onPause( )
    {
        super.onPause( );
        mMyLocationNewOverlay.disableMyLocation( );
    }

    @Override
    protected void onDestroy( )
    {
        super.onDestroy( );
        mMyLocationNewOverlay.onDetach( mMapView );
    }
}
