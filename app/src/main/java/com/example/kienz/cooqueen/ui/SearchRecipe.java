package com.example.kienz.cooqueen.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
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
import model.Resepop;
import util.Constants;

public class SearchRecipe extends AppCompatActivity {

    private RecipeSearchAdapter mAdapter;
    private Realm realm;
    public ArrayList<Resepop> mRecipes = new ArrayList<>();
    @BindView(R.id.recyclerView_search)
    RecyclerView recy;
    @BindView(R.id.search_recipe)
    SearchView SearchField;
    @BindView(R.id.search_notfound)
    TextView notFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String que = getIntent().getStringExtra("resep");
        RealmResults<Resepop> reseps = getRecipes(que);
        setContentView(R.layout.activity_search_recipe);
        ButterKnife.bind(this);
        SearchField.setFocusable(false);
        SearchField.setIconified(false);
        SearchField.setOnCloseListener(new SearchView.OnCloseListener(){
            @Override
            public boolean onClose() {
                SearchField.setQuery("",false);
                SearchField.clearFocus();
                return true;
            }
        });
        SearchField.clearFocus();
//        SearchField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SearchField.setIconified(false);
//            }
//        });
        SearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String s) {
                mRecipes.clear();
                mRecipes.addAll(getRecipes(s));
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
        mRecipes.addAll(realm.copyFromRealm(reseps));
        mAdapter = new RecipeSearchAdapter(getApplicationContext(), mRecipes);
        recy.setAdapter(mAdapter);

        if(mRecipes.isEmpty()){
            notFound.setVisibility(View.VISIBLE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchRecipe.this);
        recy.setLayoutManager(layoutManager);
        recy.setHasFixedSize(true);
    }

    private RealmResults<Resepop> getRecipes(String recipeName) {
        SyncConfiguration configuration = SyncUser.current()
                .createConfiguration(Constants.REALM_DEFAULT)
                .build();
        realm = Realm.getInstance(configuration);
        return realm
                .where(Resepop.class)
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
