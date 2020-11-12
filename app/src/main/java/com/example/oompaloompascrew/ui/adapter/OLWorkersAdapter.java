package com.example.oompaloompascrew.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oompaloompascrew.R;
import com.example.oompaloompascrew.dto.OLWorkerDTO;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class OLWorkersAdapter extends RecyclerView.Adapter<OLWorkersAdapter.WorkersViewHolder> {

    private final ArrayList<OLWorkerDTO> olWorkersList;
    private OnItemClickedCallback onItemClickedCallback;

    /**
     * Beers viewholder, view to show item in recyclerview
     */
    static class WorkersViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvProfession;
        SimpleDraweeView imageView;

        WorkersViewHolder(final View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvProfession = view.findViewById(R.id.tvProfession);
            imageView = view.findViewById(R.id.imageView);
        }
    }

    public interface OnItemClickedCallback {
        void onItemClicked(int olworkerId);
    }

    /**
     * Constructor for workers adapter
     *
     * @param olWorkers the list of workers to display
     */
    public OLWorkersAdapter(final ArrayList<OLWorkerDTO> olWorkers) {
        this.olWorkersList = olWorkers;
    }

    @NonNull
    @Override
    public WorkersViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ol_worker_item, parent, false);
        return new WorkersViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WorkersViewHolder holder, int position) {
        holder.tvName.setText(getOLWorkerFullname(position));
        holder.imageView.setImageURI(olWorkersList.get(position).getImage());
        holder.tvProfession.setText(olWorkersList.get(position).getProfession());
        holder.itemView.setOnClickListener(__ -> onItemClickedCallback.onItemClicked(olWorkersList.get(position).getId()));
    }

    private String getOLWorkerFullname(int position) {
        return olWorkersList.get(position).getFirstName() + " "
                + olWorkersList.get(position).getLastName();
    }

    @Override
    public int getItemCount() {
        return olWorkersList.size();
    }

    public void setOnItemClickedCallback(OnItemClickedCallback onItemClickedCallback) {
        this.onItemClickedCallback = onItemClickedCallback;
    }
}
