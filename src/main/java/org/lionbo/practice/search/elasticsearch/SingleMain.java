package org.lionbo.practice.search.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.SearchResult.Hit;

public class SingleMain {

    final static String INDEX_NAME = "kefu2";
    final static String QUERY_TYPE = "weixin";
    final static String HOST = "http://localhost:9200";
    final static String[] QUESTION_FILE = new String[] { "ivr.csv", "weixin.csv" };
    final static String ASK_FILE = "result";
    final static String[] SYS_ERRORS = new String[] {};
    //关键词目前没有合适答案的
    final static String[] SYS_IGNORE = new String[] {};
    //我们的结果更好的
    final static String[] OUR_BETTER = new String[] {};
    final static Map<String, List<Hit<QuestionObject, Double>>> hitcache = new HashMap<String, List<Hit<QuestionObject, Double>>>();

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();

        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(HOST).multiThreaded(true).build());
        JestClient client = factory.getObject();
        search(map, client);
    }

    private static void search(Map<String, String> map, JestClient client) {
        Double usefull = 0.1;
        //        for (Double usefull = 0.1; usefull < 0.8; usefull = usefull + 0.05) {
        int success = 0;
        int fail = 0;
        int count = 0;
        int suggest = 0;
        int errorcount = 0;
        int ignore = 0;
        int better = 0;
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
                }
            }
            if (!match) {
                fail++;
                System.out.println("--------WROING---------");
                System.out.println(entry.getKey());
                //                    System.out.println("my query:" + qsb.toString().trim());  
                System.out.println("answer:" + entry.getValue());
                System.out.println("my answer:");
                for (String str : myAnswerList) {
                    System.out.println(str);
                }

                System.out.println();
                //                if (fail > 10) {
                //                }
                if (fail > 40) {
                    return;
                }
            }

            count++;

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
            return pickupAnswer(hitList, query);

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

    private static List<String> pickupAnswer(List<Hit<QuestionObject, Double>> hitList, String query) {

        //        List<Word> words = WordParser.parseWithoutStopWords(query);

        List<String> finalAnswer = new ArrayList<String>();
        if (hitList != null && hitList.size() > 0) {
            for (Hit<QuestionObject, Double> hit : hitList) {
                Double score = hit.explanation;
                String answer = hit.source.getAnswer();
                String question = hit.source.getQuestion();
                String questionType = hit.source.getQuestionType();
                finalAnswer.add(question + "\t\t\t" + answer);
            }
        }
        if (finalAnswer.size() == 0) {
            //            System.out.println("no answer:" + query);
        }

        return finalAnswer;
    }

}
