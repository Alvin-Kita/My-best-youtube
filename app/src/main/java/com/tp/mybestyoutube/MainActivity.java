package com.tp.mybestyoutube;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tp.mybestyoutube.database.entity.YoutubeVideo;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    /**
     * Méthode qui permet de revenir à l'activité précédente
     * @param item MenuItem
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Méthode qui initialise l'activité
     * @param savedInstanceState Bundle
     */
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

        // Ajout de padding pour les bords de l'écran
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation de première vidéo
        YoutubeVideo youtubeVideoTest = new YoutubeVideo();
        youtubeVideoTest.titre = "Titre de test";
        youtubeVideoTest.description = "Description de test";
        youtubeVideoTest.url = "https://www.youtube.com/watch?v=jNQXAC9IVRw";
        youtubeVideoTest.categorie = "Test";

        // Bouton de redirection vers la page d'ajout de vidéo
        findViewById(R.id.home_button_add).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddYouTubeActivity.class);
            startActivity(intent);
        });

    }

}