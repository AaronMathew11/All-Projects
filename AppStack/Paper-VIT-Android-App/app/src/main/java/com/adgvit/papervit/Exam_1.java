package com.adgvit.papervit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class    Exam_1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    Call<root1> call;
    private RecyclerViewAdapter2 recyclerViewAdapter;
    private ArrayList<Integer> randomImage = new ArrayList<Integer>();
    private ArrayList<String> courseSlot = new ArrayList<String>();
    private ArrayList<String> courseDesc = new ArrayList<String>();
    private TextView subjectShortTextView, subjectNameTextView, subjectNameTextView2, subjectCodeTextView;

    public static ArrayList<String> paperIdArrayList = new ArrayList<>();
    public static ArrayList<String> paperSlotArrayList = new ArrayList<>();
    public static ArrayList<String> paperYearArrayList = new ArrayList<>();
    public static ArrayList<String> paperExamArrayList = new ArrayList<>();
    public static ArrayList<String> paperFileNameArrayList = new ArrayList<>();
    public static ArrayList<String> paperUrlList = new ArrayList<>();


    private static String cat1 = "CAT 1";
    private static String cat2 = "CAT 2";
    private static String fat = "FAT";

    public static String subjectName, subjectCode, subjectShort, subjectId;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://adg-papervit.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_exam_1);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.backgroundLight));

        Toolbar toolbar = findViewById(R.id.toolbarSubjects);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        subjectId = intent.getStringExtra("subjectID");
        subjectName = intent.getStringExtra("subjectName");
        subjectCode = intent.getStringExtra("subjectCode");
        subjectShort = intent.getStringExtra("subjectShort");


        recyclerView = findViewById(R.id.recyclerView);
        subjectShortTextView = findViewById(R.id.subjectShortTextView);
        //subjectNameTextView = findViewById(R.id.subjectNameTextView);
        //subjectNameTextView2 = findViewById(R.id.subjectNameTextView2);
        //subjectCodeTextView = findViewById(R.id.subjectCodeTextView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        RecyclerViewAdapter2.showShimmer = true;

        //subjectCodeTextView.setText(subjectCode);
        subjectShortTextView.setText(subjectShort);
        //subjectNameTextView.setText(subjectName);
        //subjectNameTextView2.setText(subjectName);


        paperIdArrayList = new ArrayList<>();
        paperSlotArrayList = new ArrayList<>();
        paperExamArrayList = new ArrayList<>();
        paperYearArrayList = new ArrayList<>();
        paperFileNameArrayList = new ArrayList<>();


        API api = retrofit.create(API.class);



        if (Exam.examType.equals(cat2)) {

            call = api.getPaperCat2(subjectId);
        }
        else if (Exam.examType.equals(fat)) {

            call = api.getPaperFat(subjectId);
        }
        else
        {
            call = api.getPaperCat1(subjectId);
        }

        call.enqueue(new Callback<root1>() {
            @Override
            public void onResponse(Call<root1> call, Response<root1> response) {

                try {

                    root1 model = response.body();

                    for (paperObject item : model.getData().getPapers()) {
                        paperIdArrayList.add(item.get_id());
                        paperSlotArrayList.add(item.getSlot());
                        paperUrlList.add(item.getUrl());
                        // paperExamArrayList.add(exam);
                        paperYearArrayList.add(item.getSemester());
                        paperFileNameArrayList.add(item.getFileName());

                    }
                    if (paperIdArrayList.isEmpty()) {
                        Dialog dialog = new Dialog(Exam_1.this);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.nopaper);
                        dialog.show();

                        Button button = dialog.findViewById(R.id.homeButton);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Exam_1.super.onBackPressed();
                            }
                        });
                    }
                    RecyclerViewAdapter2.showShimmer = false;
                    recyclerViewAdapter.notifyDataSetChanged();
                } catch (NullPointerException e) {
                    Log.i("Papers", "No Papers");
                }
            }

            @Override
            public void onFailure(Call<root1> call, Throwable t) {
                Log.i("INFO",t.toString());
                Dialog dialog = new Dialog(Exam_1.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.alert);
                dialog.show();

                Button button = dialog.findViewById(R.id.home);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Exam_1.this, Exam.class);
                        startActivity(intent);
                    }
                });
            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter2(paperYearArrayList,paperSlotArrayList, Exam_1.this);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void onBackPressed() {

        call.cancel();
        RecyclerViewAdapter.showShimmer = false;
        super.onBackPressed();
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