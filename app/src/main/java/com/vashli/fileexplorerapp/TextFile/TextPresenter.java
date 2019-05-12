package com.vashli.fileexplorerapp.TextFile;
import com.vashli.fileexplorerapp.Model.FileModel;


public class TextPresenter implements TextContract.Presenter {

    private TextContract.View view;
    private String path;
    private FileModel file;

    public TextPresenter(TextContract.View view, String path) {
        this.view = view;
        this.path = path;
        this.file = new FileModel(path);
    }

    @Override
    public void openTextFile(String path) {
        String txt = file.readText();
        view.loadText(txt);
    }

    @Override
    public void saveIconClicked(String editedText) {
        file.writeText(editedText);
    }



}
