package org.lionbo.practice.search.elasticsearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apdplat.word.segmentation.Word;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.lionbo.practice.search.elasticsearch.splitWord.WordParser;
import org.lionbo.practice.utils.Constants;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.Index;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;

public class Main {

    final static String INDEX_NAME = "kefu2";
    final static String QUERY_TYPE = "weixin";
    final static String HOST = "http://10.10.40.35:9200";
    final static String[] QUESTION_FILE = new String[] { "ivr.csv", "weixin.csv" };
    final static String ASK_FILE = "result";
    final static String KNOW_FILE = "ivr.csv";
    final static String ERROR_FILE = "wrong";
    final static String[] SYS_ERRORS = new String[] {};
    //关键词目前没有合适答案的
    final static String[] SYS_IGNORE = new String[] {};
    //我们的结果更好的
    final static String[] OUR_BETTER = new String[] {};
    //暂时无法解决
    final static String[] CAN_NOT = new String[] {};
    final static Map<String, List<Hit<QuestionObject, Double>>> hitcache = new HashMap<String, List<Hit<QuestionObject, Double>>>();

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map = getAskAndAnswer();
        //        map = getKnowAskAndAnswer();

        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(HOST).multiThreaded(true).build());
        JestClient client = factory.getObject();
        try {
            search(map, client);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //        importData(client, buildData());
        //        delete(client);
    }

    private static void search(Map<String, String> map, JestClient client) throws FileNotFoundException {
        int maxsuccess = 0;
        Double usefull = 0.1;
        //        for (Double usefull = 0.1; usefull < 0.8; usefull = usefull + 0.05) {
        int success = 0;
        int fail = 0;
        int count = 0;
        int suggest = 0;
        int errorcount = 0;
        int ignore = 0;
        int better = 0;
        FileOutputStream fos = new FileOutputStream(ERROR_FILE);
        for (Entry<String, String> entry : map.entrySet()) {
            String queryIn = entry.getKey();
            String query = queryIn.replaceAll("\\?", "");
            String answer = entry.getValue().trim();
            boolean haserror = false;
            for (String error : SYS_ERRORS) {
                if (answer.contains(error)) {
                    haserror = true;
                    errorcount++;
                    break;
                }
            }
            for (String istr : SYS_IGNORE) {
                if (query.contains(istr)) {
                    haserror = true;
                    ignore++;
                    break;
                }
            }
            for (String istr : OUR_BETTER) {
                if (query.contains(istr)) {
                    haserror = true;
                    better++;
                    break;
                }
            }
            for (String istr : CAN_NOT) {
                if (query.contains(istr)) {
                    haserror = true;
                    break;
                }
            }

            if (haserror) {
                count++;
                continue;
            }

            List<String> myAnswerList = doSearch(client, query, "ivr", usefull);
            if (myAnswerList.size() > 1) {
                suggest++;
            }
            if (myAnswerList.size() == 0) {
                ignore++;
                count++;
                continue;
            }

            boolean match = false;
            for (String myAnswer : myAnswerList) {

                if (entry.getValue().contains(myAnswer)) {
                    match = true;
                    success++;
                    break;
                }
            }
            if (!match) {
                fail++;
                writeStringToFile("--------WROING---------\n", fos);
                writeStringToFile(entry.getKey() + "\n", fos);
                //                    System.out.println("my query:" + qsb.toString().trim());  
                writeStringToFile("answer:" + entry.getValue() + "\n", fos);
                //                System.out.println("my answer:" + Arrays.toString(myAnswerList.toArray()));
                //                //                if (fail > 10) {
                //                //                }
                //                if (fail > 20) {
                //                    return;
                //                }
            }

            count++;

        }

        if (maxsuccess < success) {
            maxsuccess = success;
            System.out.println("count:" + (count - errorcount - better) + ",success:" + (success + better) + ",suggest:"
                    + suggest + ",fail:" + fail + ",ignore:" + ignore + ",errorcount:" + errorcount + ",better:"
                    + better + ",usefull:" + usefull);
        }
        try {
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void writeStringToFile(String str, FileOutputStream fos) {
        try {
            fos.write(str.getBytes());
            fos.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static Map<String, String> getAskAndAnswer() {
        Map<String, String> map = new HashMap<String, String>();

        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Main.class.getClassLoader().getResourceAsStream(ASK_FILE)));
            try {
                String str = reader.readLine();
                while (str != null) {
                    String[] properties = str.split(Constants.COLON);
                    if (map.get(properties[0]) == null) {
                        map.put(properties[0], properties[1]);
                    } else {
                        //                        System.out.println(properties[0] + ":" + properties[1]);
                    }

                    str = reader.readLine();
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static Map<String, String> getKnowAskAndAnswer() {
        Map<String, String> map = new HashMap<String, String>();

        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Main.class.getClassLoader().getResourceAsStream(KNOW_FILE)));
            try {
                String str = reader.readLine();
                while (str != null) {
                    String[] properties = str.split(Constants.COMMA);
                    if (map.get(properties[2]) == null) {
                        map.put(properties[2], properties[3]);
                    } else {
                        //                        System.out.println(properties[0] + ":" + properties[1]);
                    }

                    str = reader.readLine();
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static Map<String, List<QuestionObject>> buildData() {
        Map<String, List<QuestionObject>> res = new HashMap<String, List<QuestionObject>>();
        for (String file : QUESTION_FILE) {
            String type = file.split("\\" + Constants.PERIOD)[Constants.FIRST];
            List<QuestionObject> qol = new ArrayList<QuestionObject>();
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(Main.class.getClassLoader().getResourceAsStream(file)));
                try {
                    String str = reader.readLine();
                    while (str != null) {
                        String[] properties = str.split(Constants.COMMA);
                        String channel = properties[Constants.FIRST];
                        String questionType = properties[Constants.SECOND];
                        String question = properties[Constants.THIRD];
                        String answer = properties[Constants.FOUR];
                        QuestionObject questionObj = new QuestionObject();
                        questionObj.setChannel(channel);
                        questionObj.setQuestionType(questionType);
                        questionObj.setQuestion(question);
                        questionObj.setAnswer(answer);
                        qol.add(questionObj);
                        str = reader.readLine();
                    }
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

    private static void importData(final JestClient client, Map<String, List<QuestionObject>> questions) {
        Builder bulkIndexBuilder = new Bulk.Builder();
        for (Entry<String, List<QuestionObject>> entry : questions.entrySet()) {

            for (QuestionObject question : entry.getValue()) {
                bulkIndexBuilder.addAction(new Index.Builder(question).index(INDEX_NAME).type(entry.getKey()).build());
            }

        }
        try {
            client.execute(bulkIndexBuilder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<String> doSearch(final JestClient client, String query, String queryType, Double usefull) {
        try {

            List<Hit<QuestionObject, Double>> hitList;
            if (hitcache.get(query) == null) {
                SearchSourceBuilder ssb = new SearchSourceBuilder();
                ssb.query(QueryBuilders.queryStringQuery(query).field("question").analyzer("ik_syno"));
                ExtendSearch.Builder searchBuilder = new ExtendSearch.Builder(ssb.toString()).addIndex(INDEX_NAME)
                        .addType(queryType);
                SearchResultWithSocre result = client.execute(searchBuilder.build());

                //                if (result.getMaxScore() < usefull) {
                //                    ssb = new SearchSourceBuilder();
                //                    ssb.query(QueryBuilders.queryStringQuery(query).field("answer").analyzer("ik_syno"));
                //                    searchBuilder = new ExtendSearch.Builder(ssb.toString()).addIndex(INDEX_NAME).addType(queryType);
                //                    result = client.execute(searchBuilder.build());
                //                }
                hitList = result.getHitsWithSocre(QuestionObject.class, false);
                hitcache.put(query, hitList);
            } else {
                hitList = hitcache.get(query);
            }
            return pickupAnswer(hitList, query, 10);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    private static List<String> pickupAnswer(List<Hit<QuestionObject, Double>> hitList, String query, int length) {

        //        List<Word> words = WordParser.parseWithoutStopWords(query);

        List<String> finalAnswer = new ArrayList<String>();
        if (hitList != null && hitList.size() > 0) {
            for (int i = 0; i < length; i++) {
                Hit<QuestionObject, Double> hit = hitList.get(i);
                Double score = hit.explanation;
                String answer = hit.source.getAnswer();
                String question = hit.source.getQuestion();
                String questionType = hit.source.getQuestionType();
                finalAnswer.add(answer);
            }
        }
        if (finalAnswer.size() == 0) {
            //            System.out.println("no answer:" + query);
        }

        return finalAnswer;
    }

    private static Double getScore(List<Word> queryWords, String question, String questionType, Double score,
            Double quesWeight, Double typeWeight) {

        List<Word> quesWords = WordParser.parse(question);
        List<Word> quesTypeWords = WordParser.parse(questionType);

        for (Word queryword : queryWords) {

            if (queryword.getText().length() < 2) {
                //                System.out.println("忽略问题中长度为1的词:" + queryword.getText());
                continue;
            }

            for (Word quesWord : quesWords) {
                if (queryword.equals(quesWord)) {
                    score = score * quesWeight;
                }
            }

            for (Word quesTypeWord : quesTypeWords) {
                if (queryword.equals(quesTypeWord)) {
                    score = score * typeWeight;
                }
            }

        }
        return score;

    }

    private static void delete(final JestClient client) {
        try {
            client.execute(new DeleteIndex.Builder(INDEX_NAME).build());
            //            client.execute(new DeleteIndex.Builder(INDEX_NAME).type("weixin2").build());
            //            client.execute(new DeleteIndex.Builder(INDEX_NAME).type("aliases").build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void buildIndex(final JestClient client) {
        boolean indexExists;
        try {
            indexExists = client.execute(new IndicesExists.Builder(INDEX_NAME).build()).isSucceeded();
            if (indexExists) {
                client.execute(new DeleteIndex.Builder(INDEX_NAME).build());
            }
            client.execute(new CreateIndex.Builder(INDEX_NAME).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
