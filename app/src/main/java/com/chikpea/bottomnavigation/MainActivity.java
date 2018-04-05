package com.chikpea.bottomnavigation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    private boolean loadFragment(Fragment fragment)
    {
        if(fragment!=null)
        {


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit();
                    return true;


        }
        return false;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        switch ((item.getItemId()))
        {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_contacts:
                fragment = new ContactFragment();
              //  Toast.makeText("Hi this is Contact fragment",)
                break;
        }
        return loadFragment(fragment);

        }
}
