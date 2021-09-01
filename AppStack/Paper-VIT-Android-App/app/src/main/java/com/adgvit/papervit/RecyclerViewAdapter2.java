package com.adgvit.papervit;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {

    private ArrayList<Integer> randomImage = new ArrayList<>();
    private ArrayList<String> courseSlot;
    private ArrayList<String> courseDesc;
    private Context context;
    public static Boolean showShimmer = true;
    private int shimmerSize = 8;


    public RecyclerViewAdapter2(ArrayList<String> courseDesc, ArrayList<String> courseSlot, Context context) {
        this.courseDesc = courseDesc;
        this.courseSlot = courseSlot;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if(showShimmer){

            holder.shimmerFrameLayout.startShimmer();

        }else{

            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            holder.randomImageView.setImageDrawable(null);
            holder.randomImageView.setBackground(null);
            holder.courseSlotTextView.setBackground(null);
            holder.courseDescTextView.setBackground(null);
            Random random = new Random();
            int a =random.nextInt(11);
            holder.randomImageView.setImageResource(randomImage.get(position%10));
            holder.courseSlotTextView.setBackgroundResource(R.drawable.course_code_background);
            holder.courseSlotTextView.setText(courseSlot.get(position));
            holder.courseDescTextView.setText(courseDesc.get(position));

            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, Exam_2.class);
                    intent.putExtra("subName", Exam_1.subjectName);
                    intent.putExtra("subShort", Exam_1.subjectShort);
                    intent.putExtra("subCode", Exam_1.subjectCode);
                    intent.putExtra("subYear", courseDesc.get(position));
                    intent.putExtra("subSlot", courseSlot.get(position));
                    intent.putExtra("paperId", Exam_1.paperIdArrayList.get(position));
                    intent.putExtra("fileName",Exam_1.paperFileNameArrayList.get(position));
                    intent.putExtra("fileUrl",Exam_1.paperUrlList.get(position));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return showShimmer? shimmerSize : courseSlot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView courseSlotTextView,courseDescTextView;
        ImageView randomImageView;
        ConstraintLayout constraintLayout;
        ShimmerFrameLayout shimmerFrameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            randomImage.add(R.drawable.paper1);
            randomImage.add(R.drawable.paper2);
            randomImage.add(R.drawable.paper3);
            randomImage.add(R.drawable.paper4);
            randomImage.add(R.drawable.paper5);
            randomImage.add(R.drawable.paper6);
            randomImage.add(R.drawable.paper7);
            randomImage.add(R.drawable.paper8);
            randomImage.add(R.drawable.paper9);
            randomImage.add(R.drawable.paper10);

            courseSlotTextView = itemView.findViewById(R.id.courseSlotTextView);
            courseDescTextView = itemView.findViewById(R.id.courseDescTextView);
            randomImageView = itemView.findViewById(R.id.randomImageView);
            constraintLayout = itemView.findViewById(R.id.viewPaperLayout);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerLayout2);


        }
    }

}
