package com.example.kienz.cooqueen.ui;

import android.content.Context;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.adapter.RecipeSearchAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import model.ResepV2;
import util.Constants;

public class SearchRecipe extends AppCompatActivity {

    private RecipeSearchAdapter mAdapter;
    private Realm realm;
    public ArrayList<ResepV2> mRecipes = new ArrayList<>();
    @BindView(R.id.recyclerView_search)
    RecyclerView recy;
    @BindView(R.id.search_recipe)
    SearchView SearchField;
    @BindView(R.id.search_notfound)
    TextView notFound;
    private RealmResults<ResepV2> reseps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String que = getIntent().getStringExtra("resep");
        Log.d("hanuwa",que);
        GatherRecipes(que);
//        RealmResults<ResepV2> reseps = getRecipes(que);
        setContentView(R.layout.activity_search_recipe);
        ButterKnife.bind(this);
        SearchField.setFocusable(false);
        SearchField.setIconified(false);
        SearchField.setQuery(que,false);
        SearchField.setOnCloseListener(new SearchView.OnCloseListener(){
            @Override
            public boolean onClose() {
                SearchField.setQuery("",false);
                SearchField.clearFocus();
                return true;
            }
        });
        SearchField.clearFocus();
        SearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String s) {
                mRecipes.clear();
                if(internet_connection()){
                    mRecipes.addAll(getRecipes(s));
                }else{
                    final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                            "Tidak ada Koneksi Internet.",
                            Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.colorPrimary));
                    snackbar.show();
                }
                mAdapter.swapItems(mRecipes);
                mAdapter.notifyDataSetChanged();
                if(mRecipes.isEmpty()){
                    notFound.setVisibility(View.VISIBLE);
                }else{
                    notFound.setVisibility(View.GONE);
                }
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String s) {
                SearchField.clearFocus();
                return true;
            }
        });
        if(reseps != null){
            mRecipes.addAll(realm.copyFromRealm(reseps));
        }

        mAdapter = new RecipeSearchAdapter(getApplicationContext(), mRecipes);
        recy.setAdapter(mAdapter);

        if(mRecipes.isEmpty()){
            notFound.setVisibility(View.VISIBLE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchRecipe.this);
        recy.setLayoutManager(layoutManager);
        recy.setHasFixedSize(true);
    }

    private RealmResults<ResepV2> getRecipes(String recipeName) {
        SyncConfiguration configuration = SyncUser.current()
                .createConfiguration(Constants.REALM_DEFAULT)
                .build();
        realm = Realm.getInstance(configuration);
        return realm
                .where(ResepV2.class)
                .contains("name",recipeName,Case.INSENSITIVE)
                .findAll();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    private void GatherRecipes(String que){
        if(internet_connection()){
            try{
                reseps = getRecipes(que);
                ResepV2 a = realm.where(ResepV2.class).findFirst();
                Log.d("hanuwa",a.getName());
            }catch (NullPointerException e){
                Log.d("hanuwa",e.getMessage());
            }
        }else{
            final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    "Tidak ada Koneksi Internet.",
                    Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimary));
            snackbar.show();

        }
    }

    boolean internet_connection() {
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


}
