package com.tp.mybestyoutube.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * modèle pour stocker les informations d'une vidéo YouTube
 * (les catégories seront stockées sous forme de string) :
 * id (long), titre (String), description (String), url (String), categorie (String), favori (int).
 */
@Entity(tableName = "YoutubeVideo")
public class YoutubeVideo {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "titre")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "url")
    public String link;

    @ColumnInfo(name = "categorie")
    public String category;

    @ColumnInfo(name = "favori")
    public int favori;


    /**
     * Constructeur
     */
    public YoutubeVideo(String title, String description, String link, String category, int favori) {
        id = 0;
        this.title = title;
        this.description = description;
        this.link = link;
        this.category = category;
        this.favori = favori;
    }
}
