package org.lionbo.practice.search.elasticsearch;

public class EQuery {

    private EFilter filtered;

    public EFilter getFiltered() {
        return filtered;
    }

    public void setFiltered(EFilter filtered) {
        this.filtered = filtered;
    }

    @Override
    public String toString() {
        return "EQuery [filtered=" + filtered + "]";
    }

}
