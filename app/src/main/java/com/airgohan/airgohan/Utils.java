package com.airgohan.airgohan;

import com.airgohan.airgohan.model.Event;
import com.airgohan.airgohan.model.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();
    private final Utils self = this;

    public static User user;
    public static List<Event> events = new ArrayList<Event>();
    public static Event selectedEvent = new Event();
}