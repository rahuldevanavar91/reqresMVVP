package com.android.pharmeasytest.service.model.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rahul on 6/2/18.
 */

public class ReqresResponse {
    private int total;

    @SerializedName("per_page")
    private int perPage;

    private int page;

    private List<Data> data;

    private int status;
    private String errorMessage;

    public int getPerPage() {
        return perPage;
    }

    public int getPage() {
        return page;
    }

    public List<Data> getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
