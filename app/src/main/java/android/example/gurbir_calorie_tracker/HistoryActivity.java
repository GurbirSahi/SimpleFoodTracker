package android.example.gurbir_calorie_tracker;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CoordinatorLayout coordinatorLayout;
    private static final String TAG = "history";
    DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    CustomAdapter customAdapter;

    /**
     * Creates the content view and toolbar, sets up the drawer layout and the
     * action bar toggle, and sets up the navigation view.
     *
     * @param savedInstanceState Saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
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

        recyclerView = (RecyclerView) findViewById(R.id.historyRecyclerView);
        databaseHelper = new DatabaseHelper(this);
        populateListView();

    }

    private void populateListView() {
        Log.d(TAG, "Populate Listview");
        Cursor data = databaseHelper.getData();
        ArrayList<String> foodnames = new ArrayList<>();
        ArrayList<String> calories = new ArrayList<>();
        ArrayList<String> protein = new ArrayList<>();
        ArrayList<String> carbs = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();

        while (data.moveToNext()) {
            id.add(data.getString(0));
            foodnames.add(data.getString(1));
            calories.add(data.getString(2));
            protein.add(data.getString(3));
            carbs.add(data.getString(4));


        }

         customAdapter = new CustomAdapter(HistoryActivity.this,HistoryActivity.this, id, foodnames, calories,
                protein, carbs);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));

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
                Intent todayLogIntent = new Intent(this, TodayLogActivity.class );
                startActivity(todayLogIntent);
                return true;
            case R.id.drawer_history:
                // Handle the gallery action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "History", Toast.LENGTH_SHORT).show();
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