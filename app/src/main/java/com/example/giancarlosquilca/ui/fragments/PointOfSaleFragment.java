package com.example.giancarlosquilca.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.giancarlosquilca.MainActivity;
import com.example.giancarlosquilca.R;
import com.example.giancarlosquilca.data.AppExecutors;
import com.example.giancarlosquilca.data.PointSaleDataSource;
import com.example.giancarlosquilca.domain.usecase.GetPointSalesUseCase;
import com.example.giancarlosquilca.ui.adapters.PointOfSaleAdapter;
import com.example.giancarlosquilca.data.db.DbPointSale;
import com.example.giancarlosquilca.databinding.FragmentPointOfSaleBinding;
import com.example.giancarlosquilca.domain.models.PointSale;
import com.example.giancarlosquilca.ui.viewmodels.PointSaleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class PointOfSaleFragment extends Fragment implements PointOfSaleAdapter.Callback, SearchView.OnQueryTextListener {

    private List<PointSale> listPointSale;
    @NonNull
    private PointSaleViewModel dashboardViewModel;
    private PointOfSaleAdapter pointOfSaleAdapter;

    private FragmentPointOfSaleBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPointOfSaleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewModel();
        fetchPointSales();
        changeToolbar();
        uiRecyclerview();
        showDrawerLayout();
        binding.searchView.clearFocus();
        binding.searchView.setOnQueryTextListener(this);
        binding.floatingActionButton.setOnClickListener(view1 -> {Toast.makeText(requireContext(), "En construcción", Toast.LENGTH_SHORT).show();});
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setUpViewModel() {
        PointSaleViewModel.Factory factory = new PointSaleViewModel.Factory(
                new GetPointSalesUseCase(new PointSaleDataSource(new AppExecutors(), new DbPointSale(requireContext())))
        );
        dashboardViewModel = new ViewModelProvider(this, factory).get(PointSaleViewModel.class);
        dashboardViewModel.getPointSales().observe(getViewLifecycleOwner(), new Observer<List<PointSale>>() {
            @Override
            public void onChanged(List<PointSale> data) {
                if (data != null) {
                    listPointSale = data;
                    pointOfSaleAdapter.update(listPointSale);
                }
            }
        });
    }

    public void changeToolbar() {
        getActivity().setTitle("Punto de venta");
        MainActivity.toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
    }

    public void uiRecyclerview() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        pointOfSaleAdapter = new PointOfSaleAdapter(new ArrayList<>(), this);
        binding.recyclerview.setAdapter(pointOfSaleAdapter);
    }

    private void fetchPointSales() {
        dashboardViewModel.fetchPointSales();
    }

    @Override
    public void imgMapSelect(PointSale pointSale) {
        goMapFragment(pointSale);
    }

    @Override
    public void itemSelect(PointSale pointSale) {
        showDialog(pointSale);
    }

    public void showDrawerLayout() {
        MainActivity.toolbar.setNavigationOnClickListener(view1 -> {
            MainActivity.drawerLayout.open();
        });
    }

    public void showDialog(PointSale pointSale) {
        new AlertDialog.Builder(requireContext())
                .setTitle(pointSale.getName())
                .setMessage("¿Atenderá el pdv?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goVisitFragment(pointSale);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void goVisitFragment(PointSale pointSale) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", pointSale.getId());
        bundle.putString("name", pointSale.getName());
        bundle.putString("direction", pointSale.getDirection());
        Navigation.findNavController(requireView()).navigate(R.id.visitFragment, bundle);
    }

    private void goMapFragment(PointSale pointSale) {
        Bundle bundle = new Bundle();
        bundle.putString("direction", pointSale.getDirection());
        Navigation.findNavController(requireView()).navigate(R.id.mapFragment, bundle);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filterList(s);
        return false;
    }

    private void filterList(String s) {
        List<PointSale> list = new ArrayList<>();
        for(PointSale p: listPointSale) {
            if (p.getName().toLowerCase().contains(s.toLowerCase())){
                list.add(p);
            }
            if (!list.isEmpty()){
                pointOfSaleAdapter.setFilterList(list);
            }
        }
    }
}