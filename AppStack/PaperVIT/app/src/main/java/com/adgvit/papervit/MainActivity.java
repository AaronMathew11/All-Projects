package com.adgvit.papervit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adgvit.papervit.RecyclerViewAdapterPackage.RecyclerAdapterExamMain;

public class MainActivity extends AppCompatActivity {

    private CardView cat1CardView, cat2CardView, fatCardView, uploadButton;
    private ImageView settings;
    private int READ_STORAGE = 100;
    private int WRITE_STORAGE = 101;

    RecyclerView examsRecyclerView;

    String[] examName = {"CAT1","CAT2","FAT"};
    String[] examSubName = {"Continuous Assessment Test 1","Continuous Assessment Test 2","Final Assessment Test"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_main);


        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //int colorCodeDark = Color.parseColor("#F7F7FC");
        //int colorCodeDark = Color.parseColor(getResources().getColor(R.color.colorPrimaryTheme));
        //window.setStatusBarColor(colorCodeDark);
        window.setStatusBarColor(getResources().getColor(R.color.backgroundLight));

        examsRecyclerView = findViewById(R.id.recyclerview_Exams);

        //cat1CardView = findViewById(R.id.cat1CardView);
        //cat2CardView = findViewById(R.id.cat2CardView);
        //fatCardView = findViewById(R.id.fatCardView);
        settings = (ImageView) findViewById(R.id.uploadButton1);
        uploadButton = findViewById(R.id.aboutUs);

        examsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        examsRecyclerView.setAdapter(new RecyclerAdapterExamMain(examName, examSubName));

        if ((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
        {
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},READ_STORAGE);
        }
        else if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
        {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_STORAGE);
        }else {

            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Settings.class);
                    startActivity(intent);
                }
            });

            uploadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,Upload.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

            /*cat1CardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Exam.examType = "CAT 1";
                    Intent intent = new Intent(MainActivity.this,Exam.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });

            cat2CardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Exam.examType = "CAT 2";
                    Intent intent = new Intent(MainActivity.this,Exam.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });

            fatCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Exam.examType = "FAT";
                    Intent intent = new Intent(MainActivity.this,Exam.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });*/
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == READ_STORAGE)
        {

            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_STORAGE);
            }
            else {
                settings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Settings.class);
                        startActivity(intent);
                    }
                });

                uploadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Upload.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                /*cat1CardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Exam.examType = "CAT 1";
                        Intent intent = new Intent(MainActivity.this,Exam.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                });

                cat2CardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Exam.examType = "CAT 2";
                        Intent intent = new Intent(MainActivity.this,Exam.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                });

                fatCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Exam.examType = "FAT";
                        Intent intent = new Intent(MainActivity.this,Exam.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                });*/

            }
        }
        else if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == WRITE_STORAGE)
        {

            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},WRITE_STORAGE);
            }
            else {
                settings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Settings.class);
                        startActivity(intent);
                    }
                });

                uploadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Upload.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                /*cat1CardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Exam.examType = "CAT 1";
                        Intent intent = new Intent(MainActivity.this,Exam.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                });

                cat2CardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Exam.examType = "CAT 2";
                        Intent intent = new Intent(MainActivity.this,Exam.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                });

                fatCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Exam.examType = "FAT";
                        Intent intent = new Intent(MainActivity.this,Exam.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                });*/
            }

            }
        }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //super.onBackPressed();
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

