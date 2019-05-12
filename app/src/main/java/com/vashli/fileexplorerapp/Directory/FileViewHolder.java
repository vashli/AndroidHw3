package com.vashli.fileexplorerapp.Directory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vashli.fileexplorerapp.Model.FileModel;

public abstract class FileViewHolder extends RecyclerView.ViewHolder {
    public FileViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract void setData(FileModel fileModel);
    abstract void setSelectMode(boolean selectMode);
}
