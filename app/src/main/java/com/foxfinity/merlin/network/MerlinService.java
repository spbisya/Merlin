package com.foxfinity.merlin.network;


import com.foxfinity.merlin.models.Word;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Project Merlin. Created by gwa on 3/18/17.
 */

public interface MerlinService {

    @GET("words")
    Call<List<Word>> getWordsByMask(@Query("sp") String expression);
}
