package com.adgvit.papervit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Sub {

    @SerializedName("success")
    private String success;

    @SerializedName("response")
    private List response;

    public List getResponse() {
        return response;
    }

    public void setResponse(List response) {
        this.response = response;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
