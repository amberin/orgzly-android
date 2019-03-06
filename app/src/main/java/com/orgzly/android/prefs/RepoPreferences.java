package com.orgzly.android.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.orgzly.android.data.DataRepository;

public class RepoPreferences {
    private Context context;
    private long repoId;

    public static RepoPreferences fromUri(Context c, Uri uri) {
        return fromString(c, uri.toString());
    }

    private static RepoPreferences fromString(Context c, String string) {
        long rid = 0; // TODO: dataRepository.getRepoByUrl(string).getId();
        return new RepoPreferences(c, rid);
    }

    public static RepoPreferences fromRepoId(Context c, Long rid) {
        return new RepoPreferences(c, rid);
    }

    public RepoPreferences(Context c, long rid) {
        repoId = rid;
        context = c;
    }

    private String getRepoPreferencesFilename() {
        return String.format("repo.%d", repoId); // Anropas även vid sync.
    }

    public SharedPreferences getRepoPreferences() {
        return context.getSharedPreferences(getRepoPreferencesFilename(), Context.MODE_PRIVATE); // Detta verkar funka som det ska.
    }

    private SharedPreferences getAppPreferences() {
        return AppPreferences.getStateSharedPreferences(context);
    }

    private String getSelector(int selector) {
        return context.getResources().getString(selector);
    }

    public String getStringValue(int selector, String def) {
        return getStringValue(getSelector(selector), def); // används framgångsrikt av activity
    }

    public String getStringValue(String key, String def) {
        return getRepoPreferences().getString(key, def);
    }

    public String getStringValueWithGlobalDefault(String key, String def) {
        return getStringValue(key, getAppPreferences().getString(key, def)); // Jag tror faktiskt att alla dessa funkar. Annars skulle inget någonsin kunna läsas från XML-filen.
    }

    public String getStringValueWithGlobalDefault(int selector, String def) {
        return getStringValueWithGlobalDefault(getSelector(selector), def);
    }

    public long getRepoId() {
        return repoId;
    }

    public Context getContext() {
        return context;
    }
}
