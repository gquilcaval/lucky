package com.example.giancarlosquilca.data;

import com.example.giancarlosquilca.data.callback.Callable;
import com.example.giancarlosquilca.domain.models.PointSale;

/**
 * Created by Giancarlos Quilca on 30/05/2022.
 */
public interface PointSaleRepository {
    void fetchPointSales(Callable<OperationResult<PointSale>> callable);
}
