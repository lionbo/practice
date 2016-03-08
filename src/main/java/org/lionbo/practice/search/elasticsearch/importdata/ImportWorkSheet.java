package org.lionbo.practice.search.elasticsearch.importdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lionbo.practice.utils.Constants;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.Index;
import io.searchbox.indices.DeleteIndex;

public class ImportWorkSheet {

    final static String INDEX_NAME = "kefu";
    final static String QUERY_TYPE = "worksheet";
    final static String HOST = "http://10.10.40.35:9200";
    final static String[] QUESTION_FILE = new String[] { "worksheet.csv" };

    public static void main(String[] args) {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(HOST).multiThreaded(true).build());
        JestClient client = factory.getObject();
        //        importData(client, buildData());
        //        buildData();
        delete(client);
    }

    private static Map<String, List<WorkSheet>> buildData() {
        Map<String, List<WorkSheet>> res = new HashMap<String, List<WorkSheet>>();
        for (String file : QUESTION_FILE) {
            String type = file.split("\\" + Constants.PERIOD)[Constants.FIRST];
            List<WorkSheet> qol = new ArrayList<WorkSheet>();
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(ImportWorkSheet.class.getClassLoader().getResourceAsStream(file)));
                try {
                    String str = reader.readLine();
                    int i = 0;
                    while (str != null) {
                        String[] properties = str.split(Constants.COMMA);
                        if (properties.length > 23) {
                            String worksheetId = properties[Constants.FIRST];
                            String worksheetStatus = properties[Constants.SECOND];
                            String firstCategory = properties[Constants.THIRD];
                            String secondCategroy = properties[Constants.FOUR];

                            String thirdCategroy = properties[4];
                            String callPhone = properties[5];
                            String registerPhone = properties[6];
                            String handleDepart = properties[7];
                            String busType = properties[8];
                            String province = properties[9];
                            String priority = properties[10];
                            String workSheetType = properties[11];
                            String occupy = properties[12];
                            String client = properties[13];
                            String channel = properties[14];
                            String orderid = properties[15];
                            String creator = properties[16];
                            String content = properties[17];
                            String createTime = properties[18];
                            String updateTime = properties[19];
                            String takeTime = properties[20];
                            String closeTime = properties[21];
                            String complainId = properties[22];
                            String complainedName = properties[23];
                            WorkSheet workSheet = new WorkSheet(worksheetId, worksheetStatus, firstCategory,
                                    secondCategroy, thirdCategroy, callPhone, registerPhone, handleDepart, busType,
                                    province, priority, workSheetType, occupy, client, channel, orderid, creator,
                                    content, createTime, updateTime, takeTime, closeTime, complainId, complainedName);
                            qol.add(workSheet);
                        } else {
                            i++;
                            if (properties.length == 1)
                                System.out.println(str);
                        }
                        str = reader.readLine();
                    }
                    System.out.println(i);
                } finally {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            res.put(type, qol);
        }
        return res;
    }

    private static void importData(final JestClient client, Map<String, List<WorkSheet>> questions) {
        Builder bulkIndexBuilder = new Bulk.Builder();
        for (Entry<String, List<WorkSheet>> entry : questions.entrySet()) {

            for (WorkSheet question : entry.getValue()) {
                bulkIndexBuilder.addAction(new Index.Builder(question).index(INDEX_NAME).type(entry.getKey()).build());
            }

        }
        try {
            client.execute(bulkIndexBuilder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void delete(final JestClient client) {
        try {
            client.execute(new DeleteIndex.Builder(INDEX_NAME).type("worksheet").build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
