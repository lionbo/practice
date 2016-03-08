package org.lionbo.practice.search.elasticsearch;

import java.util.List;

public class EQueryString {

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    private String query;

    private String analyzer = "ik_syno";

    private List<String> fields;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(String analyzer) {
        this.analyzer = analyzer;
    }

    @Override
    public String toString() {
        return "EQueryString [query=" + query + ", fields=" + fields + "]";
    }

}
