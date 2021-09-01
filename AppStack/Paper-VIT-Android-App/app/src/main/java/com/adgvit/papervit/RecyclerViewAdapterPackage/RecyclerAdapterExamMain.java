package com.adgvit.papervit.RecyclerViewAdapterPackage;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adgvit.papervit.Exam;
import com.adgvit.papervit.MainActivity;
import com.adgvit.papervit.R;

public class RecyclerAdapterExamMain extends RecyclerView.Adapter<RecyclerAdapterExamMain.ExamViewHolder> {

    private String[] examName;
    private String[] examSubName;

    Context context;

    public RecyclerAdapterExamMain(String[] examName, String[] examSubName) {
        this.examName = examName;
        this.examSubName = examSubName;
    }

    @NonNull
    @Override
    public RecyclerAdapterExamMain.ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exam_layout_item,parent,false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterExamMain.ExamViewHolder holder, int position) {

        String name = examName[position];
        String subName = examSubName[position];

        holder.examNameTV.setText(name);
        holder.examSubNameTV.setText(subName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exam.examType = "CAT 1";                                    //For CAT1, NOW testing
                Intent intent = new Intent(context,Exam.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                //Exam.examType = "CAT 2";
                //Intent intent = new Intent(MainActivity.this,Exam.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //context.startActivity(intent);

                //Exam.examType = "FAT";
                //Intent intent = new Intent(MainActivity.this,Exam.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return examName.length;
    }

    public class ExamViewHolder extends RecyclerView.ViewHolder{

        TextView examNameTV;
        TextView examSubNameTV;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);

            examNameTV = itemView.findViewById(R.id.examNameTextView);
            examSubNameTV = itemView.findViewById(R.id.examSubNameTextView);

            context = itemView.getContext();

        }
    }

}
