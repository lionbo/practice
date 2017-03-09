package org.lionbo.practice.utils;

import java.io.IOException;

import org.apache.commons.lang3.RandomUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

public class VoteUtils {
    public static void main(String[] args) {

        VoteData data = new VoteData();
        VoteDataMeta meta = new VoteDataMeta();
        meta.setCode("643b86c077e39790842b2a26901e3ec2");
        meta.setF_uid(RandomUtils.nextInt(0, 10000) + "");
        meta.setF_id("5428");
        meta.setStime(System.currentTimeMillis() + RandomUtils.nextInt(0, 1000) + "");
        data.getData().add(meta);

        String str;
        try {
            str = JacksonUtil.obj2Str(data);
            str = str.replaceFirst(":", "=").replace("\"data\"", "data");
            System.out.println(str);
            HttpUtils.postJson("http://kps1.kplan.cn:8000/activities/fc_vote", str);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
