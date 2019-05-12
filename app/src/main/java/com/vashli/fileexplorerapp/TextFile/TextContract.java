package com.vashli.fileexplorerapp.TextFile;

public class TextContract {

    interface View {
        void loadText(String text);

    }

    interface Presenter {
        void openTextFile(String path);

        void saveIconClicked(String editedText);
    }

}
