package com.adgvit.papervit;

import java.util.List;

public class data {
    List<subject> subjects;

    public data(List<subject> subjects) {
        this.subjects = subjects;
    }

    public List<subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<subject> subjects) {
        this.subjects = subjects;
    }
}
