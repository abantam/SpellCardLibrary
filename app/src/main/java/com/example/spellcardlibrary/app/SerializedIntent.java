package com.example.spellcardlibrary.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.Serializable;

/**
 * Created by admin on 2015/08/18.
 */
public class SerializedIntent extends Intent implements Serializable {

    public SerializedIntent() {
        super();
    }

    public SerializedIntent(Intent o) {
        super(o);
    }

    public SerializedIntent(String action) {
        super(action);
    }

    public SerializedIntent(String action, Uri uri) {
        super(action, uri);
    }

    public SerializedIntent(Context packageContext, Class<?> cls) {
        super(packageContext, cls);
    }

    public SerializedIntent(String action, Uri uri, Context packageContext, Class<?> cls) {
        super(action, uri, packageContext, cls);
    }
}
