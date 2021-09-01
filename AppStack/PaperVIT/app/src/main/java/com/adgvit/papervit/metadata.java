package com.adgvit.papervit;

public class metadata {
    boolean success;
    int status;
    String timestamp;

    public metadata(boolean success, int status, String timestamp) {
        this.success = success;
        this.status = status;
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
