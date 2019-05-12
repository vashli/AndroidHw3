package com.vashli.fileexplorerapp.Directory;

import com.vashli.fileexplorerapp.Model.FileModel;

import java.util.List;

public interface MainContract {

    interface View {

        void showData(List<FileModel> list);

        void setLayout(boolean isList);

        void showPath(String path);

        void showDeleteIcon(boolean showIcon);

        void openTextFile(String path);

        void openPdfFile(String path);

    }

    interface Presenter {

        void start();

        void loadDir(String path);

        void menuIconClicked();

        void deleteIconClicked();

        boolean fileClicked(FileModel file);

        boolean fileLongClicked(FileModel file);

        // returns true if changed dir
        boolean backPressed();
    }

}
