package com.vashli.fileexplorerapp.TextFile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.vashli.fileexplorerapp.R;

public class TextActivity extends AppCompatActivity implements TextContract.View{
    private ImageView saveIcon;
    private EditText editText;
    private TextContract.Presenter presenter;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        editText = this.findViewById(R.id.activity_text_edit_text);
        addSaveIcon();
        Intent intent = getIntent();
        path = intent.getStringExtra("path");

        this.presenter = new TextPresenter(this, path);
        this.presenter.openTextFile(path);
    }

    private void addSaveIcon() {
        saveIcon = this.findViewById(R.id.activity_text_icon_save);
        saveIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String editedText = String.valueOf(editText.getText());
                presenter.saveIconClicked(editedText);
            }
        });
    }

    @Override
    public void loadText(String text) {
        this.editText.setText(text);
    }
}
