package com.vashli.fileexplorerapp.PDFFile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;
import com.vashli.fileexplorerapp.R;

import java.io.File;

public class PdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromFile(new File(path)).load();
    }
}
