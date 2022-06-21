package android.example.gurbir_calorie_tracker;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.loader.app.LoaderManager;

import android.companion.CompanionDeviceManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CoordinatorLayout coordinatorLayout;
    private TextView commonFoods;
    private EditText newFoodEntry;
    private Button newFoodButton;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> tempList;
    String listString = "";



    public CategoriesActivity(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
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

        commonFoods = (TextView) findViewById(R.id.commonFoodsTV);
        newFoodEntry = (EditText) findViewById(R.id.newfood_tv);
        newFoodButton = (Button) findViewById(R.id.newFoodButton);

            String[] values = new String[]{"\n\nChicken Breast: 100 grams, 165 cals, 31g protein, 0g carbs\n\n", "Chicken Thighs: 100 grams, 177 cals, 24g protein, 0g carbs\n\n",
                    "Chicken Drumstick: 100 grams, 159 cals, 18g protein, 0g carbs\n\n", "Chicken Wings: 100 grams, 203 cals, 30g protein, 0g carbs\n\n",
                    "Basmati Rice: 100 grams, 130 cals, 2.7g protein, 28g carbs\n\n"};

            for (int i = 0; i < values.length; ++i) {
                list.add(values[i]);
            }




        newFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = newFoodEntry.getText().toString();
                String tempString="";
                list.add(foodName + "\n\n" );
                for (String s : list) {
                    tempString += s + " ";
                }

                commonFoods.setText(tempString);

            }
        });




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
                Intent todayLogIntent = new Intent(this, TodayLogActivity.class);
                startActivity(todayLogIntent);
                return true;
            case R.id.drawer_history:
                // Handle the gallery action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "History", Toast.LENGTH_SHORT).show();
                Intent historyIntent = new Intent(this, HistoryActivity.class);
                startActivity(historyIntent);
                return true;
            case R.id.drawer_foodsearch:
                // Handle the slideshow action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Food Search", Toast.LENGTH_SHORT).show();
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.drawer_foodcategories:
                // Handle the tools action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Food Categories", Toast.LENGTH_SHORT).show();
            default:
                return false;
        }
    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}



