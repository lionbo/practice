package org.lionbo.practice.search.elasticsearch;

import java.util.List;

public class QueryObject {

    private EQuery query;

    private int size = 10;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public EQuery getQuery() {
        return query;
    }

    public void setQuery(EQuery query) {
        this.query = query;
    }

    public void build(String query, List<String> fields) {
        EQuery equery = new EQuery();
        EFilter efilter = new EFilter();
        EInnerQuery einnerquery = new EInnerQuery();
        EQueryString equerystring = new EQueryString();
        equerystring.setQuery(query);
        equerystring.setFields(fields);
        einnerquery.setQuery_string(equerystring);
        efilter.setQuery(einnerquery);
        equery.setFiltered(efilter);
        this.query = equery;
    }

    @Override
    public String toString() {
        return "QueryObject [query=" + query + "]";
    }

}
