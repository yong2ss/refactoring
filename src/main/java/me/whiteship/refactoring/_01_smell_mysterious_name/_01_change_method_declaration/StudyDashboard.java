package me.whiteship.refactoring._01_smell_mysterious_name._01_change_method_declaration;

import me.whiteship.refactoring.config.GithubConfig;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    함수 선언 변경하기
        - 메소드 이름 변경
            - 주석을 먼저 작성해보고 그 내용에 맞는 메소드명으로 변경
            - 좋은 이름을 가진 함수는 어떻게 구현되었는지 코드를 보지 않아도 이름만 보고도 이해 가능
        - 매개변수 추가
            - 매개변수는 함수 내부의 문맥을 결정, 의존성을 결정
        - 매개변수 제거
        - 시그니처 변경
 */
public class StudyDashboard {

    private Set<String> usernames = new HashSet<>();

    private Set<String> reviews = new HashSet<>();

    /**
     * 스터디 리뷰 이슈에 작성되어 있는 리뷰어 목록과 리뷰를 읽어온다.
     * @throws IOException
     */
    private void loadReviews() throws IOException {
        GitHub gitHub = GithubConfig.getInstance();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        List<GHIssueComment> comments = issue.getComments();
        for (GHIssueComment comment : comments) {
            usernames.add(comment.getUserName());
            reviews.add(comment.getBody());
        }
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public Set<String> getReviews() {
        return reviews;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.loadReviews();
        studyDashboard.getUsernames().forEach(System.out::println);
        studyDashboard.getReviews().forEach(System.out::println);
    }
}
