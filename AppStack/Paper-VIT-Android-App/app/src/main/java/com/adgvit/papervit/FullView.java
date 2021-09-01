package com.adgvit.papervit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.WindowManager;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class FullView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_full_view);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        PDFView pdfView = findViewById(R.id.pdfViewerFull);

        Intent intent = getIntent();
        String filename = intent.getStringExtra("filename");

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + filename);
        pdfView.fromFile(file).load();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
