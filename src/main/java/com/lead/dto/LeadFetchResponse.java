package com.lead.dto;

import java.util.List;

public class LeadFetchResponse {
    private String status;
    private List<Data> data;

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "LeadFetchResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
