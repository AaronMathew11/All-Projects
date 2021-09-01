package com.adgvit.papervit;


public class root {
    data data;
    metadata metadata;

    public root(com.adgvit.papervit.data data, com.adgvit.papervit.metadata metadata) {
        this.data = data;
        this.metadata = metadata;
    }

    public com.adgvit.papervit.data getData() {
        return data;
    }

    public void setData(com.adgvit.papervit.data data) {
        this.data = data;
    }

    public com.adgvit.papervit.metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(com.adgvit.papervit.metadata metadata) {
        this.metadata = metadata;
    }
}
