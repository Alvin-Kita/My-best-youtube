package com.tp.mybestyoutube.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tp.mybestyoutube.database.dao.YoutubeVideoDao;
import com.tp.mybestyoutube.database.entity.YoutubeVideo;

/**
 * classe permettant de créer la base de données et d’accéder au DAO
 * afin de stocker les informations d'une vidéo Youtube.
 */
@Database(entities = {YoutubeVideo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "My_Best_Youtube";

    public static AppDatabase getDb(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

    public abstract YoutubeVideoDao youtubeVideoDao();
}
