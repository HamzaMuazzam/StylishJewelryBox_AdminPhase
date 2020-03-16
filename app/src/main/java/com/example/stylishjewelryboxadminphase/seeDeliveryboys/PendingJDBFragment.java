package com.example.stylishjewelryboxadminphase.seeDeliveryboys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.stylishjewelryboxadminphase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingJDBFragment extends Fragment {
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pending_j_d_b, container, false);

        return view;
    }
}
