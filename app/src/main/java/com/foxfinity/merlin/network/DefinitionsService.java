package com.foxfinity.merlin.network;

import com.foxfinity.merlin.models.DefinitionAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static com.foxfinity.merlin.network.contracts.NetworkContract.ENDPOINT_DEFINITIONS;
import static com.foxfinity.merlin.network.contracts.NetworkContract.PATH_QUERY;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public interface DefinitionsService {
    @Headers({
            "X-Mashape-Key: 1uNQYFb0qBmshQOfn93vQe5WGbQQp1klMHnjsn45qBoz2hEF06",
            "Accept: application/json"
    })
    @GET(ENDPOINT_DEFINITIONS)
    Call<DefinitionAnswer> getDefinitions(@Path(value = PATH_QUERY) String query);
}
