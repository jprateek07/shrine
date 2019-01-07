package com.google.codelabs.mdc.java.shrine;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;

public class ProductGridFragment extends Fragment {

    BottomAppBar bottomAppBar;
    View view;
    private final String android_version_names[] = {
            "Neurologist",
            "Cardiologist",
            "Nephrologist",
            "Gynecologist",
            "Ophthalmologist",
            "otorhinolaryngology",
            "Pediatrcians",
            "Radiologist",
            "Dematoligist",
        "See More"
    };

    private final String android_image_urls[] = {
            "http://www.johalhospital.com/images/catimgs/cardiology.png"
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false);


        setUpToolbar(view);

        bottomAppBar = view.findViewById(R.id.bottom_appbar);

        bottomAppBar.replaceMenu(R.menu.bottom_navigation_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.product_grid).setBackground(getContext().getDrawable(R.drawable.shr_product_grid_background_shape));
        }

        initViews();
        initViewsHospital();
        return view;
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.gridCategory);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),5);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<DepartmentCategory> DepartmentCategorys = prepareData();
        DataAdapter adapter = new DataAdapter(getActivity(),DepartmentCategorys);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<DepartmentCategory> prepareData(){

        ArrayList<DepartmentCategory> android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            DepartmentCategory DepartmentCategory = new DepartmentCategory(android_version_names[i],android_image_urls[0]);
            android_version.add(DepartmentCategory);
        }
        return android_version;
    }

    private void initViewsHospital(){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.hospitallist);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<HospitalData> DepartmentCategorys = prepareData1();
        HomeHosApater adapter = new HomeHosApater(getActivity(),DepartmentCategorys);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<HospitalData> prepareData1(){

        ArrayList<HospitalData> android_version = new ArrayList<>();
        for(int i=0;i<4;i++){
            HospitalData DepartmentCategory = new HospitalData(DataStorage.Hospitalname[i],DataStorage.Hospitaldistance[i],DataStorage.Hospitalrating[i],DataStorage.Hospitalimg[0]);
            android_version.add(DepartmentCategory);
        }
        return android_version;
    }
    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                getContext(),
                view.findViewById(R.id.product_grid),
                new AccelerateDecelerateInterpolator(),
                getContext().getResources().getDrawable(R.drawable.shr_branded_menu), // Menu open icon
                getContext().getResources().getDrawable(R.drawable.shr_close_menu))); // Menu close icon
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

}
