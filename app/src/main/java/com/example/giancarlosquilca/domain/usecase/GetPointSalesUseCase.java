package com.example.giancarlosquilca.domain.usecase;

import androidx.annotation.NonNull;

import com.example.giancarlosquilca.data.OperationResult;
import com.example.giancarlosquilca.data.PointSaleRepository;
import com.example.giancarlosquilca.data.callback.Callable;
import com.example.giancarlosquilca.data.db.DbPointSale;
import com.example.giancarlosquilca.domain.models.PointSale;

import java.util.prefs.Preferences;

/**
 * Created by Giancarlos Quilca on 29/05/2022.
 */
public class GetPointSalesUseCase {
    private final PointSaleRepository dbPointSale;

    public GetPointSalesUseCase(@NonNull PointSaleRepository repository) {
        this.dbPointSale = repository;
    }

    public void execute(Callable<OperationResult<PointSale>> callable) {
        dbPointSale.fetchPointSales(callable);
    }
}
