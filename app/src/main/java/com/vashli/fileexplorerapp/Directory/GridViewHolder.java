package com.vashli.fileexplorerapp.Directory;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.vashli.fileexplorerapp.Model.FileModel;
import com.vashli.fileexplorerapp.R;

public class GridViewHolder extends FileViewHolder {
    private View view;
    private ImageView icon;
    private TextView fileName;

    public GridViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        initViewComponents();
    }

    private void initViewComponents(){
        icon = view.findViewById(R.id.cell_grid_icon);
        fileName = view.findViewById(R.id.cell_grid_file_name);
    }

    @Override
    public void setData(FileModel fileModel){
        icon.setImageResource(fileModel.getIcon());
        fileName.setText(fileModel.getName());
    }

    @Override
    public void setSelectMode(boolean selectMode) {
        if(selectMode){
            view.setBackgroundResource(R.color.colorBgSelected);
        }else{
            view.setBackgroundResource(R.color.colorBgNotSelected);
        }
    }
}
