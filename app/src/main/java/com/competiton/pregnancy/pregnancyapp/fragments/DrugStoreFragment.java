package com.competiton.pregnancy.pregnancyapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.competiton.pregnancy.pregnancyapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class DrugStoreFragment extends DialogFragment implements OnMapReadyCallback {
    
    private Spinner spinnerDrugStore;
    private MapView mapViewDrugStore;
    private GoogleMap gmap;
    private String hospital;

    public DrugStoreFragment() {
        // Required empty public constructor
    }

    public static DrugStoreFragment newInstance() {
        DrugStoreFragment fragment = new DrugStoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null && d.getWindow() != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NO_FRAME, R.style.AppTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drug_store, container, false);
        initialize(view);
        mapViewDrugStore.onCreate(savedInstanceState);
        mapViewDrugStore.getMapAsync(this);
        setSpinner();
        return view;
    }

    private void initialize(View view) {
        mapViewDrugStore = view.findViewById(R.id.map_view_hospitals);
        spinnerDrugStore = view.findViewById(R.id.hospital_list);
    }

    private void setSpinner() {
        List<String> drugStoreList = new ArrayList<>();
        drugStoreList.add("Drug Store 1");
        drugStoreList.add("Drug Store 2");
        drugStoreList.add("Drug Store 3");
        drugStoreList.add("Drug Store 4");
        drugStoreList.add("Drug Store 5");

        ArrayAdapter<String> dataAdapterHospital = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, drugStoreList);
        dataAdapterHospital.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerDrugStore.setAdapter(dataAdapterHospital);

        spinnerDrugStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hospital = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        gmap.setIndoorEnabled(true);
        UiSettings uiSettings = gmap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        LatLng ny = new LatLng(21.2514, 81.6296);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        gmap.addMarker(markerOptions).showInfoWindow();
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        mapViewDrugStore.onResume();
    }
    
}
