package com.allhomes.poc.serverInterfaces;

import com.allhomes.poc.javaBeans.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cli on 25/02/2018.
 */
public interface AdsRestfulServerInterface
{
    @GET("searchservice.svc/mapsearch")
    Call<SearchResponse> searchProperties(@Query("mode") String mode, @Query("sub") String sub, @Query("pcodes") String pcodes, @Query("state") String state);


}
