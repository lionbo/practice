package org.lionbo.practice.utils;

import java.util.ArrayList;
import java.util.List;

public class VoteData {

    private List<VoteDataMeta> data = new ArrayList<VoteDataMeta>();

    public List<VoteDataMeta> getData() {
        return data;
    }

    public void setData(List<VoteDataMeta> data) {
        this.data = data;
    }

}
