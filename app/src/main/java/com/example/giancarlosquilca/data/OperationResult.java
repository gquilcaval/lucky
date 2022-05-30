package com.example.giancarlosquilca.data;

import java.util.List;

/**
 * Created by Giancarlos Quilca on 29/05/2022.
 */
public class OperationResult<T> {
    private List<T> success;
    private Exception failure;

    public OperationResult(List<T> success) {
        this.success = success;
    }

    public List<T> getSuccess() {
        return success;
    }

    public Exception getFailure() {
        return failure;
    }


}
