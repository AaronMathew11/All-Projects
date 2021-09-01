package com.adgvit.papervit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Exam extends AppCompatActivity {

    public static RecyclerViewAdapter favRecyclerViewAdapter, allSubjectRecyclerViewAdapter;
    public static RecyclerView favRecyclerView, allSubjectRecyclerView;

    public static ArrayList<String> favSubjectNameArrayList = new ArrayList<>();
    public static ArrayList<String> favSubjectShortArrayList = new ArrayList<>();
    public static ArrayList<String> favSubjectCodeArrayList = new ArrayList<>();
    public static ArrayList<Boolean> favCheckArrayList = new ArrayList<>();

    public static ArrayList<String> subjectNameArrayList = new ArrayList<>();
    public static ArrayList<String> subjectShortArrayList = new ArrayList<>();
    public static ArrayList<String> subjectCodeArrayList = new ArrayList<>();
    public static ArrayList<Boolean> checkArrayList = new ArrayList<>();
    public static ArrayList<String> subjectIdArrayList = new ArrayList<>();

    public static ArrayList<String> searchedSubjectNameArrayList = new ArrayList<>();
    public static ArrayList<String> searchedSubjectShortArrayList = new ArrayList<>();
    public static ArrayList<String> searchedSubjectCodeArrayList = new ArrayList<>();
    public static ArrayList<Boolean> searchedcheckArrayList = new ArrayList<>();

    public static ArrayList<ArrayList<String>> idMapping = new ArrayList<>();

    public static TextView favTextView,examTypeEditText;
    private EditText searchEditText;
    private Button option;
    public static Context context;
    private static String cat1 = "CAT 1";
    private static String cat2 = "CAT 2";
    private static String fat = "FAT";
    private String cat1_action = "android.intent.action.CAT1";
    private String cat2_action = "android.intent.action.CAT2";
    private String fat_action = "android.intent.action.FAT";
    Call<root> call;

    public static int filterOption = 0;

    public static String examType = "CAT 1";
    private Database database;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://adg-papervit.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_exam);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.backgroundLight));

        Toolbar toolbar = findViewById(R.id.toolbarExam);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        favRecyclerView = findViewById(R.id.favRecyclerView);
        allSubjectRecyclerView = findViewById(R.id.allSubjectRecyclerView);
        favTextView = findViewById(R.id.favTextView);
        examTypeEditText = findViewById(R.id.examTypeEditText);
        searchEditText = findViewById(R.id.searchEditText);
        option = findViewById(R.id.filterOption1);

        context = Exam.this;

        try {
            String action = getIntent().getAction();

            if(action.equals(cat2_action))
            {
                examType = cat2;
            }
            else if(action.equals(fat_action))
            {
                examType = fat;
            }
            else
            {
                examType = cat1;
            }
        }
        catch (Exception e)
        {

        }

        RecyclerViewAdapter.showShimmer = true;

        database = new Database(this);

        API api = retrofit.create(API.class);

        examTypeEditText.setText(examType);



        subjectNameArrayList = new ArrayList<>();
        subjectCodeArrayList = new ArrayList<>();
        subjectShortArrayList = new ArrayList<>();
        checkArrayList = new ArrayList<>();
        subjectIdArrayList = new ArrayList<>();

        favSubjectNameArrayList = new ArrayList<>();
        favSubjectShortArrayList = new ArrayList<>();
        favSubjectCodeArrayList = new ArrayList<>();
        favCheckArrayList = new ArrayList<>();

//        Toast.makeText(context, examType, Toast.LENGTH_SHORT).show();

        if (examType.equals(cat2)) {

            call = api.getSubCat2();
        }
        else if (examType.equals(fat)) {

            call = api.getSubFat();
        }
        else
        {
            call = api.getSubCat1();
        }

        call.enqueue(new Callback<root>() {
            @Override
            public void onResponse(Call<root> call, Response<root> response) {

                try {
                    root model = response.body();
                    for (subject s : model.getData().getSubjects()) {
                        subjectNameArrayList.add(s.getSubjectName());
                        subjectCodeArrayList.add(s.getSubjectCode());
                        subjectShortArrayList.add(s.getShortName());
                        checkArrayList.add(false);
                        subjectIdArrayList.add(s.get_id());
                    }
                    idMapping.add(subjectCodeArrayList);
                    idMapping.add(subjectIdArrayList);

                    RecyclerViewAdapter.showShimmer = false;
                    favRecyclerViewAdapter.notifyDataSetChanged();
                    allSubjectRecyclerViewAdapter.notifyDataSetChanged();
                    getFav();
                }catch (ArrayIndexOutOfBoundsException e){
                    Log.i("Exception",e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<root> call, Throwable t) {
                Log.i("INFO",t.toString());
                Dialog dialog = new Dialog(Exam.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.alert);
                dialog.show();

                Button button = dialog.findViewById(R.id.home);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Exam.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context, option);
                popupMenu.getMenuInflater().inflate(R.menu.option1,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.toString().equals("Course Name")){
                            filterOption=0;
                            searchEditText.setHint("Search by Course Name");
                        }
                        else if(item.toString().equals("Course Short Name")){
                            filterOption=1;
                            searchEditText.setHint("Search by Course Short Name");
                        }
                        else if(item.toString().equals("Course Code")){
                            filterOption=2;
                            searchEditText.setHint("Search by Course Code");
                        }
                        else {
                            filterOption=0;
                            searchEditText.setHint("Search by Course Name");
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        KeyboardVisibilityEvent.setEventListener(
                Exam.this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if(isOpen)
                        {
                            favTextView.setVisibility(View.GONE);
                            favRecyclerView.setVisibility(View.GONE);
                        }
                        else
                        {
                            favTextView.setVisibility(View.VISIBLE);
                            favRecyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = String.valueOf(charSequence).toLowerCase();
                searchedSubjectNameArrayList = new ArrayList<>();
                searchedSubjectShortArrayList = new ArrayList<>();
                searchedSubjectCodeArrayList = new ArrayList<>();
                searchedcheckArrayList = new ArrayList<>();
                searchedSubjectNameArrayList.clear();
                searchedSubjectShortArrayList.clear();
                searchedSubjectCodeArrayList.clear();
                searchedcheckArrayList.clear();
                if(filterOption==0) {
                    for (String name : subjectNameArrayList) {
                        if (isContain(name.toLowerCase(), searchText)) {

                            //Log.i("INFO",name);
                            searchedSubjectNameArrayList.add(name);
                            //Log.i("INFO",searchedSubjectNameArrayList.toString());
                            searchedSubjectCodeArrayList.add(subjectCodeArrayList.get(subjectNameArrayList.indexOf(name)));
                            searchedSubjectShortArrayList.add(subjectShortArrayList.get(subjectNameArrayList.indexOf(name)));
                            searchedcheckArrayList.add(checkArrayList.get(subjectNameArrayList.indexOf(name)));

                        }
                    }

                }
                else  if(filterOption==1){
                    //Log.i("filterOption","One");
                    //Log.i("filterOption",String.valueOf(filterOption));
                    for (String name : subjectShortArrayList) {
                        if (isContain(name.toLowerCase(), searchText)) {

                            //Log.i("INFO",name);
                            searchedSubjectShortArrayList.add(name);
                            //Log.i("INFO",searchedSubjectNameArrayList.toString());
                            searchedSubjectCodeArrayList.add(subjectCodeArrayList.get(subjectShortArrayList.indexOf(name)));
                            searchedSubjectNameArrayList.add(subjectNameArrayList.get(subjectShortArrayList.indexOf(name)));
                            searchedcheckArrayList.add(checkArrayList.get(subjectShortArrayList.indexOf(name)));

                        }
                    }
                }
                else if(filterOption==2){
                    //Log.i("filterOption","Two");
                    //Log.i("filterOption",String.valueOf(filterOption));
                    for (String name : subjectCodeArrayList) {
                        if (isContain(name.toLowerCase(), searchText)) {

                            //Log.i("INFO",name);
                            searchedSubjectCodeArrayList.add(name);
                            //Log.i("INFO",searchedSubjectNameArrayList.toString());
                            searchedSubjectShortArrayList.add(subjectShortArrayList.get(subjectCodeArrayList.indexOf(name)));
                            searchedSubjectNameArrayList.add(subjectNameArrayList.get(subjectCodeArrayList.indexOf(name)));
                            searchedcheckArrayList.add(checkArrayList.get(subjectCodeArrayList.indexOf(name)));

                        }
                    }
                }
                else {
                    for (String name : subjectNameArrayList) {
                        if (isContain(name.toLowerCase(), searchText)) {

                            //Log.i("INFO",name);
                            searchedSubjectNameArrayList.add(name);
                            //Log.i("INFO",searchedSubjectNameArrayList.toString());
                            searchedSubjectCodeArrayList.add(subjectCodeArrayList.get(subjectNameArrayList.indexOf(name)));
                            searchedSubjectShortArrayList.add(subjectShortArrayList.get(subjectNameArrayList.indexOf(name)));
                            searchedcheckArrayList.add(checkArrayList.get(subjectNameArrayList.indexOf(name)));

                        }
                    }
                }
                allSubjectRecyclerViewAdapter = new RecyclerViewAdapter(searchedSubjectNameArrayList, searchedSubjectShortArrayList, searchedSubjectCodeArrayList, context, searchedcheckArrayList);
                allSubjectRecyclerView.setAdapter(allSubjectRecyclerViewAdapter);
                allSubjectRecyclerViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        favRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allSubjectRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateAllSubjectRecyclerView();
        updateFavRecyclerView();
    }

    public static void updateFavRecyclerView()
    {
        if(favSubjectNameArrayList.size() == 0)
        {
            favTextView.setVisibility(View.GONE);
            favRecyclerView.setVisibility(View.GONE);
        }
        else {
            favTextView.setVisibility(View.VISIBLE);
            favRecyclerView.setVisibility(View.VISIBLE);
        }

        favRecyclerViewAdapter = new RecyclerViewAdapter(favSubjectNameArrayList,favSubjectShortArrayList,favSubjectCodeArrayList,context, favCheckArrayList);
        favRecyclerView.setAdapter(favRecyclerViewAdapter);

    }

    public static void updateAllSubjectRecyclerView()
    {
        allSubjectRecyclerViewAdapter = new RecyclerViewAdapter(subjectNameArrayList,subjectShortArrayList,subjectCodeArrayList,context, checkArrayList);
        allSubjectRecyclerView.setAdapter(allSubjectRecyclerViewAdapter);
    }

    public void getFav()
    {
        Cursor data = database.getFav(examType);
        while (data.moveToNext())
        {
            favSubjectCodeArrayList.add(data.getString(0));
            int index = subjectCodeArrayList.indexOf(data.getString(0));
            favSubjectShortArrayList.add(subjectShortArrayList.get(index));
            favSubjectNameArrayList.add(subjectNameArrayList.get(index));
            favCheckArrayList.add(true);
            checkArrayList.set(index,true);
            updateAllSubjectRecyclerView();
            updateFavRecyclerView();
        }
    }

    private static boolean isContain(String source, String subItem){
        String pattern = "^.*" + subItem + ".*$";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(source);
        return m.find();
    }

    @Override
    public void onBackPressed() {
        call.cancel();
        RecyclerViewAdapter.showShimmer = false;
        super.onBackPressed();
//        Intent intent = new Intent(this,MainActivity.class);
//        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
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