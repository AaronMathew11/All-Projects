package com.adgvit.papervit;

public class PaperMetadata {
    String success;
    String status;
    Error error;
    String timestamp;

    public PaperMetadata(String success, String status, Error error, String timestamp) {
        this.success = success;
        this.status = status;
        this.error = error;
        this.timestamp = timestamp;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
