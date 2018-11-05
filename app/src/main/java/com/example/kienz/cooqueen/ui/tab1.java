package com.example.kienz.cooqueen.ui;

import android.content.Context;
import android.net.Uri;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Recipe;
import model.Recommender;


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
//                for (Recipe h : mRecipes) {
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


    private RecipeFragHomeAdapter adapter;
    public ArrayList<Recommender> mRecipes = new ArrayList<>();
    @BindView (R.id.recyclerView_fraghome) RecyclerView recy;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recy.setLayoutManager(layoutManager);

        ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
        Recipe recipe = new Recipe("Burger","https://images.unsplash.com/photo-1504185945330-7a3ca1380535?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9f2d35c4ea30a81e428e66c653748f91&auto=format&fit=crop&w=621&q=80","google.com","");
        recipeArrayList.add(recipe);
        recipeArrayList.add(recipe);
        recipeArrayList.add(recipe);
        recipeArrayList.add(recipe);
        recipeArrayList.add(recipe);
        recipeArrayList.add(recipe);
        recipeArrayList.add(recipe);
        recipeArrayList.add(recipe);
        recipeArrayList.add(recipe);
        Recommender A = new Recommender("Recommended for you",recipeArrayList);
        mRecipes.add(A);
        mRecipes.add(A);
        mRecipes.add(A);
        mRecipes.add(A);
        mRecipes.add(A);
        mRecipes.add(A);

        for (Recommender h : mRecipes) {
            Log.d("namonn",h.getTitle());
        }

        adapter = new RecipeFragHomeAdapter(getActivity(),mRecipes);
        recy.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
