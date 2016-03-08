package org.lionbo.practice.search.elasticsearch;

public class EFilter {

    private EInnerQuery query;

    public EInnerQuery getQuery() {
        return query;
    }

    public void setQuery(EInnerQuery query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "EFilter [query=" + query + "]";
    }

}
