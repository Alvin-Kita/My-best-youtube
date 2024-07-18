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
    public String titre;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "categorie")
    public String categorie;

    @ColumnInfo(name = "favori")
    public int favori;


}
