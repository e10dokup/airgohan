package com.airgohan.airgohan.model;

import android.content.Context;
import android.util.Log;

import com.airgohan.airgohan.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.ErrorListener;

public class Event {
    private static final String TAG = Event.class.getSimpleName();
    private final Event self = this;

    private int mId;
    private int mHostId;
    private String mName;
    private String mAddress;
    private String mGenre;
    private String mMainMenu;
    private Date mStartDate;
    private Date mFinishDate;
    private int mMaxMember;

    public Event(){}

    public Event(int id, int hostId, String name, String address, String genre, String mainMenu, Date startDate, Date finishDate, int maxMember) {
        mId = id;
        mHostId = hostId;
        mName = name;
        mAddress = address;
        mGenre = genre;
        mMainMenu = mainMenu;
        mStartDate = startDate;
        mFinishDate = finishDate;
        mMaxMember = maxMember;
    }

    public Event(JSONObject json) throws JSONException{
        mId = json.getJSONObject("レコード番号").getInt("value");
        mHostId = json.getJSONObject("host_id").getInt("value");
        mName = json.getJSONObject("name").getString("value");
        mAddress = json.getJSONObject("address").getString("value");
        mGenre = json.getJSONObject("genre").getString("value");
        mMainMenu = json.getJSONObject("main_menu").getString("value");
        mMaxMember = json.getJSONObject("max_member").getInt("value");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        try{
            mStartDate = sdf.parse(json.getJSONObject("start").getString("value"));
            mFinishDate = sdf.parse(json.getJSONObject("finish").getString("value"));
        }catch(ParseException e){
            Log.d("Event", e.toString());
        }
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getHostId() {
        return mHostId;
    }

    public void setHostId(int hostId) {
        mHostId = hostId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getMainMenu() {
        return mMainMenu;
    }

    public void setMainMenu(String mainMenu) {
        mMainMenu = mainMenu;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public Date getFinishDate() {
        return mFinishDate;
    }

    public void setFinishDate(Date finishDate) {
        mFinishDate = finishDate;
    }

    public int getMaxMember() {
        return mMaxMember;
    }

    public void setMaxMember(int maxMember) {
        mMaxMember = maxMember;
    }

    public void getEvents(final Context context, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        // Volley でリクエスト
        String url = context.getString(R.string.str_api_events);

        JsonObjectRequest indexJson = new JsonObjectRequest(url, successListener, errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                // Add Http Header
                Map<String, String> newHeaders = new HashMap<String, String>();
                newHeaders.putAll(headers);
                newHeaders.put("X-Cybozu-API-Token", context.getString(R.string.str_key_events));
                return newHeaders;
            }
        };

        requestQueue.add(indexJson);
        requestQueue.start();

    }

    public void getHostEvents(final Context context, int userId, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        // Volley でリクエスト
        String url = context.getString(R.string.str_api_events) + "&query=host_id=" + String.valueOf(userId);

        JsonObjectRequest indexJson = new JsonObjectRequest(url, successListener, errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                // Add Http Header
                Map<String, String> newHeaders = new HashMap<String, String>();
                newHeaders.putAll(headers);
                newHeaders.put("X-Cybozu-API-Token", context.getString(R.string.str_key_events));
                return newHeaders;
            }
        };

        requestQueue.add(indexJson);
        requestQueue.start();

    }

    public void putEvents(final Context context, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        // Volley でリクエスト
        String url = context.getString(R.string.str_api_event);

        JSONObject json = new JSONObject();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
            json.put("app", "17");
            json.put("id", mId);
            JSONObject record = new JSONObject();
            record.put("name", new JSONObject().put("value", mName));
            record.put("host_id", new JSONObject().put("value", mHostId));
            record.put("genre", new JSONObject().put("value", mGenre));
            record.put("main_menu", new JSONObject().put("value", mMainMenu));
            record.put("address", new JSONObject().put("value", mAddress));
            record.put("start", new JSONObject().put("value", sdf.format(mStartDate)));
            record.put("finish", new JSONObject().put("value", sdf.format(mFinishDate)));
            record.put("max_member", new JSONObject().put("value", mMaxMember));
            json.put("record", record);

            Log.d(TAG, json.toString());

            JsonObjectRequest putJson = new JsonObjectRequest(Request.Method.PUT, url, json, successListener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = super.getHeaders();
                    // Add Http Header
                    Map<String, String> newHeaders = new HashMap<String, String>();
                    newHeaders.putAll(headers);
                    newHeaders.put("X-Cybozu-API-Token", context.getString(R.string.str_key_events));
                    newHeaders.put("Content-Type", "application/json");
                    return newHeaders;
                }
            };

            requestQueue.add(putJson);
            requestQueue.start();
        } catch (JSONException e){
            Log.d(TAG, e.toString());

        }
    }

    public void postEvents(final Context context, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        // Volley でリクエスト
        String url = context.getString(R.string.str_api_event);

        JSONObject json = new JSONObject();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
            json.put("app", "17");
            JSONObject record = new JSONObject();
            record.put("name", new JSONObject().put("value", mName));
            record.put("host_id", new JSONObject().put("value", mHostId));
            record.put("genre", new JSONObject().put("value", mGenre));
            record.put("main_menu", new JSONObject().put("value", mMainMenu));
            record.put("address", new JSONObject().put("value", mAddress));
            record.put("start", new JSONObject().put("value", sdf.format(mStartDate)));
            record.put("finish", new JSONObject().put("value", sdf.format(mFinishDate)));
            record.put("max_member", new JSONObject().put("value", mMaxMember));
            json.put("record", record);

            Log.d(TAG, json.toString());

            JsonObjectRequest putJson = new JsonObjectRequest(Request.Method.POST, url, json, successListener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = super.getHeaders();
                    // Add Http Header
                    Map<String, String> newHeaders = new HashMap<String, String>();
                    newHeaders.putAll(headers);
                    newHeaders.put("X-Cybozu-API-Token", context.getString(R.string.str_key_events));
                    newHeaders.put("Content-Type", "application/json");
                    return newHeaders;
                }
            };

            requestQueue.add(putJson);
            requestQueue.start();
        } catch (JSONException e) {
            Log.d(TAG, e.toString());

        }
    }

    public void getIdEvents(final Context context, int id, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        // Volley でリクエスト
        String url = context.getString(R.string.str_api_events) + "&レコード番号=" + String.valueOf(id);

        JsonObjectRequest indexJson = new JsonObjectRequest(url, successListener, errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                // Add Http Header
                Map<String, String> newHeaders = new HashMap<String, String>();
                newHeaders.putAll(headers);
                newHeaders.put("X-Cybozu-API-Token", context.getString(R.string.str_key_events));
                return newHeaders;
            }
        };

        requestQueue.add(indexJson);

    }
}