package com.vashli.fileexplorerapp.Directory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vashli.fileexplorerapp.Model.FileModel;
import com.vashli.fileexplorerapp.PDFFile.PdfActivity;
import com.vashli.fileexplorerapp.R;
import com.vashli.fileexplorerapp.TextFile.TextActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private final int REQUEST_EXTERNAL_STORAGE = 123456;
    private MainContract.Presenter presenter;
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private ImageView menuIcon;
    private TextView pathView;
    private ImageView deleteIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addMenuIcon();
        addDeleteIcon();
        recyclerView = this.findViewById(R.id.activity_main_recycler_view);
        pathView = this.findViewById(R.id.activity_main_path);
        presenter = new MainPresenter(this);

        addAdapter();
        presenter.start();
        askStoragePermissionAndLoadData();
    }

    private void addAdapter() {
        adapter = new MainAdapter();
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public boolean onItemClick(View view, FileModel fileModel) {
                return presenter.fileClicked(fileModel);
            }

            @Override
            public boolean onItemLongClick(View view, FileModel fileModel) {
                return presenter.fileLongClicked(fileModel);
            }
        });
        setLayout(true);
    }

    private void addMenuIcon() {
        menuIcon = this.findViewById(R.id.activity_main_icon_menu);
        menuIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               presenter.menuIconClicked();
            }
        });
    }

    private void addDeleteIcon() {
        deleteIcon = this.findViewById(R.id.activity_main_icon_delete);
        deleteIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                presenter.deleteIconClicked();
            }
        });
    }

    private void askStoragePermissionAndLoadData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int selfPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (selfPermission != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
            } else {
                presenter.loadDir(null);
            }
        }else{
            Log.d("mari"," Build.VERSION.SDK_INT < Build.VERSION_CODES.M");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_EXTERNAL_STORAGE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                presenter.loadDir(null);
            }
        }
    }

    @Override
    public void showData(List<FileModel> files) {
        adapter.setFileData(files);
    }

    @Override
    public void setLayout(boolean isList){
        if(isList){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        adapter.setMode(isList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showPath(String path) {
        pathView.setText(path);
    }

    @Override
    public void showDeleteIcon(boolean showIcon) {
        if(showIcon){
            deleteIcon.setVisibility(View.VISIBLE);
        }else {
            deleteIcon.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (!presenter.backPressed()){
            super.onBackPressed();
        }
    }

    @Override
    public void openTextFile(String path){
        Intent myIntent = new Intent(this, TextActivity.class);
        myIntent.putExtra("path", path);
        this.startActivity(myIntent);
    }

    @Override
    public void openPdfFile(String path) {
        Intent myIntent = new Intent(this, PdfActivity.class);
        myIntent.putExtra("path", path);
        this.startActivity(myIntent);
    }
}