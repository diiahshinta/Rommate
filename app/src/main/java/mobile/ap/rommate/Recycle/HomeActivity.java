package mobile.ap.rommate.Recycle;

import android.app.SearchManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.*;

import java.util.ArrayList;
import java.util.List;

import mobile.ap.rommate.R;

public class HomeActivity extends AppCompatActivity {

    private List<VenueModel> venuesList;
    private RecyclerView.Adapter venuesRecyclerAdapter;
    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        venuesList = new ArrayList<>();
        mainController = new MainControllerImplement();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.venues_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        assert mRecyclerView != null;
        mRecyclerView.setLayoutManager(linearLayoutManager);
        venuesRecyclerAdapter = new VenueRecyclerAdapter(venuesList, HomeActivity.this);
        mRecyclerView.setAdapter(venuesRecyclerAdapter);

        mainController.getVenuesData("Malang,Indonesia", venuesList, venuesRecyclerAdapter, HomeActivity.this); //initialize the list in an arbitrary location
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String queryString) {

                mainController.getVenuesData(queryString, venuesList, venuesRecyclerAdapter, HomeActivity.this);

                searchView.clearFocus();
                searchMenuItem.collapseActionView();
                searchView.setQuery("", false);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String queryString) {
                return false;
            }
        });
        return true;
    }
}