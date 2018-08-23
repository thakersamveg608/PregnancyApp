package com.competiton.pregnancy.pregnancyapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.adapter.DrugStoreAdapter;
import com.competiton.pregnancy.pregnancyapp.model.DrugStoreDetail;
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
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private MapView mapViewDrugStore;
    private DrugStoreAdapter drugStoreAdapter;
    private List<DrugStoreDetail> drugStoreDetailList = new ArrayList<>();
    private GoogleMap gmap;
    private String drugStore;

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
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Drug Stores");
        toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.white));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_expand_more));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DrugStoreFragment.this.dismiss();
            }
        });
        setSpinner();
        mapViewDrugStore.onCreate(savedInstanceState);
        mapViewDrugStore.getMapAsync(this);
        DrugStoreDetail drugStoreDetail = new DrugStoreDetail("Palmer's Massage Lotion", "https://rukminim1.flixcart.com/image/832/832/j52rrm80/moisturizer-cream/e/f/e/250-massae-lotion-for-stretch-marks-palmer-s-original-imaevfxx3scbgmeh.jpeg?q=70");
        drugStoreDetailList.add(drugStoreDetail);
        drugStoreDetailList.add(drugStoreDetail);
        drugStoreDetailList.add(drugStoreDetail);
        drugStoreDetailList.add(drugStoreDetail);
        drugStoreDetailList.add(drugStoreDetail);
        drugStoreDetailList.add(drugStoreDetail);
        drugStoreDetailList.add(drugStoreDetail);
        drugStoreDetailList.add(drugStoreDetail);
        drugStoreAdapter = new DrugStoreAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(drugStoreAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        refreshList();
        return view;
    }

    private void initialize(View view) {
        toolbar = view.findViewById(R.id.drug_store_toolbar);
        mapViewDrugStore = view.findViewById(R.id.map_view_drug_store);
        spinnerDrugStore = view.findViewById(R.id.drug_store_list);
        recyclerView = view.findViewById(R.id.recycler_view_drug_store);
        progressBar = view.findViewById(R.id.progressBarDrugStore);
    }

    private void setSpinner() {
        List<String> drugStoreList = new ArrayList<>();
        drugStoreList.add("Jain Medicals");
        drugStoreList.add("Ganesh Surgicals");
        drugStoreList.add("Gupta Medicals");
        drugStoreList.add("Ambika Homeo Pharmacy");
        drugStoreList.add("Om Medicos");
        drugStoreList.add("Balaji Medicos");

        ArrayAdapter<String> dataAdapterDrugStore = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, drugStoreList);
        dataAdapterDrugStore.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerDrugStore.setAdapter(dataAdapterDrugStore);

        spinnerDrugStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                drugStore = parent.getItemAtPosition(position).toString().trim();
                refreshList();
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

    private void refreshList() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setVisibility(View.VISIBLE);
                drugStoreAdapter.setDrugStoreDetailList(drugStoreDetailList);
                drugStoreAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        }, 1000);
    }
    
}
