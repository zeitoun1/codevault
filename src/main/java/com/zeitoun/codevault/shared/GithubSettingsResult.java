package com.zeitoun.codevault.shared;

public class GithubSettingsResult {
    private final String personalAccessToken;
    private final String gistScope;

    public GithubSettingsResult(String personalAccessToken, String gistScope) {
        this.personalAccessToken = personalAccessToken;
        this.gistScope = gistScope;
    }

    public String getPersonalAccessToken() {
        return personalAccessToken;
    }

    public String getGistScope() {
        return gistScope;
    }
}
