package com.example.kienz.cooqueen.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.adapter.RecipeFragSearchAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.ResepV2;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab2 extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static tab2 newInstance(String param1, String param2) {
        tab2 fragment = new tab2();
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
        View v = inflater.inflate(R.layout.fragment_tab2, container, false);
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


    @BindView(R.id.search_tab2)
    SearchView searchView;
    @BindView(R.id.recyclerView_fragsearch)
    RecyclerView recycler_fragsearch;
    private RecipeFragSearchAdapter mAdapter;
    public ArrayList<ResepV2> mRecipes = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        searchView.setFocusable(false);
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnCloseListener(new SearchView.OnCloseListener(){
            @Override
            public boolean onClose() {
                searchView.setQuery("",false);
                searchView.clearFocus();
                return true;
            }
        });
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchView.setIconified(false);
//            }
//        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent searchIntent = new Intent(getContext(),SearchRecipe.class);
                searchIntent.putExtra("resep",s);
                startActivity(searchIntent);
                searchView.clearFocus();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL
                , false);
        GridLayoutManager gridManager = new GridLayoutManager(getActivity(),2);

        recycler_fragsearch.setLayoutManager(gridManager);
        mRecipes.add(new ResepV2("Ayam","https://keeprecipes.com/sites/keeprecipes/files/imagecache/recipe_large/05-resep_ayam_goreng_bumbu_kuning_-_cara_membuat.jpg","google.com",""));
        mRecipes.add(new ResepV2("Sapi","https://ecs7.tokopedia.net/img/cache/700/product-1/2016/9/26/468603/468603_381e0bfe-ad67-4505-bbe4-5e76eebca5ac.jpg","google.com",""));
        mRecipes.add(new ResepV2("Wortel","http://cdn2.tstatic.net/aceh/foto/bank/images/wortel_20170108_093912.jpg","google.com",""));
        mRecipes.add(new ResepV2("Seafood","https://www.sbs.com.au/food/sites/sbs.com.au.food/files/styles/full/public/thalassina-sta-karvouna-grilled-seafood.jpg?itok=QZn2Za0E&mtime=1416267312","google.com",""));

        for (ResepV2 h : mRecipes) {
            Log.d("namonn",h.getName());
        }
        mAdapter = new RecipeFragSearchAdapter(getActivity(),mRecipes);
        recycler_fragsearch.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
