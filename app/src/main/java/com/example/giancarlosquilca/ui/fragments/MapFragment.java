package com.example.giancarlosquilca.ui.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giancarlosquilca.MainActivity;
import com.example.giancarlosquilca.R;
import com.example.giancarlosquilca.databinding.FragmentMapBinding;
import com.example.giancarlosquilca.domain.models.PointSale;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mMapView ;

    private FragmentMapBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = binding.mapView;
        initGoogleMap(savedInstanceState);
        changeToolbar();
        goBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private PointSale getDataBundle() {
        Bundle args = getArguments();
        PointSale pointSale = new PointSale();
        pointSale.setDirection(args.getString("direction"));
        return pointSale;
    }

    private void initGoogleMap(Bundle savedInstanceState){
        mMapView.getMapAsync(this);
        mMapView.onCreate(savedInstanceState);
    }

    public void changeToolbar() {
        getActivity().setTitle("Mapas");
        MainActivity.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
    }
    private void goBack() {
        MainActivity.toolbar.setNavigationOnClickListener(view -> {
            Navigation.findNavController(requireView()).popBackStack();
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        Geocoder coder = new Geocoder(requireContext());
        List<Address> address;
        try {
            address = coder.getFromLocationName(getDataBundle().getDirection(),5);
            if (address == null) {
                return;
            }
            Address location=address.get(0);
            LatLng direction = new LatLng(location.getLatitude(), location.getLongitude());

            map.addMarker(new MarkerOptions().position(direction).title(getDataBundle().getDirection()));
            map.moveCamera(CameraUpdateFactory.newLatLng(direction));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mMapView.onLowMemory();
        super.onLowMemory();
    }
}