package vn.edu.poly.tailaph26495_appnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import vn.edu.poly.tailaph26495_appnote.FRAGMENT.AddNoteFragment;
import vn.edu.poly.tailaph26495_appnote.FRAGMENT.HomeFragment;
import vn.edu.poly.tailaph26495_appnote.FRAGMENT.ListNoteFragment;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView ic_search ;

    TextView tv_title;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_nav);
        toolbar =findViewById(R.id.toolbar_activity);
        ic_search = findViewById(R.id.ic_search);
        tv_title = findViewById(R.id.toolbar_title);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                        tv_title.setText("Home");
                        toolbar.setVisibility(View.VISIBLE);
                        break;
                    case R.id.frag_add:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddNoteFragment()).commit();
                        tv_title.setText("My Note");
                        toolbar.setVisibility(View.VISIBLE);
                        break;
                    case R.id.frag_list:
                        toolbar.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListNoteFragment()).commit();
                        break;
                }

                return true;
            }
        });


    }
}