package codingwithmitch.com.forsale.util;

import java.util.Map;

import codingwithmitch.com.forsale.models.HitsObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

/**
 * Created by User on 10/31/2017.
 */

public interface ElasticSearchAPI {

    @GET("_search/")
    Call<HitsObject> search(
            @HeaderMap Map<String, String> headers,
            @Query("default_operator") String operator, //1st query (prepends '?')
            @Query("q") String query //second query (prepends '&')

    );
}
