package com.tp.mybestyoutube;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tp.mybestyoutube.database.AppDatabase;
import com.tp.mybestyoutube.database.entity.YoutubeVideo;

/**
 * Activité qui permet d'ajouter une vidéo YouTube
 */
public class AddYouTubeActivity extends BaseActivity {

    /**
     * Méthode qui initialise l'activité
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_youtube);
        setupToolbar();

        // Ajout de padding pour les bords de l'écran
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_youtube), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ajout du Spinner pour les catégories
        Spinner spinner = findViewById(R.id.add_yt_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.add_yt_categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Bouton annuler qui ramène sur la page d'accueil
        findViewById(R.id.add_yt_btn_cancel).setOnClickListener(v -> finish());

        // Bouton ajouter qui ajoute la vidéo à la base de données
        findViewById(R.id.add_yt_btn_add).setOnClickListener(v -> {
            EditText titleEditText = findViewById(R.id.add_yt_title);
            EditText descriptionEditText = findViewById(R.id.add_yt_description);
            EditText linkEditText = findViewById(R.id.add_yt_link);

            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String link = linkEditText.getText().toString();
            String category = spinner.getSelectedItem().toString();

            // Vérification des champs
            if (title.isEmpty() || description.isEmpty() || link.isEmpty()) {
                if (title.isEmpty()) {
                    titleEditText.setError(getString(R.string.add_yt_error_EditText));
                }
                if (description.isEmpty()) {
                    descriptionEditText.setError(getString(R.string.add_yt_error_EditText));
                }
                if (link.isEmpty()) {
                    linkEditText.setError(getString(R.string.add_yt_error_EditText));
                }
                Toast.makeText(this, getString(R.string.add_yt_error_toast), Toast.LENGTH_SHORT).show();
            } else {
                // Création de l'objet YoutubeVideo et ajout à la base de données
                YoutubeVideo youtubeVideo = new YoutubeVideo(title, description, link, category, 0);

                AppDatabase db = AppDatabase.getDb(this);
                db.youtubeVideoDao().addVideo(youtubeVideo);
                Toast.makeText(this, getString(R.string.add_yt_succes_toast), Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

}