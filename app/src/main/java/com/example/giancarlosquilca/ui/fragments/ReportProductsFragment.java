package com.example.giancarlosquilca.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.giancarlosquilca.MainActivity;
import com.example.giancarlosquilca.R;
import com.example.giancarlosquilca.domain.models.PointSale;
import com.example.giancarlosquilca.ui.adapters.ReportProductAdapter;
import com.example.giancarlosquilca.data.db.DbPointSale;
import com.example.giancarlosquilca.databinding.FragmentReportProductsBinding;
import com.example.giancarlosquilca.domain.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class ReportProductsFragment extends Fragment {

    private ReportProductAdapter reportProductAdapter = null;

    private FragmentReportProductsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReportProductsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeToolbar();
        uiRecyclerview();
        setHasOptionsMenu(true);
        goBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_app_bar, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.btnSave) {
            goMain();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeToolbar() {
        getActivity().setTitle(getPointSaleBundle().getName());
        MainActivity.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
    }

    public void uiRecyclerview() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        reportProductAdapter = new ReportProductAdapter(new ArrayList<>());
        binding.recyclerview.setAdapter(reportProductAdapter);
        reportProductAdapter.update(fetchProductsByPointSale(getPointSaleBundle().getId()));
    }

    public List<Product> fetchProductsByPointSale(Integer idPointSale) {
        final DbPointSale dbUser = new DbPointSale(requireContext());
        List<Product> listUsers = dbUser.getAllProductsByPointSale(idPointSale);
        return listUsers;
    }

    private PointSale getPointSaleBundle() {
        PointSale p = new PointSale();
        Bundle args = getArguments();
        p.setId(args.getInt("id"));
        p.setName(args.getString("name"));
        return p;
    }

    private void goBack() {
        MainActivity.toolbar.setNavigationOnClickListener(view -> {
            Navigation.findNavController(requireView()).popBackStack();
        });
    }

    private void goMain(){
        Navigation.findNavController(requireView()).popBackStack(R.id.pointOfSaleFragment, false);
    }

}