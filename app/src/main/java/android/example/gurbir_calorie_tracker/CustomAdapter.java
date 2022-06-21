package android.example.gurbir_calorie_tracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, foodname, calories, protein, carbs;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList foodname, ArrayList calories,
                  ArrayList protein, ArrayList carbs){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.foodname = foodname;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.foodname.setText(String.valueOf(foodname.get(position)));
        holder.calories.setText(String.valueOf(calories.get(position)));
        holder.protein.setText(String.valueOf(protein.get(position)));
        holder.carbs.setText(String.valueOf(carbs.get(position)));

        //Recyclerview onClickListener


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, foodname, calories, protein, carbs;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_txt);
            foodname = itemView.findViewById(R.id.foodnameText);
            calories = itemView.findViewById(R.id.calorieNumberText);
            protein = itemView.findViewById(R.id.proteinNumberText);
            carbs = itemView.findViewById(R.id.carbNumberText);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
        }

    }

}
