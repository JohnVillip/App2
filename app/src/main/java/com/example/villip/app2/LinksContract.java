package com.example.villip.app2;

import android.net.Uri;

public class LinksContract {
    public static final String PROVIDER_NAME = "com.example.villip.app1.LinksContentProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/links";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String _ID = "_id";
    public static final String LINK = "link";
    public static final String STATUS = "status";
    public static final String TIME = "time";

}
