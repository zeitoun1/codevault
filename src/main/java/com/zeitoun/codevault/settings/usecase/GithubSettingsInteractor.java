package com.zeitoun.codevault.settings.usecase;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class GithubSettingsInteractor {
    private final String propertiesFile = System.getProperty("user.home") + File.separator + "codevault" + File.separator + ".properties";
    private final Properties gitHubProperties = new Properties();
    private final GithubSettingsOutputBoundary githubSettingsOutputBoundary;

    public GithubSettingsInteractor(GithubSettingsOutputBoundary githubSettingsOutputBoundary) {
        this.githubSettingsOutputBoundary = githubSettingsOutputBoundary;
    }

    public void execute(String personalAccessToken, String gistScope) {
        // add properties
        gitHubProperties.setProperty("personalAccessToken", personalAccessToken);
        gitHubProperties.setProperty("gistScope", gistScope);

        // store properties in the file
        try (OutputStream out = Files.newOutputStream(Path.of(propertiesFile))){
            gitHubProperties.store(out, null);
        } catch (IOException e) {
            githubSettingsOutputBoundary.showErrorMessage("something went wrong trying to save your personal access token.");
            throw new RuntimeException(e);
        }
    }
}
