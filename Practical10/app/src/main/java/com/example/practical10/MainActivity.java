package com.example.practical10;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button animateButton = findViewById(R.id.animateButton);

        // Load the animation from XML
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Set an onClick listener to start the animation when the button is clicked
        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButton.startAnimation(fadeAnimation);
            }
        });
    }
}
