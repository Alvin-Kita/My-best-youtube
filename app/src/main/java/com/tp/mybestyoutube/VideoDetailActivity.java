package com.tp.mybestyoutube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoDetailActivity extends BaseActivity {

    /**
     * Méthode qui initialise la page de détail d"une vidéo
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_detail);
        setupToolbar();

        // Récupération des informations de la vidéo
        long id = getIntent().getLongExtra("id", -1);
        String title = getIntent().getStringExtra("title");
        String formattedTitle = id + ". " + title;
        String description = getIntent().getStringExtra("description");
        String formattedDescription = getString(R.string.video_detail_formatted_description, description);
        String link = getIntent().getStringExtra("link");
        String formattedLink = getString(R.string.video_detail_formatted_link, link);
        String category = getIntent().getStringExtra("category");
        String formattedCategory = getString(R.string.video_detail_formatted_category, category);
        int favorite = getIntent().getIntExtra("favori", 0);


        // Affichage des informations de la vidéo
        TextView titleTextView = findViewById(R.id.video_detail_title);
        TextView descriptionTextView = findViewById(R.id.video_detail_description);
        TextView linkTextView = findViewById(R.id.video_detail_link);
        TextView categoryTextView = findViewById(R.id.video_detail_category);
        TextView favoriteTextView = findViewById(R.id.video_detail_favorite);

        titleTextView.setText(formattedTitle);
        descriptionTextView.setText(formattedDescription);
        linkTextView.setText(formattedLink);
        categoryTextView.setText(formattedCategory);

        if (favorite == 1) {
            favoriteTextView.setText(R.string.video_detail_favorite_true);
        } else {
            favoriteTextView.setText(R.string.video_detail_favorite_false);
        }

        // Bouton Voici la vidéo
        findViewById(R.id.video_detail_button_see).setOnClickListener(v -> {
            Intent intent = new Intent(this, YoutubePlayerActivity.class);
            intent.putExtra("link", link);
            startActivity(intent);
        });

        // Bouton de redirection vers la page de modification de la vidéo
        findViewById(R.id.video_detail_button_edit).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddYouTubeActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            intent.putExtra("description", description);
            intent.putExtra("link", link);
            intent.putExtra("category", category);
            intent.putExtra("favori", favorite);
            intent.putExtra("isEditing", true); // Indique que a AddYoutubeActivity que l'on est en mode édition
            startActivity(intent);
        });

        // Ajout de padding pour les bords de l'écran
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_video_detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}