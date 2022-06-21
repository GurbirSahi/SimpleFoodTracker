package android.example.gurbir_calorie_tracker;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TodayLogActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CoordinatorLayout coordinatorLayout;
    DatabaseHelper databaseHelper;
    private Button enterButton;
    private EditText calorieEntry;
    private EditText proteinEntry;
    private EditText carbEntry;
    private EditText foodNameEntry;
    private TextView totalCalorieTV;
    private TextView totalProteinTV;
    private TextView totalCarbTV;
    private Button resetButton;
    private int calorieTotal = 0;
    private int proteinTotal =0;
    private int carbTotal = 0 ;
    private int currentCalorie = 0;
    private int currentProtein =0;
    private int currentCarb = 0 ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }



        calorieEntry = (EditText) findViewById(R.id.userEntry_calories);
        proteinEntry = (EditText) findViewById(R.id.userEntry_protein);
        carbEntry = (EditText) findViewById(R.id.userEntry_carbs);
        foodNameEntry = (EditText) findViewById(R.id.userEntry_foodName);
        enterButton = (Button) findViewById(R.id.enterButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        totalCalorieTV = (TextView) findViewById(R.id.calorieTotal);
        totalProteinTV = (TextView) findViewById(R.id.proteinTotal);
        totalCarbTV = (TextView) findViewById(R.id.carbTotal);

        SharedPreferences newSP = getSharedPreferences("mySavedInfo", Context.MODE_PRIVATE);

        totalCalorieTV.setText(String.valueOf(calorieTotal));
        totalProteinTV.setText(String.valueOf(proteinTotal));
        totalCarbTV.setText(String.valueOf(carbTotal));


        databaseHelper = new DatabaseHelper(this);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = foodNameEntry.getText().toString();
                String calories = calorieEntry.getText().toString();
                String protein = proteinEntry.getText().toString();
                String carbs = carbEntry.getText().toString();

                currentCalorie = Integer.parseInt(calories);
                currentProtein = Integer.parseInt(protein);
                currentCarb = Integer.parseInt(carbs);

                calorieTotal += currentCalorie ;
                proteinTotal += currentProtein;
                carbTotal +=currentCarb;

                if (calories.length()!=0 ) {
                    addData(foodName, calories, protein, carbs);
                    totalCalorieTV.setText(String.valueOf(calorieTotal));
                    totalProteinTV.setText(String.valueOf(proteinTotal));
                    totalCarbTV.setText(String.valueOf(carbTotal));

                    SharedPreferences.Editor editor = newSP.edit();
                    editor.putInt("cals", calorieTotal);
                    editor.putInt("prot", proteinTotal);
                    editor.putInt("carbs", carbTotal);
                    editor.commit();

                    foodNameEntry.setText("");
                    calorieEntry.setText("");
                    proteinEntry.setText("");
                    carbEntry.setText("");
                    currentCalorie = 0;
                    currentProtein = 0;
                    currentCarb = 0;

                } else {
                    Toast.makeText(TodayLogActivity.this, "Please Enter Text", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalCalorieTV.setText("0");
                totalProteinTV.setText("0");
                totalCarbTV.setText("0");
                calorieTotal = 0 ;
                proteinTotal = 0;
                carbTotal =0;


            }
        });


    }

    public void addData(String foodname, String calories, String protein, String carbs) {

        databaseHelper.addData(foodname, calories, protein, carbs);
        Toast.makeText(TodayLogActivity.this, "New entry added", Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.drawer_todayslog:
                // Handle the camera import action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Today's Log", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.drawer_history:
                // Handle the gallery action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "History", Toast.LENGTH_SHORT).show();
                Intent historyIntent = new Intent(this, HistoryActivity.class );
                startActivity(historyIntent);
                return true;
            case R.id.drawer_foodsearch:
                // Handle the slideshow action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Food Search", Toast.LENGTH_SHORT).show();
                Intent searchIntent = new Intent(this, SearchActivity.class );
                startActivity(searchIntent);
                return true;
            case R.id.drawer_foodcategories:
                // Handle the tools action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Food Categories", Toast.LENGTH_SHORT).show();
                Intent categoryIntent = new Intent(this, CategoriesActivity.class );
                startActivity(categoryIntent);
                return true;
            default:
                return false;
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}