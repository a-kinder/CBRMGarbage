package com.angelakinder.cbrmgarbage;

import java.net.URL;
import java.util.concurrent.Callable;
import java.io.InputStream;

import org.json.*;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.*;

class GarbageClient {
    private static final String BASE_URL = "https://api.twitter.com/1/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

class TwitterRestClientUsage {
    public void getPublicTimeline() throws JSONException {
        RequestParams params = new RequestParams();
        params.put("key", "value");
        params.put("more", "data");

        GarbageClient.post("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {

                try {
                    // Pull out the first event on the public timeline
                    JSONObject firstEvent = (JSONObject)timeline.get(0);
                    String tweetText = firstEvent.getString("text");

                    // Do something with the response
                    System.out.println(tweetText);
                } catch (Exception e) {
                }
            }
        });
    }
}
