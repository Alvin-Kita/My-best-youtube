package com.tp.mybestyoutube;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class YoutubePlayerActivity extends BaseActivity {

    /**
     * Méthode qui initialise la page de lecture d'une vidéo YouTube
     * (J'utilise un iframe et donc javascript pour afficher la vidéo, je ne veux pas pas payer pour l'API
     * de YouTube, et souhaite avoir un affichage directement dans l'application.
     * En contrepartie la sécurité sur cette page doit être renforcée à terme pour éviter les attaques XSS (cross-site scripting))
     *
     * @param savedInstanceState Bundle
     */
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_youtube_player);
        setupToolbar();

        WebView webView = findViewById(R.id.webview_youtube_player);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Activation de JavaScript avec les risques que cela comporte comme présenté dans la JavaDoc

        // Autoriser le contenu mixte (HTTP et HTTPS)
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.getSettings().setUserAgentString("Mozilla/5.0 Google");

        // Récupération du lien de la vidéo
        String videoLink = getIntent().getStringExtra("link");
        String frameVideo = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoLink + "\" frameborder=\"0\" allowfullscreen></iframe>";

        webView.loadData(frameVideo, "text/html", "utf-8");

        // Ajout de padding pour les bords de l'écran
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}