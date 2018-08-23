package com.competiton.pregnancy.pregnancyapp.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.competiton.pregnancy.pregnancyapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HospitalFragment extends DialogFragment implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener {

    private Button selectDate, btnSubmit;
    private TextView userDate;
    private LinearLayout llDate;
    private Spinner spinnerHospital, spinnerTimeSlot;
    private Toolbar toolbar;
    private MapView mapViewHospitals;
    private GoogleMap gmap;
    private String hospital, timeSlot;
    private boolean isHospitalSelected = false, isTimeSlotSelected = false, isDateSelected = false;

    public HospitalFragment() {
        // Required empty public constructor
    }

    public static HospitalFragment newInstance() {
        HospitalFragment fragment = new HospitalFragment();
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
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        initialize(view);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Hospitals");
        toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.white));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_expand_more));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HospitalFragment.this.dismiss();
            }
        });
        mapViewHospitals.onCreate(savedInstanceState);
        mapViewHospitals.getMapAsync(this);
        Calendar calendar = Calendar.getInstance();
        final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentYear = calendar.get(Calendar.YEAR);
        setSpinner();
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK, HospitalFragment.this, currentYear, currentMonth, currentDay);
                dialog.show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmation();
            }
        });

        return view;
    }

    private void initialize(View view) {
        toolbar = view.findViewById(R.id.hospital_toolbar);
        llDate = view.findViewById(R.id.ll_date);
        selectDate = view.findViewById(R.id.btnDate);
        userDate = view.findViewById(R.id.dateUser);
        mapViewHospitals = view.findViewById(R.id.map_view_hospitals);
        spinnerHospital = view.findViewById(R.id.hospital_list);
        spinnerTimeSlot = view.findViewById(R.id.time_slot_list);
        btnSubmit = view.findViewById(R.id.hospital_fragment_submit);
    }

    private void setSpinner() {
        List<String> hospitalList = new ArrayList<>();
        hospitalList.add("Hospital 1");
        hospitalList.add("Hospital 2");
        hospitalList.add("Hospital 3");
        hospitalList.add("Hospital 4");
        hospitalList.add("Hospital 5");

        List<String> timeSlotList = new ArrayList<>();
        timeSlotList.add("9am - 11am");
        timeSlotList.add("11am - 1pm");
        timeSlotList.add("3pm - 5pm");
        timeSlotList.add("6pm - 8pm");

        ArrayAdapter<String> dataAdapterHospital = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, hospitalList);
        dataAdapterHospital.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerHospital.setAdapter(dataAdapterHospital);

        spinnerHospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                isHospitalSelected = true;
                hospital = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                isHospitalSelected = false;
            }
        });

        ArrayAdapter<String> dataAdapterTime = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, timeSlotList);
        dataAdapterTime.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerTimeSlot.setAdapter(dataAdapterTime);

        spinnerTimeSlot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                isTimeSlotSelected = true;
                timeSlot = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                isTimeSlotSelected = false;
            }
        });
    }

    private void confirmation() {
        if (!isDateSelected) {
            Toast.makeText(getContext(), "Please select the Date", Toast.LENGTH_LONG).show();
        } else if (!isHospitalSelected) {
            Toast.makeText(getContext(), "Please select the hospital", Toast.LENGTH_LONG).show();
        } else if (!isTimeSlotSelected) {
            Toast.makeText(getContext(), "Please select the time slot", Toast.LENGTH_LONG).show();
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("You have been alloted an appointment on " + userDate.getText().toString().trim() + " from " + timeSlot + " at " + hospital)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
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
        mapViewHospitals.onResume();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        llDate.setVisibility(View.VISIBLE);
        isDateSelected = true;
        userDate.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
    }

}
