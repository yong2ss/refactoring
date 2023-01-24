package me.whiteship.refactoring.config;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

public class GithubConfig {

    private static String token = ""; //개인 github Token 값 설정
    private static GitHub instance;
    private GithubConfig() {
    }

    public static GitHub getInstance() {
        try {
            instance = new GitHubBuilder().withOAuthToken(token).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}
