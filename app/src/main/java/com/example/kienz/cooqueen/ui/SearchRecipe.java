package com.example.kienz.cooqueen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.adapter.RecipeSearchAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import model.Resepi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchRecipe extends AppCompatActivity {

    private RecipeSearchAdapter mAdapter;
    private Realm realm;
    public ArrayList<Resepi> mRecipes = new ArrayList<>();
    @BindView(R.id.recyclerView_search)
    RecyclerView recy;
    @BindView(R.id.search_recipe)
    SearchView SearchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String que = getIntent().getStringExtra("resep");
        RealmResults<Resepi> reseps = getRecipes(que);
        setContentView(R.layout.activity_search_recipe);
        ButterKnife.bind(this);
        SearchField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchField.setIconified(false);
            }
        });
        SearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String s) {
                mRecipes.clear();
                mRecipes.addAll(getRecipes(s));
                mAdapter.swapItems(mRecipes);
                mAdapter.notifyDataSetChanged();
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String s) {
                SearchField.clearFocus();
                return true;
            }
        });
        mRecipes.addAll(realm.copyFromRealm(reseps));
        mAdapter = new RecipeSearchAdapter(getApplicationContext(), mRecipes);
        recy.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchRecipe.this);
        recy.setLayoutManager(layoutManager);
        recy.setHasFixedSize(true);
    }

    private RealmResults<Resepi> getRecipes(String recipeName) {
        SyncConfiguration configuration = SyncUser.current()
                .createConfiguration("realms://cooquenpkmjtk16.us1a.cloud.realm.io" + "/default")
                .build();
        realm = Realm.getInstance(configuration);

        return realm
                .where(Resepi.class)
                .contains("name",recipeName,Case.INSENSITIVE)
                .findAll();
    }

//    private void getRecipes(String ingredient1, String ingredient2) {
//        final FoodService foodService = new FoodService();
//
//        foodService.findRecipes(ingredient1, ingredient2, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                mRecipes = foodService.processResults(response);
//
//
//                SearchRecipe.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter = new RecipeSearchAdapter(getApplicationContext(), mRecipes);
//                        recy.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchRecipe.this);
//                        recy.setLayoutManager(layoutManager);
//                        recy.setHasFixedSize(true);
//                    }
//                });
//
//            }
//        });
//    }
}
