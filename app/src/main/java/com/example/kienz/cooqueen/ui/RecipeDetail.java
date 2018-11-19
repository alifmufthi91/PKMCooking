package com.example.kienz.cooqueen.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kienz.cooqueen.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String recipeName = getIntent().getStringExtra("namaResep");
        String recipeImg = getIntent().getStringExtra("gambarResep");
        Double recipe_ratingvalue = getIntent().getDoubleExtra("nilaireviewResep",0);
        Double recipe_ratinggiver = getIntent().getDoubleExtra("jumlahreviewResep",0);
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

        setTitle(recipeName);
        Picasso.get().load(recipeImg).into(recipeImage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
