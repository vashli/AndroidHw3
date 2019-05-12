package com.vashli.fileexplorerapp.Directory;

import android.os.Environment;
import com.vashli.fileexplorerapp.Model.FileModel;
import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private String root;
    private String currDir;
    private MainContract.View view;
    private boolean isList;
    private boolean isSelectMode;
    private List<FileModel> selectedFiles;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.isList = true;
        this.isSelectMode = false;
        this.root = Environment.getExternalStorageDirectory().toString();
        this.selectedFiles = new ArrayList<>();
        this.view.showDeleteIcon(isSelectMode);
    }

    @Override
    public void start() {
        loadDir(null);
    }



    @Override
    public void loadDir(String path) {
        if (path == null){
            path = root;
        }
        currDir = path;
        FileModel dir = new FileModel(path);
        ArrayList<FileModel> fileModels = dir.getFiles();
        view.showData(fileModels);
        view.showPath(currDir);
    }

    private String getPrevDir(String dirPath){
        if (dirPath.equals(root)) return root;
        while (dirPath.charAt(dirPath.length() - 1) == '/'){
            dirPath = dirPath.substring(0, dirPath.length() - 1);
        }
        int index = dirPath.lastIndexOf('/');
        if(index >= 0 && index < dirPath.length()){
            dirPath = dirPath.substring(0, index);
        }
        return dirPath;
    }

    @Override
    public void menuIconClicked() {
        isList = !isList;
        view.setLayout(isList);
    }

    @Override
    public void deleteIconClicked() {
        setSelectMode(false);
        for (FileModel file:
             selectedFiles) {
            file.deleteFile();
        }
        selectedFiles.clear();
        loadDir(currDir);
    }


    @Override
    public boolean fileClicked(FileModel file) {
        if(isSelectMode) {
            return clickedInSelectMode(file);
        }else {
            switch (file.getType()) {
                case FileModel.DIR:
                    String path = file.getPath();
                    loadDir(path);
                    break;
                case FileModel.FILE_TXT:
                    view.openTextFile(file.getPath());
                    break;
                case FileModel.FILE_PDF:
                    view.openPdfFile(file.getPath());
                    break;
            }
            return false;
        }
    }

    @Override
    public boolean fileLongClicked(FileModel file) {
        if (!isSelectMode){
            setSelectMode(true);
        }
        return clickedInSelectMode(file);
    }

    private boolean clickedInSelectMode(FileModel file){
        if (selectedFiles.contains(file)) {
            selectedFiles.remove(file);
            if (selectedFiles.size() == 0) {
                setSelectMode(false);
            }
            return false;
        }else {
            selectedFiles.add(file);
            return true;
        }
    }

    @Override
    public boolean backPressed() {
        if (currDir.equals(root)){
            return false;
        }
        String prevPath = getPrevDir(currDir);
        if (prevPath.equals(currDir)) return false;
        loadDir(prevPath);
        return true;
    }

    private void setSelectMode(boolean selectMode){
        this.isSelectMode = selectMode;
        view.showDeleteIcon(selectMode);
    }





}
