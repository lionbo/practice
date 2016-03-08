package org.lionbo.practice.search.elasticsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.searchbox.core.SearchResult;

/**
 * @author liuyongbo
 */
public class SearchResultWithSocre extends SearchResult {

    public SearchResultWithSocre(Gson gson) {
        super(gson);
    }

    public static final String SCORE_KEY = "_score";

    protected <T> List<Hit<T, Double>> getHitsWithSocre(Class<T> sourceType, boolean returnSingle) {
        List<Hit<T, Double>> sourceList = new ArrayList<Hit<T, Double>>();

        if (jsonObject != null) {
            String[] keys = getKeys();
            if (keys != null) { // keys would never be null in a standard search scenario (i.e.: unless search class is overwritten)
                String sourceKey = keys[keys.length - 1];
                JsonElement obj = jsonObject.get(keys[0]);
                for (int i = 1; i < keys.length - 1; i++) {
                    obj = ((JsonObject) obj).get(keys[i]);
                }

                if (obj.isJsonObject()) {
                    sourceList.add(extractHitWithSocre(sourceType, obj, sourceKey));
                } else if (obj.isJsonArray()) {
                    for (JsonElement hitElement : obj.getAsJsonArray()) {
                        sourceList.add(extractHitWithSocre(sourceType, hitElement, sourceKey));
                        if (returnSingle)
                            break;
                    }
                }
            }
        }

        return sourceList;
    }

    protected <T> Hit<T, Double> extractHitWithSocre(Class<T> sourceType, JsonElement hitElement, String sourceKey) {
        Hit<T, Double> hit = null;

        if (hitElement.isJsonObject()) {
            JsonObject hitObject = hitElement.getAsJsonObject();
            JsonObject source = hitObject.getAsJsonObject(sourceKey);

            if (source != null) {
                JsonElement id = hitObject.get("_id");
                JsonElement explanation = hitObject.get(SCORE_KEY);
                Map<String, List<String>> highlight = extractHighlight(hitObject.getAsJsonObject(HIGHLIGHT_KEY));
                List<String> sort = extractSort(hitObject.getAsJsonArray(SORT_KEY));

                if (id != null)
                    source.add(ES_METADATA_ID, id);
                hit = new Hit<T, Double>(sourceType, source, Double.class, explanation, highlight, sort);
            }
        }

        return hit;
    }

}
