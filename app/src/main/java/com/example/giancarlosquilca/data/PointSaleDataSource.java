package com.example.giancarlosquilca.data;

import androidx.annotation.NonNull;

import com.example.giancarlosquilca.data.callback.Callable;
import com.example.giancarlosquilca.data.db.DbPointSale;
import com.example.giancarlosquilca.domain.models.PointSale;

/**
 * Created by Giancarlos Quilca on 29/05/2022.
 */
public class PointSaleDataSource implements PointSaleRepository{

    private final AppExecutors appExecutors;
    private final DbPointSale dbPointSale;

    public PointSaleDataSource(@NonNull AppExecutors appExecutors, @NonNull DbPointSale dbPointSale) {
        this.appExecutors = appExecutors;
        this.dbPointSale = dbPointSale;
    }

    @Override
    public void fetchPointSales(Callable<OperationResult<PointSale>> callable) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final OperationResult<PointSale> result = dbPointSale.getAll();
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            callable.call(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }
}
