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
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;

import java.util.ArrayList;

import static com.google.codelabs.mdc.java.shrine.DataStorage.android_version_names;
import static com.google.codelabs.mdc.java.shrine.DataStorage.doctorrating;

public class ProductGridFragment extends Fragment {
    SpaceNavigationView spaceNavigationView;
    View view;

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
         spaceNavigationView = (SpaceNavigationView) view.findViewById(R.id.bottom_appbar);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.setSpaceItemIconSize(140);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.home));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.drugs));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.blood_bottom));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.bloodreq));
        spaceNavigationView.showIconOnly();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.product_grid).setBackground(getContext().getDrawable(R.drawable.shr_product_grid_background_shape));
        }
        initViews();
        initViewsHospital(0);
        initViewSlider();

        initViewsHospital(1);
        return view;
    }

    private void initViewSlider() {
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.Slider);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        AdpterSlider adapter = new AdpterSlider(getActivity(),DataStorage.sliderImage);
        recyclerView.setAdapter(adapter);

    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.gridCategory);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<DepartmentCategory> DepartmentCategorys = prepareData();
        DataAdapter adapter = new DataAdapter(getActivity(),DepartmentCategorys);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<DepartmentCategory> prepareData(){

        ArrayList<DepartmentCategory> android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            DepartmentCategory DepartmentCategory = new DepartmentCategory(android_version_names[i],DataStorage.android_image_urls[i]);
            android_version.add(DepartmentCategory);
        }
        return android_version;
    }

    private void initViewsHospital(int i){
        RecyclerView recyclerView = null;

        if(i==1) {
        recyclerView= (RecyclerView) view.findViewById(R.id.hospitallist);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            ArrayList<HospitalData> DepartmentCategorys = prepareData1(1);
            HomeHosApater adapter = new HomeHosApater(getActivity(), DepartmentCategorys);
            recyclerView.setAdapter(adapter);

        }else
            {recyclerView= (RecyclerView) view.findViewById(R.id.doctorlist);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);

                ArrayList<HospitalData> DepartmentCategorys = prepareData1(0);
                HomeHosApater adapter = new HomeHosApater(getActivity(), DepartmentCategorys);
                recyclerView.setAdapter(adapter);

        }
    }
    private ArrayList<HospitalData> prepareData1(int flag){
       ArrayList<HospitalData> android_version = new ArrayList<>();

        for(int i=0;i<4;i++){
       if(flag==0){
            HospitalData DepartmentCategory = new HospitalData(DataStorage.doctorname[i],DataStorage.Hospitaldepartment[i],DataStorage.doctorrating[i],DataStorage.doctorimg[i]);
            android_version.add(DepartmentCategory);
        }
        else {
           HospitalData DepartmentCategory = new HospitalData(DataStorage.Hospitalname[i],DataStorage.Hospitaldistance[i],DataStorage.Hospitalrating[i],DataStorage.Hospitalimg[i]);
           android_version.add(DepartmentCategory);

       }}
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
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }
}
