package com.adgvit.papervit;

import java.util.List;

public class data1 {
    List<paperObject> papers;

    public data1(List<paperObject> papers) {
        this.papers = papers;
    }

    public List<paperObject> getPapers() {
        return papers;
    }

    public void setPapers(List<paperObject> papers) {
        this.papers = papers;
    }
}
