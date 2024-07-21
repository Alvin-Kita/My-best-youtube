package com.tp.mybestyoutube.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tp.mybestyoutube.database.entity.YoutubeVideo;

import java.util.List;

/**
 * DAO pour ajouter, récupérer une vidéo ou une liste de vidéo,
 * mettre à jour une vidéo et supprimer une vidéo
 */
@Dao
public interface YoutubeVideoDao {

    // récupérer toutes les vidéos
    @Query("SELECT * FROM YoutubeVideo")
    List<YoutubeVideo> getAll();

    // Ajouter une vidéo
    @Insert
    void addVideo(YoutubeVideo video);

    //--- Action sur une vidéo en fonction de l'id ---//

    // Récupérer une vidéo en fonction de son id
    @Query("SELECT * FROM YoutubeVideo WHERE id IN (:id)")
    YoutubeVideo getById(long id);

    // Supprimer une vidéo en fonction de son id
    @Query("DELETE FROM YoutubeVideo WHERE id = :id")
    void deleteById(long id);


    //--- Action sur une vidéo en fonction du titre ---//

    // Récupérer une vidéo en fonction de son titre
    @Query("SELECT * FROM YoutubeVideo WHERE titre IN (:titre)")
    YoutubeVideo getByTitre(String titre);

    // Mettre à jour le titre d'une vidéo
    @Query("UPDATE YoutubeVideo SET titre = :titre WHERE id = :id")
    void updateTitre(long id, String titre);

    // Supprimer une vidéo en fonction de son titre
    @Query("DELETE FROM YoutubeVideo WHERE titre = :titre")
    void deleteByTitre(String titre);

    //--- Action sur une vidéo en fonction de l'url ---//

    // Récupérer une vidéo en fonction de son url
    @Query("SELECT * FROM YoutubeVideo WHERE url IN (:url)")
    YoutubeVideo getByUrl(String url);

    // Mettre à jour l'url d'une vidéo
    @Query("UPDATE YoutubeVideo SET url = :url WHERE id = :id")
    void updateUrl(long id, String url);

    // Supprimer une vidéo en fonction de son url
    @Query("DELETE FROM YoutubeVideo WHERE url = :url")
    void deleteByUrl(String url);


    //--- Action sur une vidéo en fonction de la catégorie ---//

    // Récupérer une liste de vidéo en fonction de leur catégorie
    @Query("SELECT * FROM YoutubeVideo WHERE categorie IN (:categorie)")
    List<YoutubeVideo> getByCategorie(String categorie);

    // Ajouter une video à une catégorie
    @Query("UPDATE YoutubeVideo SET categorie = :categorie WHERE id = :id")
    void addCategorie(long id, String categorie);

    // Supprimer une vidéo d'une catégorie
    @Query("UPDATE YoutubeVideo SET categorie = '' WHERE id = :id")
    void deleteCategorie(long id);


    //--- Action sur une vidéo en fonction de son statut favori ---//

    // Récupérer la liste de vidéo en favori
    @Query("SELECT * FROM YoutubeVideo WHERE favori IN (1)")
    List<YoutubeVideo> getFavoris();

    // Ajouter une vidéo en favori
    @Query("UPDATE YoutubeVideo SET favori = 1 WHERE id = :id")
    void addFavori(long id);

    // Supprimer une vidéo des favoris
    @Query("UPDATE YoutubeVideo SET favori = 0 WHERE id = :id")
    void deleteFavori(long id);

}
