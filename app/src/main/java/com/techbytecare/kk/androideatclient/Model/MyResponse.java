package com.techbytecare.kk.androideatclient.Model;

import java.util.List;

/**
 * Created by kundan on 12/21/2017.
 */

public class MyResponse {

    public long multicast_id;
    public int success;
    public int failure;
    public int canonical_ids;
    public List<Result> results;

    public MyResponse(long multicast_id, int success, int failure, int canonical_ids, List<Result> results) {
        this.multicast_id = multicast_id;
        this.success = success;
        this.failure = failure;
        this.canonical_ids = canonical_ids;
        this.results = results;
    }
}
