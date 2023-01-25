package me.whiteship.refactoring._02_duplicated_code._04_extract_function;

import me.whiteship.refactoring.config.GithubConfig;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 동일한 코드를 여러 메소드에서 사용하는 경우, 함수 추출하기
 *      - "의도"와 "구현" 분리
 *      - 추출한 함수의 이름으로 "무슨 일을 하는지" 표현 할 수 있다.
 *      - 따라서 한 줄 짜리 메서드도 가능할수도 있다
 *      - 거대한 함수 속 주석은 추출한 함수를 찾는 좋은 단서가 될 수 있다.
 *      - IDE의 Refactor -> Extract Method 이요
 */
public class StudyDashboard {

    private void printParticipants(int eventId) throws IOException {
        GHIssue issue = getGhIssue(eventId);
        Set<String> participants = getUsernames(issue);
        print(participants);
    }

    private void printReviewers() throws IOException {
        GHIssue issue = getGhIssue(30);
        Set<String> participants = getUsernames(issue);
        print(participants);
    }

    private void print(Set<String> participants) {
        participants.forEach(System.out::println);
    }

    private Set<String> getUsernames(GHIssue issue) throws IOException {
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));
        return participants;
    }

    private GHIssue getGhIssue(int eventId) throws IOException {
        GitHub gitHub = GithubConfig.getInstance();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);
        return issue;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }

}
