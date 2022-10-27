package vn.edu.poly.tailaph26495_appnote;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HelloScreenActivity extends AppCompatActivity {


    LinearLayout layout_hello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_screen);

        anhXa();
        animationActivity();

        final  Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }



    public void animationActivity(){
        anhXa();
        layout_hello.setAlpha(0f);
        layout_hello.setTranslationZ(-150);
        layout_hello.animate().alpha(1f).translationZBy(150).setDuration(1200);

    }

    public void anhXa(){
        layout_hello = findViewById(R.id.layout_hello);

    }


}