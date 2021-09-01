package com.adgvit.papervit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class Exam_2 extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private PDFView pdfView;
    private String copy;
    private Button downloadPdf;
    private boolean paperDownloaded;
    private int READ_STORAGE = 100;
    private int WRITE_STORAGE = 101;
    private String filename;
    private String subTitle;
    private ImageView downloadIcon;
    private String fileUrl;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_exam_2);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.backgroundLight));

        Toolbar toolbar = findViewById(R.id.toolbarPaper);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView subShort, subName, subName2, subCode, subYear, subSlot;

        final Intent intent = getIntent();
        String subjectName = intent.getStringExtra("subName");
        String subjectShort = intent.getStringExtra("subShort");
        String subjectCode = intent.getStringExtra("subCode");
        String subjectYear = intent.getStringExtra("subYear");
        String subjectSlot = intent.getStringExtra("subSlot");
        String paperId = intent.getStringExtra("paperId");
        filename = intent.getStringExtra("fileName");
        fileUrl = intent.getStringExtra("fileUrl");
        //Log.i("fileUrl",fileUrl);
        subShort = findViewById(R.id.subjectShortTextView2);
        //subName = findViewById(R.id.subjectNameTextView3);
        //subName2 = findViewById(R.id.subjectNameTextView4);
        //subCode = findViewById(R.id.subjectCodeTextView2);
        subYear = findViewById(R.id.courseYearTextView);
        subSlot = findViewById(R.id.courseSlotTextView);
        downloadPdf = findViewById(R.id.downloadPdfButton);
        pdfView = findViewById(R.id.pdfViewer2);
        downloadIcon = findViewById(R.id.downloadIcon);

        subShort.setText(subjectShort);
        //subName.setText(subjectName);
        //subName2.setText(subjectName);
        //subCode.setText(subjectCode);
        subYear.setText(subjectYear);
        subSlot.setText("Slot: " + subjectSlot);

        //filename = subjectCode + "_" + subjectYear + "_" + subjectSlot + "_" + Exam.examType + ".pdf";

        subTitle = filename.replace("/","_");
        final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + subTitle);

        if (file.isFile()) {

            pdfView.fromFile(file).load();
            downloadPdf.setText(getString(R.string.view));
            paperDownloaded = true;
            progressDialog = ProgressDialog.show(Exam_2.this, "Loading Paper", "Please wait...", false, false);
            new RetrievePDFfromUrl().execute(fileUrl);

        } else {
            Log.i("INFO", "file not found");
            paperDownloaded = false;
        }

        if (!paperDownloaded) {
            progressDialog = ProgressDialog.show(Exam_2.this, "Loading Paper", "Please wait...", false, false);
            new RetrievePDFfromUrl().execute(fileUrl);
        }

        downloadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if ((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE);
                } else if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE);
                } else {

                    if (paperDownloaded) {
                        Intent intent = new Intent(Exam_2.this, FullView.class);
                        intent.putExtra("filename", subTitle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        downloadPdf.setText(getString(R.string.download));
                        downloadPdf.setEnabled(false);
                        downloadPdf.setAlpha(0.7f);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                savePdf(fileUrl,filename);
                            }
                        }, 1000);
                    }

                }
            }
        });

        downloadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paperDownloaded) {

//                    startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
                    Intent intent1 = new Intent(Intent.ACTION_SEND);
                    intent1.setType(URLConnection.guessContentTypeFromName(subTitle));
                    intent1.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + subTitle));
                    startActivity(Intent.createChooser(intent1, "Share File"));

                } else {
                    Toast toast = Toast.makeText(Exam_2.this, "Download the paper to share", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }

    class RetrievePDFfromUrl extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    return inputStream;
                }
                else {
                    return null;
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            if(inputStream==null){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Dialog dialog = new Dialog(Exam_2.this);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.alert);
                        dialog.show();

                        Button button = dialog.findViewById(R.id.home);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                progressDialog.dismiss();
                                startActivity(new Intent(Exam_2.this,MainActivity.class));
                            }
                        });

                    }
                }, 3000);

            }
            else {
                pdfView.fromStream(inputStream).load();
                progressDialog.dismiss();

            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == READ_STORAGE) {

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE);
            } else {
                if (paperDownloaded) {
                    Intent intent = new Intent(Exam_2.this, FullView.class);
                    intent.putExtra("filename",subTitle );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                } else {
                    downloadPdf.setText(getString(R.string.download));
                    downloadPdf.setEnabled(false);
                    downloadPdf.setAlpha(0.7f);
                    savePdf(fileUrl, filename);
                }

            }
        } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == WRITE_STORAGE) {

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, WRITE_STORAGE);
            } else {
                if (paperDownloaded) {
                    Intent intent = new Intent(Exam_2.this, FullView.class);
                    intent.putExtra("filename", subTitle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                } else {
                    downloadPdf.setText(getString(R.string.download));
                    downloadPdf.setEnabled(false);
                    downloadPdf.setAlpha(0.7f);
                    savePdf(fileUrl, filename);
                }

            }
        }
    }
    private void savePdf(String url,String title){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(title);
        String subTitle = filename.replace("/","_");
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,subTitle);
        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        request.setMimeType("application/pdf");
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        manager.enqueue(request);
        paperDownloaded=true;
        //Toast.makeText(this, "Downloaded"+"/"+filename, Toast.LENGTH_SHORT).show();
        downloadPdf.setText("View PDF");
        downloadPdf.setAlpha(1f);
        downloadPdf.setEnabled(true);
        paperDownloaded = true;
        Toast.makeText(this, "Paper saved at Downloads/" + subTitle, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RecyclerViewAdapter2.showShimmer = false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}