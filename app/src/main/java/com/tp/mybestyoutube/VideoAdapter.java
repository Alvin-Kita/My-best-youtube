package com.tp.mybestyoutube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.mybestyoutube.database.entity.YoutubeVideo;

import java.util.List;

/**
 * Adapter pour afficher la liste des vidéos
 */
class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<YoutubeVideo> videoList;

    /**
     * Constructeur de l'adapter
     *
     * @param videos Liste des vidéos
     */
    public VideoAdapter(List<YoutubeVideo> videos) {
        this.videoList = videos;
    }


    /**
     * Méthode qui crée un ViewHolder
     *
     * @param parent   ViewGroup
     * @param viewType int
     * @return VideoViewHolder
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    /**
     * Méthode qui lie les données à un ViewHolder
     *
     * @param holder   VideoViewHolder
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        YoutubeVideo video = videoList.get(position);
        holder.title.setText(video.title);
        holder.description.setText(video.description);
    }

    /**
     * Méthode qui retourne le nombre d'éléments dans la liste
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return videoList.size();
    }

    /**
     * ViewHolder pour les vidéos
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        /**
         * Constructeur du ViewHolder
         *
         * @param itemView View
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.video_item_title);
            description = itemView.findViewById(R.id.video_item_description);
        }
    }
}
