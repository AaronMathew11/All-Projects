package com.adgvit.papervit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> subjectNameArrayList;
    private ArrayList<String> subjectShortArrayList;
    private ArrayList<String> subjectCodeArrayList;
    private Context context;
    private ArrayList<Boolean> checkArrayList;
    private Database database;
    public static Boolean showShimmer = true;
    private int shimmerSize = 8;

    public RecyclerViewAdapter(ArrayList<String> subjectNameArrayList, ArrayList<String> subjectShortArrayList, ArrayList<String> subjectCodeArrayList, Context context, ArrayList<Boolean> checkArrayList) {
        this.subjectNameArrayList = subjectNameArrayList;
        this.subjectShortArrayList = subjectShortArrayList;
        this.subjectCodeArrayList = subjectCodeArrayList;
        this.context = context;
        this.checkArrayList = checkArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shimmer_layout_subject, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        database = new Database(context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if(showShimmer)
        {
            holder.shimmerFrameLayout.startShimmer();
        }
        else
        {
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            holder.subjectNameTextView.setBackground(null);
            holder.subjectCodeTextView.setBackgroundResource(R.drawable.course_code_background);
            holder.subjectShortTextView.setBackground(null);
            holder.checkImage.setImageResource(R.drawable.not_checked);
            holder.checkImage.setBackground(null);

            holder.subjectNameTextView.setText(subjectNameArrayList.get(position));
            holder.subjectShortTextView.setText(subjectShortArrayList.get(position));
            holder.subjectCodeTextView.setText(subjectCodeArrayList.get(position));

            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                    String code = subjectCodeArrayList.get(position);
                    Integer codeIndex = Exam.idMapping.get(0).indexOf(code);
                    String subjectId = Exam.idMapping.get(1).get(codeIndex);
                    String subjectName = subjectNameArrayList.get(position);
                    String subjectCode = subjectCodeArrayList.get(position);
                    String subjectShort = subjectShortArrayList.get(position);

                    Intent intent = new Intent(context, Exam_1.class);

                    intent.putExtra("subjectID",subjectId);
                    intent.putExtra("subjectName",subjectName);
                    intent.putExtra("subjectCode",subjectCode);
                    intent.putExtra("subjectShort",subjectShort);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }catch (ArrayIndexOutOfBoundsException e){
                    Log.i("Papers","No Papers");


                }
                }
            });

            if(checkArrayList.get(position))
            {
                holder.checkImage.setImageResource(R.drawable.fav_fill);
            }
            else
            {
                holder.checkImage.setImageResource(R.drawable.fav_unfill);
            }

            holder.checkImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int i = Exam.subjectCodeArrayList.indexOf(subjectCodeArrayList.get(position));

                    if(checkArrayList.get(position))
                    {
                        //Removing
                        int index = Exam.favSubjectCodeArrayList.indexOf(subjectCodeArrayList.get(position));
                        String code = subjectCodeArrayList.get(position);
                        database.deleteFav(Exam.examType,code);
                        Exam.favSubjectNameArrayList.remove(subjectNameArrayList.get(position));
                        Exam.favSubjectCodeArrayList.remove(subjectCodeArrayList.get(position));
                        Exam.favSubjectShortArrayList.remove(subjectShortArrayList.get(position));
                        Exam.favCheckArrayList.remove(index);
                        Exam.checkArrayList.set(i,false);
                        Exam.updateFavRecyclerView();
                        Exam.updateAllSubjectRecyclerView();
                        holder.checkImage.setImageResource(R.drawable.not_checked);
//                    checkArrayList.set(position,false);
                    }
                    else
                    {
                        //Adding
                        String code = subjectCodeArrayList.get(position);
                        database.addFav(Exam.examType,code);
                        Exam.favSubjectNameArrayList.add(subjectNameArrayList.get(position));
                        Exam.favSubjectCodeArrayList.add(subjectCodeArrayList.get(position));
                        Exam.favSubjectShortArrayList.add(subjectShortArrayList.get(position));
                        Exam.favCheckArrayList.add(true);
                        Exam.checkArrayList.set(position,true);
                        Exam.updateFavRecyclerView();
                        Exam.updateAllSubjectRecyclerView();
                        holder.checkImage.setImageResource(R.drawable.checked);
//                    checkArrayList.set(position,true);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return showShimmer? shimmerSize : subjectNameArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout item;
        ShimmerFrameLayout shimmerFrameLayout;
        TextView subjectShortTextView, subjectNameTextView, subjectCodeTextView;
        ImageView checkImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item);
            subjectCodeTextView = itemView.findViewById(R.id.subjectCodeTextView);
            subjectNameTextView = itemView.findViewById(R.id.subjectNameTextView);
            subjectShortTextView = itemView.findViewById(R.id.subjectShortTextView);
            checkImage = itemView.findViewById(R.id.checkImage);
            cardView = itemView.findViewById(R.id.cardView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerLayout);
        }
    }
}