package com.example.kienz.cooqueen.ui;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.adapter.RecipeFragHomeAdapter;
import com.recombee.api_clients.RecombeeClient;
import com.recombee.api_clients.api_requests.RecommendItemsToUser;
import com.recombee.api_clients.bindings.Recommendation;
import com.recombee.api_clients.bindings.RecommendationResponse;
import com.recombee.api_clients.exceptions.ApiException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import model.Recommender;
import model.ResepV2;
import util.Constants;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tab1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tab1#newInstance} factory method to
 * create an instance of this fragment.
 */


public class tab1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public RecipeFragHomeAdapter adapter;
    public ArrayList<Recommender> mRecipes;
    @BindView (R.id.recyclerView_fraghome) RecyclerView recy;
    private Realm defaultrealm;
    private ArrayList<ResepV2> topRated;
    ArrayList<String> listRecipeId;
    ArrayList<ResepV2> listRecommend;
    ArrayList<ResepV2> listRecommend2;

    private OnFragmentInteractionListener mListener;

    public tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static tab1 newInstance(String param1, String param2) {
        tab1 fragment = new tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public void onResume() {
        mRecipes = new ArrayList<>();
        listRecipeId = new ArrayList<>();
        listRecommend = new ArrayList<>();
        listRecommend2 = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recy.setLayoutManager(layoutManager);
        new SyncRecommendation().execute();
        topRated = new ArrayList<>();
        Recommender A = new Recommender("Recommended for you",listRecommend2);
        Recommender B = new Recommender("Top Rated",topRated);
        mRecipes.add(A);
        mRecipes.add(B);



        for (Recommender h : mRecipes) {
            Log.d("namonn",h.getTitle());
        }

        adapter = new RecipeFragHomeAdapter(getActivity(),mRecipes);
        getTopRated();
        recy.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        ButterKnife.bind(this,v);
        // Inflate the layout for this fragment
        return v;
    }

//    private void getRecipes(String ingredient1, String ingredient2) {
//        final FoodService foodService = new FoodService();
//        foodService.findRecipes(ingredient1, ingredient2, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("statmsg","no");
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                Log.d("statmsg","yes");
//                mRecipes = foodService.processResults(response);
//                for (ResepV2 h : mRecipes) {
//                    Log.d("namonn",h.getName());
//                }
//            }
//        });
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    void getTopRated(){
        SyncConfiguration configuration = SyncUser.current()
                .createConfiguration(Constants.REALM_DEFAULT)
                .build();
        defaultrealm  = Realm.getInstance(configuration);
        RealmResults<ResepV2> topratedRecipe = defaultrealm.where(ResepV2.class).sort("rating_value",Sort.DESCENDING,"rating_giver",Sort.DESCENDING).findAllAsync();
        topratedRecipe.addChangeListener((resepV2s, changeSet) -> {
            Log.d("hakuwatop221", String.valueOf(resepV2s.size()));
            if(changeSet.isCompleteResult()){
                if(!resepV2s.isEmpty()){
                    for (int i=0;i<8;i++){
                        topRated.add(resepV2s.get(i));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

        });
        if(topRated.isEmpty()){
            Log.d("hakuwatop222", String.valueOf(topRated.size()));
            if(!topratedRecipe.isEmpty()){
                for (int i=0;i<8;i++){
                    topRated.add(topratedRecipe.get(i));
                }
                Log.d("hakuwatop223", String.valueOf(topRated.size()));
                adapter.notifyDataSetChanged();
            }
        }

    }

    void getRec(){
        for (String a : listRecipeId){
            RealmResults<ResepV2> recoRecipe = defaultrealm.where(ResepV2.class).equalTo("recipeId",a).findAllAsync();
            recoRecipe.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<ResepV2>>() {
                @Override
                public void onChange(RealmResults<ResepV2> resepV2s, OrderedCollectionChangeSet changeSet) {
                    if(changeSet.isCompleteResult()){
                        listRecommend2.add(resepV2s.first());
                        Log.d("hanuwa","masuk eko");
                        adapter.notifyDataSetChanged();
                    }

                }
            });

        }

    }



    private class SyncRecommendation extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("hakuwanexy", String.valueOf(listRecommend.size()));
            getRec();
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... ids) {
            SyncConfiguration configuration = SyncUser.current()
                    .createConfiguration(Constants.REALM_DEFAULT)
                    .build();
            Realm defaultrealmrec  = Realm.getInstance(configuration);
            RecombeeClient client = new RecombeeClient("pkmcooking","f7TmuRpKNXlVVNLz6Se5CfSjbSTBRVaPRN6eqZvTPSftZUdAvHuWe9luZCjnynzf");
            try {
                RecommendationResponse recommendationResponse = client.send(new RecommendItemsToUser(SyncUser.current().getIdentity(), 5));
                Log.d("hakuwarec","Recommended items:");
                for(Recommendation rec: recommendationResponse) {
                    Log.d("hakuwarecid",rec.getId());
                    listRecipeId.add(rec.getId());
                    ResepV2 a = defaultrealmrec.where(ResepV2.class).equalTo("recipeId",rec.getId()).findFirst();
                    listRecommend.add(a);
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }
            Log.d("hakuwanexy", String.valueOf(listRecommend.size()));

            return null;
        }
    }


}
