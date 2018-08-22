package com.competiton.pregnancy.pregnancyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class HospitalFragment extends Fragment {

    private final static int PLACE_PICKER_REQUEST = 999;

    public HospitalFragment() {
        // Required empty public constructor
    }

    public static HospitalFragment newInstance(String param1, String param2) {
        HospitalFragment fragment = new HospitalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_hospital, container, false);
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//        try {
//            Intent intent = builder.build(getActivity());
//            startActivityForResult(intent, PLACE_PICKER_REQUEST);
//        } catch (GooglePlayServicesRepairableException e) {
//            e.printStackTrace();
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }


        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PLACE_PICKER_REQUEST) {
//            if (resultCode == getActivity().RESULT_OK) {
//                Place place = PlacePicker.getPlace(data, getContext());
//                String toastMsg = String.format("Place: %s", place.getName());
//                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
//            }
//        }
//    }
}
