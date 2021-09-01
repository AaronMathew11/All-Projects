package com.adgvit.papervit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Papers{

    @SerializedName("success")
    private String success;

    @SerializedName("response")
    private List response;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List getResponse() {
        return response;
    }

    public void setResponse(List response) {
        this.response = response;
    }
}
