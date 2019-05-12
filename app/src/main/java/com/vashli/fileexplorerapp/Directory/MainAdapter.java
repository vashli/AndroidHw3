package com.vashli.fileexplorerapp.Directory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vashli.fileexplorerapp.Model.FileModel;
import com.vashli.fileexplorerapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends  RecyclerView.Adapter<FileViewHolder> {
    private List<FileModel> fileModels = new ArrayList<>();
    private boolean isList = true;
    private ItemClickListener onItemClickListener;

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (isList){
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_list, viewGroup, false);
            return new ListViewHolder(v);
        }else{
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_grid, viewGroup, false);
            return new GridViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final FileViewHolder viewHolder, final int i) {
        viewHolder.setData(fileModels.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isSelected = onItemClickListener.onItemClick(v, fileModels.get(i));
                viewHolder.setSelectMode(isSelected);
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean isSelected = onItemClickListener.onItemLongClick(v, fileModels.get(i));
                viewHolder.setSelectMode(isSelected);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return fileModels.size();
    }

    public void setFileData(List<FileModel> fileModels){
        this.fileModels.clear();
        this.fileModels.addAll(fileModels);
        notifyDataSetChanged();
    }

    public void setMode(boolean isList){
        this.isList = isList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.onItemClickListener = listener;
    }


}
