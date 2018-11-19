package com.example.kienz.cooqueen.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String que = getIntent().getStringExtra("resep");
        RealmResults<Resepi> reseps = getRecipes(que);
        setContentView(R.layout.activity_search_recipe);
        ButterKnife.bind(this);
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
