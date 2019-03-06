package com.orgzly.android.git;

import android.net.Uri;

import com.orgzly.R;
import com.orgzly.android.prefs.AppPreferences;
import com.orgzly.android.prefs.RepoPreferences;

public class GitPreferencesFromRepoPrefs implements GitPreferences {
    private RepoPreferences repoPreferences;

    public GitPreferencesFromRepoPrefs(RepoPreferences prefs) {
        repoPreferences = prefs;
    }

    @Override
    public String sshKeyPathString() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_ssh_key_path, "orgzly");
    }

    @Override
    public String getAuthor() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_author, "orgzly");
    }

    @Override
    public String getEmail() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_email, "");
    }

    @Override
    public String repositoryFilepath() { // Tvivlar starkt på att denna funkar... repoPreferences verkar ju i det här läget inte innehålla något mer än värdet på repoId.
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_repository_filepath,
                AppPreferences.repositoryStoragePathForUri(
                        repoPreferences.getContext(), remoteUri()));
    }

    @Override
    public String remoteName() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_remote_name, "origin");
    }

    @Override
    public String branchName() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_branch_name, "master");
    }

    @Override
    public Uri remoteUri() {
        String url = "victor@do1.koloni.info:orgzly.git";
        RepoPreferences prefs = repoPreferences; // Om man kunde få in uri i context...?
        return Uri.parse(url);
    }
}
