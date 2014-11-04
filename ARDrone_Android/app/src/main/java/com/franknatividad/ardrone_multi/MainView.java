package com.franknatividad.ardrone_multi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.*;


public class MainView extends Activity {
    private final ArdroneAPI drone1 = new ArdroneAPI("192.168.43.3");
    private final ArdroneAPI drone2 = new ArdroneAPI("192.168.43.4");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        final Button bnStart = (Button) findViewById(R.id.bnStart);
        bnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        drone1.connect();
                        drone2.connect();
                    }
                }).start();

            }
        });

        final Button bnLand = (Button) findViewById(R.id.bnLand);
        bnLand.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        drone1.close();
                        drone2.close();
                    }
                }).start();
            }
        });

        final Button bnLeft = (Button) findViewById(R.id.bnLeft);
        bnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        drone1.tleft();
                        drone2.tleft();
                    }
                }).start();
            }
        });

        final Button bnRight = (Button) findViewById(R.id.bnRight);
        bnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        drone1.tright();
                        drone2.tright();
                    }
                }).start();
            }
        });

        final Button bnFront = (Button) findViewById(R.id.bnFront);
        bnFront.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        drone1.front();
                        drone2.front();
                    }
                }).start();
            }
        });

        final Button bnBack = (Button) findViewById(R.id.bnBack);
        bnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        drone1.back();
                        drone2.back();
                    }
                }).start();
            }
        });

        final Button bnUp = (Button) findViewById(R.id.bnUp);
        bnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        drone1.up();
                        drone2.up();
                    }
                }).start();
            }
        });

        final Button bnDown = (Button) findViewById(R.id.bnDown);
        bnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        drone1.down();
                        drone2.down();
                    }
                }).start();
            }
        });


    }



}
