package com.vashli.fileexplorerapp.Directory;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.vashli.fileexplorerapp.Model.FileModel;
import com.vashli.fileexplorerapp.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListViewHolder extends FileViewHolder {
    private View view;
    private ImageView icon;
    private TextView fileName;
    private TextView size;
    private TextView sizeDim;
    private TextView date;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        initViewComponents();
    }



    private void initViewComponents(){
        icon = view.findViewById(R.id.cell_list_icon);
        fileName = view.findViewById(R.id.cell_list_file_name);
        size = view.findViewById(R.id.cell_list_size);
        sizeDim = view.findViewById(R.id.cell_list_size_dim);
        date = view.findViewById(R.id.cell_list_date);
    }

    @Override
    public void setData(FileModel fileModel){
        icon.setImageResource(fileModel.getIcon());
        fileName.setText(fileModel.getName());
        size.setText(Long.toString(fileModel.getSize()));
        sizeDim.setText(fileModel.getSizeDim());
        date.setText(getDateString(fileModel.getCreateDate()));
    }

    @Override
    public void setSelectMode(boolean selectMode) {
        if(selectMode){
            view.setBackgroundResource(R.color.colorBgSelected);
        }else{
            view.setBackgroundResource(R.color.colorBgNotSelected);
        }
    }

    private String getDateString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date);
    }

}
