package com.tp.mybestyoutube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.mybestyoutube.database.AppDatabase;
import com.tp.mybestyoutube.database.entity.YoutubeVideo;

import java.util.List;

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

        // Initialisation de la base de données
        AppDatabase db = AppDatabase.getDb(this);

        // Initialisation de la liste des vidéos
        RecyclerView videosRecyclerView = findViewById(R.id.homeRecyclerView);
        videosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<YoutubeVideo> videos = db.youtubeVideoDao().getAll();

        // Ajout de la vidéo de test si la base de données est vide
        if (videos.isEmpty()) {
            // TODO: Passer les paramètres de la vidéo dans string.xml
            YoutubeVideo videoTest = new YoutubeVideo("Première vidéo", "Première vidéos d'une longue série", "jNQXAC9IVRw", "Autre", 0);
            db.youtubeVideoDao().addVideo(videoTest);
            videos = db.youtubeVideoDao().getAll();
        }

        VideoAdapter adapter = new VideoAdapter(videos);
        videosRecyclerView.setAdapter(adapter);

        // Bouton de redirection vers la page d'ajout de vidéo
        findViewById(R.id.home_button_add).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddYouTubeActivity.class);
            startActivity(intent);
        });

        // Spinner de catégories
        Spinner spinner = findViewById(R.id.home_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.home_categories,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                filterVideos(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ne fais rien, est appelé quand rien est selectionné
                // Il y'a peut être mieux à faire mais je manque de temps
            }
        });
    }

    /**
     * Méthode qui filtre les vidéos en fonction de la catégorie sélectionnée
     *
     * @param category Catégorie sélectionnée
     */
    private void filterVideos(String category) {
        AppDatabase db = AppDatabase.getDb(this);
        List<YoutubeVideo> filteredVideos;

        if (category.equals("Tous")) {
            filteredVideos = db.youtubeVideoDao().getAll();
        } else if (category.equals("Favoris")) {
            filteredVideos = db.youtubeVideoDao().getFavoris();
        } else {
            filteredVideos = db.youtubeVideoDao().getByCategorie(category);
        }

        VideoAdapter adapter = new VideoAdapter(filteredVideos);
        RecyclerView videosRecyclerView = findViewById(R.id.homeRecyclerView);
        videosRecyclerView.setAdapter(adapter);
    }

    /**
     * Méthode qui met à jour la liste des vidéos quand on revient sur la page d'accueil pour appliquer les modifications faites sur les autres pages
     */
    @Override
    protected void onResume() {
        super.onResume();
        AppDatabase db = AppDatabase.getDb(this);
        List<YoutubeVideo> videos = db.youtubeVideoDao().getAll();
        VideoAdapter adapter = new VideoAdapter(videos);
        RecyclerView videosRecyclerView = findViewById(R.id.homeRecyclerView);
        videosRecyclerView.setAdapter(adapter);
    }
}