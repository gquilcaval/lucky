package com.example.giancarlosquilca.ui.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giancarlosquilca.MainActivity;
import com.example.giancarlosquilca.R;
import com.example.giancarlosquilca.databinding.FragmentVisitBinding;
import com.example.giancarlosquilca.domain.models.PointSale;

/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class VisitFragment extends Fragment {


    private FragmentVisitBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVisitBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ui();
        changeToolbar();
        goBack();

        binding.btnVisitar.setOnClickListener(v -> {
            goReportProductFragment(getDataBundle());
        });

        binding.imgPhoto.setOnClickListener(view1 -> {
            openCamera();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void ui() {
        binding.tvNamePoint.setText(getDataBundle().getName());
        binding.tvDirection.setText(getDataBundle().getDirection());
    }

    public void changeToolbar() {
        getActivity().setTitle("Visita");
        MainActivity.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
    }

    private PointSale getDataBundle() {
        Bundle args = getArguments();
        PointSale pointSale = new PointSale();
        pointSale.setId(args.getInt("id"));
        pointSale.setName(args.getString("name"));
        pointSale.setDirection(args.getString("direction"));
        return pointSale;
    }

    private void openCamera() {
        Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(open_camera, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        binding.imgPhoto.setImageBitmap(photo);
    }

    private void goBack() {
        MainActivity.toolbar.setNavigationOnClickListener(view -> {
            Navigation.findNavController(requireView()).popBackStack();
        });
    }

    private void goReportProductFragment(PointSale pointSale) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", pointSale.getId());
        bundle.putString("name", pointSale.getName());
        Navigation.findNavController(requireView()).navigate(R.id.reportProductsFragment, bundle);
    }
}