package com.zakariadouih.portfolioapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RemarquesAdapter extends RecyclerView.Adapter<RemarquesAdapter.RemarquesHolder> {

    Context context;
    public RemarquesAdapter(Context context, String[] s1){
        this.context=context;
    }
    @NonNull
    @Override
    public RemarquesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RemarquesHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RemarquesHolder extends RecyclerView.ViewHolder{
        public RemarquesHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
