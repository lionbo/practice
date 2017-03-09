package org.lionbo.practice.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.common.util.concurrent.FutureCallback;

class FutureCallbackData implements FutureCallback<Object> {

    public FutureCallbackData(List<Object> list) {
        this.list = list;
    }

    private List<Object> list = new ArrayList<Object>();

    public void onSuccess(Object result) {
        list.add(result);
        System.out.println(result);
    }

    public void onFailure(Throwable t) {

    }

}
