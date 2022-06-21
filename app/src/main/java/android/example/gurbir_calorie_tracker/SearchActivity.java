 package android.example.gurbir_calorie_tracker;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CoordinatorLayout coordinatorLayout;
    EditText et_userEntry;
    Button searchButton;
    TextView usdaSearchResults;
    private final String url = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=DEMO_KEY&query=chicken%20breast&pageSize=10&pageNumber=1";
    String tempUrl = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=DEMO_KEY&query=";
    String searchUrl =  "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=DEMO_KEY&query=";

    private final String appid = "kqYBKwAaMvfJdMreygYBepa4mlVpbzmJkpkoUbna";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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

        usdaSearchResults = findViewById(R.id.tv_usdaResults);
        searchButton = findViewById(R.id.bt_enterbutton);
        et_userEntry = findViewById(R.id.et_userentry);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entry = et_userEntry.getText().toString();
                if(entry.equals("")) {
                    Toast.makeText(SearchActivity.this, "Please enter a food", Toast.LENGTH_SHORT).show();
                }
                else {
                    String[] splitText = entry.split(" ");
                    if(splitText.length==1){
                        tempUrl = searchUrl + splitText[0];

                    }
                    else {
                        tempUrl = searchUrl + splitText[0] + "%20" + splitText[1];

                    }
                    getWeather(v);


                }

                Toast.makeText(SearchActivity.this, "Searching Now", Toast.LENGTH_SHORT).show();

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
                return true;
            case R.id.drawer_foodcategories:
                // Handle the tools action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Food Categories", Toast.LENGTH_SHORT).show();
                Intent categoryIntent = new Intent(this, CategoriesActivity.class);
                startActivity(categoryIntent);
                return true;
            default:
                return false;
        }
    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    public void getWeather(View view) {



        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, tempUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("response", response.toString());
                            String foodName;
                            String CalorieName;
                            String calorieNumber;
                            String ProteinName;
                            String ProteinNumber;
                            String carbNumber;
                            JSONArray jsonArray = response.getJSONArray("foods");
                            JSONObject foodObject = jsonArray.getJSONObject(0);
                            foodName = foodObject.getString("description");
                            JSONArray nutrientArray = foodObject.getJSONArray("foodNutrients");
                            JSONObject proteinObject = nutrientArray.getJSONObject(0);
                            ProteinName = proteinObject.getString("nutrientName");
                            ProteinNumber = proteinObject.getString("value");
                            JSONObject calorieObject = nutrientArray.getJSONObject(3);
                            CalorieName = calorieObject.getString("nutrientName");
                            calorieNumber = calorieObject.getString("value");
                            JSONObject carbObject = nutrientArray.getJSONObject(2);
                            carbNumber = carbObject.getString("value");
                            Log.d("works", foodName);
                            String complete = "Food (Values per 100g)\n\n" + foodName + "\n\n" + CalorieName + " cals\n" + calorieNumber + "\n"
                                    + ProteinName + "\n" + ProteinNumber + " g\n" + "Carbohydrates" + "\n" + carbNumber + " g";
                            usdaSearchResults.setText(complete);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("response", "Error: " + error.getMessage());
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        // Adding request to request queue
        Volley.newRequestQueue(this).add(jsonObjReq);
    }
}

