package com.foxfinity.merlin.network;


import com.foxfinity.merlin.models.Word;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.foxfinity.merlin.network.contracts.NetworkContract.ENDPOINT_WORDS;
import static com.foxfinity.merlin.network.contracts.NetworkContract.PARAM_SP;

/**
 * Project Merlin. Created by gwa on 3/18/17.
 */

public interface MerlinService {

    @GET(ENDPOINT_WORDS)
    Call<List<Word>> getWordsByMask(@Query(PARAM_SP) String expression);
}
