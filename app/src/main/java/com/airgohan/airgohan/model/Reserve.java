package com.airgohan.airgohan.model;

public class Reserve {
    private static final String TAG = Reserve.class.getSimpleName();
    private final Reserve self = this;

    private int mId;
    private int mUserId;
    private int mHostId;
    private int mEventId;
    private int mEvaluation;
    private String mComment;

    public Reserve(int id, int userId, int hostId, int eventId, int evaluation, String comment) {
        mId = id;
        mUserId = userId;
        mHostId = hostId;
        mEventId = eventId;
        mEvaluation = evaluation;
        mComment = comment;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getHostId() {
        return mHostId;
    }

    public void setHostId(int hostId) {
        mHostId = hostId;
    }

    public int getEventId() {
        return mEventId;
    }

    public void setEventId(int eventId) {
        mEventId = eventId;
    }

    public int getEvaluation() {
        return mEvaluation;
    }

    public void setEvaluation(int evaluation) {
        mEvaluation = evaluation;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }
}