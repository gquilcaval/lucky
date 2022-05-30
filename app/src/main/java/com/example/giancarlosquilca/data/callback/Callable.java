package com.example.giancarlosquilca.data.callback;

import com.example.giancarlosquilca.domain.models.PointSale;

import java.util.ArrayList;

/**
 * Created by Giancarlos Quilca on 29/05/2022.
 */
public interface Callable<OperationResult> {
    void call(OperationResult result);
}
