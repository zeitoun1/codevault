package com.zeitoun.codevault.settings.interfaceadapter;

import com.zeitoun.codevault.settings.usecase.GithubSettingsInteractor;
import com.zeitoun.codevault.shared.GithubSettingsResult;

public class GithubSettingsController {
    private final GithubSettingsInteractor githubSettingsInteractor;

    public GithubSettingsController(GithubSettingsInteractor githubSettingsInteractor) {
        this.githubSettingsInteractor = githubSettingsInteractor;
    }

    public void setGithubSettings(GithubSettingsResult githubSettings) {
        githubSettingsInteractor.execute(githubSettings.getPersonalAccessToken(), githubSettings.getGistScope());
    }
}
