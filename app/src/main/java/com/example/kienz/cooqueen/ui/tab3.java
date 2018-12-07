package com.example.kienz.cooqueen.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.adapter.RecipeFragMealplanAdapter;
import com.example.kienz.cooqueen.adapter.RecipeFragMealplanViewholder2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import model.Meal_Plan;
import model.Meal_Plan_Holder;
import model.PlannedResepV3;
import model.ResepV2;
import util.Constants;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tab3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tab3#newInstance} factory method to
 * create an instance of this fragment.
 */

public class tab3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Realm realm;
    private ArrayList<ResepV2> plannedRecipes = new ArrayList<>();
    private RecipeFragMealplanAdapter adapter;
    Meal_Plan_Holder mealPlans;
    public ArrayList<Meal_Plan> mRecipes = new ArrayList<>();
    @BindView(R.id.recyclerView_fragplan)
    RecyclerView recycler;
    @BindView(R.id.hint)
    RelativeLayout hint;
    @BindView(R.id.close_hint)
    ImageButton closeHint;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static tab3 newInstance(String param1, String param2) {
        tab3 fragment = new tab3();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab3, container, false);
        ButterKnife.bind(this,v);

        // Inflate the layout for this fragment
        return v;
    }

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
        closeHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hint.setVisibility(View.GONE);
            }
        });


    }

    private ResepV2 getRecipe(String recipeId) {
        SyncConfiguration configuration = SyncUser.current()
                .createConfiguration(Constants.REALM_DEFAULT)
                .build();
        realm = Realm.getInstance(configuration);
        ResepV2 returned = realm
                .where(ResepV2.class)
                .equalTo("recipeId",recipeId)
                .findAllAsync().first();
        return returned;
    }

    @Override
    public void onResume() {
        getPlannedRecipes();
        super.onResume();
    }

    void getPlannedRecipes(){
        mealPlans = new Meal_Plan_Holder();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(layoutManager);
        adapter = new RecipeFragMealplanAdapter(getActivity(),mealPlans.getMealPlanArrayList());
        recycler.setAdapter(adapter);
        String url = Constants.REALM_USER;
        SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), url).build();
        realm = Realm.getInstance(config);
        ArrayList<PlannedResepV3> plannedIds = new ArrayList<>();
        if(!mealPlans.getMealPlanArrayList().isEmpty()){

        }
        RealmResults<PlannedResepV3> plannedresults = realm.where(PlannedResepV3.class).findAllAsync();
        plannedresults.addChangeListener((plannedResepV2s, changeSet) -> {
            if(changeSet.isCompleteResult()){
                if(!plannedResepV2s.isEmpty()){
                    plannedIds.addAll(plannedresults);
                }
            }
            Log.d("hakuwasize3", String.valueOf(plannedresults.size()));
            for (PlannedResepV3 itemResep : plannedIds){
                if(itemResep.isValid()){
                    ResepV2 detailResep = getRecipe(itemResep.getRecipeID());
                    plannedRecipes.add(detailResep);
                    mealPlans.add(itemResep.getType(),detailResep,itemResep.getPlanId());
                    adapter.notifyDataSetChanged();
                }

            }
            plannedresults.removeAllChangeListeners();
            Log.d("hakuwasize4", String.valueOf(plannedRecipes.size()));
        });
        if (plannedIds==null){
            if(!plannedresults.isEmpty()){
                plannedIds.addAll(plannedresults);
                Log.d("hakuwasize2", String.valueOf(plannedresults.size()));
                for (PlannedResepV3 itemResep : plannedIds){
                    ResepV2 detailResep = getRecipe(itemResep.getRecipeID());
                    plannedRecipes.add(detailResep);
                    mealPlans.add(itemResep.getType(),detailResep,itemResep.getPlanId());
                    adapter.notifyDataSetChanged();
                }
            }
        }
        Log.d("hakuwasize1", String.valueOf(plannedresults.size()));
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Hapus Resep")
                        .setMessage("Anda ingin menghapus resep ini dari jadwal?")
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                int position = viewHolder.getAdapterPosition();
                                String id = adapter.getItem(position).getPlanId();
                                String url = Constants.REALM_USER;
                                SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), url).build();
                                realm = Realm.getInstance(config);
                                realm.executeTransactionAsync(realm -> {
                                    RealmResults<PlannedResepV3> items = realm.where(PlannedResepV3.class)
                                            .equalTo("planId", id)
                                            .findAll();
                                    if (!items.isEmpty()) {
                                        items.deleteAllFromRealm();
                                    }
                                });
                                Log.d("hakuwa",id);
                                mealPlans.delete(position);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Resep telah dihapus", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.notifyDataSetChanged();
                            }
                        }).show()
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                adapter.notifyDataSetChanged();
                            }
                        });

            }

            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                if (viewHolder instanceof RecipeFragMealplanViewholder2){
                    return super.getSwipeDirs(recyclerView, viewHolder);
                }else{
                    return 0;
                }

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recycler);
    }

    @Override
    public void onDestroy() {
        realm.close();
        super.onDestroy();

    }
}
