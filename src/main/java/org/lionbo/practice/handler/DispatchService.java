package org.lionbo.practice.handler;

import java.util.HashMap;
import java.util.Map;

public class DispatchService {

    static Map<String, AbstarctHandler> map = new HashMap<String, AbstarctHandler>();

    static {
        map.put("lingyun", new HandlerA());
        map.put("rengong", new HandlerB());
    }

    public void dispatch(String type) {
        map.get(type).dohandle();
    }

}
