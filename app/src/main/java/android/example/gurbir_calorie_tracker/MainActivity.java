package android.example.gurbir_calorie_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
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


