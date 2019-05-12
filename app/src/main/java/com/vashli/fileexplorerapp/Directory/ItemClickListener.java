package com.vashli.fileexplorerapp.Directory;

import android.view.View;

import com.vashli.fileexplorerapp.Model.FileModel;

public interface ItemClickListener {
    //bool isSelected
    boolean onItemClick(View view, FileModel fileModel);
    boolean onItemLongClick(View view, FileModel fileModel);
}
