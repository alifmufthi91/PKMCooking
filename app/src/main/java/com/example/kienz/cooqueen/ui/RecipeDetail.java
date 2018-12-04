package com.example.kienz.cooqueen.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kienz.cooqueen.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import model.PlannedResepV3;
import model.RatingResepV2;
import model.ResepV2;
import util.Constants;

public class RecipeDetail extends AppCompatActivity {

    @BindView(R.id.recipe_img_activity)
    ImageView recipeImage;
    @BindView(R.id.Recipe_desc)
    TextView recipeDesc;
    @BindView(R.id.Recipe_sajian)
    TextView recipeSaji;
    @BindView(R.id.Recipe_sumber)
    TextView recipeSource;
    @BindView(R.id.Recipe_nutrisi)
    TextView recipeNutr;
    @BindView(R.id.Recipe_bahan)
    TextView recipeIngre;
    @BindView(R.id.Recipe_alat)
    TextView recipeTool;
    @BindView(R.id.Recipe_langkah)
    TextView recipeInst;
    @BindView(R.id.submit_rating)
    Button submitRating;
    @BindView(R.id.ratingBar)
    MaterialRatingBar ratingBar;
    @BindView(R.id.ratingGiver)
    TextView ratingGiverView;
    @BindView(R.id.ratingValue)
    TextView ratingValueView;
    RatingResepV2 myrate;
    PlannedResepV3 myplan;
    Double recipe_ratingvalue;
    int recipe_ratinggiver;
    String recipeId;
    Realm realm;
    Realm profileRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeId = getIntent().getStringExtra("idResep");
        getMyRating(recipeId);
        String recipeName = getIntent().getStringExtra("namaResep");
        String recipeImg = getIntent().getStringExtra("gambarResep");
        recipe_ratingvalue = getIntent().getDoubleExtra("nilaireviewResep",0);
        recipe_ratinggiver = getIntent().getIntExtra("jumlahreviewResep",0);
        String deskripsiResep = Html.fromHtml("<b>"+recipeName+"</b> - ")+getIntent().getStringExtra("deskripsiResep");
        String sajianResep = "Sajian : "+String.valueOf(getIntent().getIntExtra("sajianResep",0));
        String sumberResep = "Sumber : "+getIntent().getStringExtra("sumberResep");
        String kandungannutResep = TextUtils.join("\n",getIntent().getStringArrayListExtra("kandungannutResep"));
        String bahanResep = TextUtils.join("\n",getIntent().getStringArrayListExtra("bahanResep"));
        String alatResep = TextUtils.join("\n",getIntent().getStringArrayListExtra("alatResep"));
        String instruksiResep = TextUtils.join("\n\n",getIntent().getStringArrayListExtra("instruksiResep"));

        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);
        recipeDesc.setText(deskripsiResep);
        recipeSaji.setText(sajianResep);
        recipeSource.setText(sumberResep);
        recipeNutr.setText(kandungannutResep);
        recipeIngre.setText(bahanResep);
        recipeTool.setText(alatResep);
        recipeInst.setText(instruksiResep);
        ratingValueView.setText(new DecimalFormat("##.##").format(recipe_ratingvalue));
        ratingGiverView.setText(String.format("(%s)", String.valueOf(recipe_ratinggiver)));
        setTitle(recipeName);
        ResepV2 thisRecipe = realm.where(ResepV2.class).equalTo("recipeId",recipeId).findFirst();
        Picasso.get().load(recipeImg).into(recipeImage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submitRating.setEnabled(false);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ratingBar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                if(!submitRating.isEnabled()&&thisRecipe!=null){
                    submitRating.setEnabled(true);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RecipeDetail.this);
                String[] category = new String[]{
                        "Sarapan",
                        "Makan siang",
                        "Makan malam"
                };
                final boolean[] checkedCategory = new boolean[]{
                        false,
                        false,
                        false

                };
                final List<String> categoryList = Arrays.asList(category);
                builder.setMultiChoiceItems(category, checkedCategory, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        checkedCategory[which] = isChecked;

                        String currentItem = categoryList.get(which);
                    }
                });

                builder.setTitle("Tambahkan ke");
                builder.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        profileRealm.executeTransactionAsync(realm1 -> {
                            int i = 0;
                            for(boolean selected : checkedCategory){
                                if(selected){
                                    PlannedResepV3 newPlan = new PlannedResepV3(recipeId,category[i]);
                                    realm1.insert(newPlan);
                                }
                                i++;
                            }
                        });
                        Toast.makeText(getApplicationContext(),
                                "Resep berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("Kembali", null);

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });

        submitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myrate!=null){
                    myrate.getRealm().executeTransactionAsync(realm1 -> {
                        RatingResepV2 newRate = realm1.where(RatingResepV2.class).equalTo("UserID",SyncUser.current().getIdentity()).equalTo("recipeID",recipeId).findFirst();
                        newRate.setRating((double) ratingBar.getRating());
                    });
                    thisRecipe.getRealm().executeTransactionAsync(realm1 -> {
                        ResepV2 olditem = realm1.where(ResepV2.class).equalTo("recipeId", recipeId).findFirst();
                        RatingResepV2 oldrating = realm1.where(RatingResepV2.class).equalTo("UserID",SyncUser.current().getIdentity()).equalTo("recipeID",recipeId).findFirst();
                        if (olditem != null&&oldrating != null) {
                            double newValue = ((olditem.getRating_value()*olditem.getRating_giver()) - oldrating.getRating() + (double) ratingBar.getRating())/olditem.getRating_giver();
                            olditem.giveRating(newValue);
                            recipe_ratinggiver=olditem.getRating_giver();
                            recipe_ratingvalue=newValue;
                        }
                    });
                }else{
                    realm.executeTransaction(realm1 -> {
                        RatingResepV2 rate = new RatingResepV2(UUID.randomUUID().toString(),SyncUser.current().getIdentity(),recipeId, (double) ratingBar.getRating());
                        realm.insert(rate);
                        myrate = rate;
                    });
                    thisRecipe.getRealm().executeTransactionAsync(realm -> {
                        ResepV2 item = realm.where(ResepV2.class).equalTo("recipeId", recipeId).findFirst();
                        if (item != null) {
                            item.giveRating((double) ratingBar.getRating());
                            recipe_ratinggiver=item.getRating_giver();
                            recipe_ratingvalue=item.getRating_value();
                        }
                    });
                    UpdateRate(recipe_ratingvalue,recipe_ratinggiver);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    private void getMyRating(String idResep){
        SyncConfiguration configuration = SyncUser.current()
                .createConfiguration(Constants.REALM_DEFAULT)
                .build();
        realm = Realm.getInstance(configuration);
        String url = Constants.REALM_USER;
        SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), url).build();
        profileRealm = Realm.getInstance(config);
        RealmResults<RatingResepV2> result = realm.where(RatingResepV2.class).equalTo("UserID",SyncUser.current().getIdentity()).equalTo("recipeID",idResep).findAllAsync();
        result.addChangeListener((ratingResepV2s, changeSet) -> {
            if(!ratingResepV2s.isEmpty()){
                myrate=ratingResepV2s.first();
                ratingBar.setRating(ratingResepV2s.first().getRating().floatValue());
            }
        });
        if(myrate==null){
            if(!result.isEmpty()){
                myrate=result.first();
            }

        }

    }

    private void UpdateRate(Double value,int giver){
        ratingValueView.setText(new DecimalFormat("##.##").format(value));
        ratingGiverView.setText(String.format("(%s)", String.valueOf(giver)));
    }



}
