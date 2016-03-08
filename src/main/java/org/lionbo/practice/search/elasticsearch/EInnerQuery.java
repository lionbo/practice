package org.lionbo.practice.search.elasticsearch;

public class EInnerQuery {

    private EQueryString query_string;

    public EQueryString getQuery_string() {
        return query_string;
    }

    public void setQuery_string(EQueryString query_string) {
        this.query_string = query_string;
    }

    @Override
    public String toString() {
        return "EInnerQuery [query_string=" + query_string + "]";
    }

}
