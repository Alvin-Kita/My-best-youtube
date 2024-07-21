package com.tp.mybestyoutube;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tp.mybestyoutube.database.AppDatabase;
import com.tp.mybestyoutube.database.entity.YoutubeVideo;

/**
 * Activité principale de l'application (page d'accueil)
 */
public class MainActivity extends BaseActivity {

    /**
     * Méthode qui initialise l'activité
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupToolbar();

        // Ajout de padding pour les bords de l'écran
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation de première vidéo TODO: à supprimer
        YoutubeVideo youtubeVideoTest = new YoutubeVideo("Titre de test", "Description de test", "jNQXAC9IVRw", "Test", 0);
        AppDatabase db = AppDatabase.getDb(this);
        db.youtubeVideoDao().addVideo(youtubeVideoTest);

        // Bouton de redirection vers la page d'ajout de vidéo
        findViewById(R.id.home_button_add).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddYouTubeActivity.class);
            startActivity(intent);
        });

    }

}