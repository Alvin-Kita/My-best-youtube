package com.tp.mybestyoutube;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tp.mybestyoutube.database.entity.YoutubeVideo;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialisation de la toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewCompat.setOnApplyWindowInsetsListener(myToolbar, (v, insets) -> {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            layoutParams.topMargin = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            v.setLayoutParams(layoutParams);
            return insets;
        });

        // Initialisation de première vidéo
        YoutubeVideo youtubeVideoTest = new YoutubeVideo();
        youtubeVideoTest.titre = "Titre de test";
        youtubeVideoTest.description = "Description de test";
        youtubeVideoTest.url = "https://www.youtube.com/watch?v=jNQXAC9IVRw";
        youtubeVideoTest.categorie = "Test";

    }

}