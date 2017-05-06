package com.bluesky.video.app;

import java.io.File;

/**
 * Created by duchao on 2017/5/6.
 */

public class Constants {
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
}
