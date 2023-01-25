package me.whiteship.refactoring._02_duplicated_code._05_slide_statements;

import me.whiteship.refactoring.config.GithubConfig;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 코드가 비슷하게 생겼지만 완전히 같지는 않은 경우, 코드 분리하기
 *      - 관련있는 코드끼리 묶여있어야 코드를 더 쉽게 이해가능
 *      - 함수를 상단에 미리 정의하기 보다는, 해당 변수를 사용하는 코드 바로 위에 선언
 *      - 관련있는 코드끼리 묶은 다음, 함수 추출하기로 추가 분리 가능
 *      
 */
public class StudyDashboard {

    private void printParticipants(int eventId) throws IOException {
        // Get github issue to check homework
        // Set<String> reviewers = new HashSet<>(); --> 여기 있었다면 코드 관리가 더 어려웠을 것
        GitHub gitHub = GithubConfig.getInstance();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);

        // Get participants
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));

        // Print participants
        participants.forEach(System.out::println);
    }

    private void printReviewers() throws IOException {
        // Get github issue to check homework
        GitHub gitHub = GithubConfig.getInstance();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        // Get reviewers
        Set<String> reviewers = new HashSet<>();
        issue.getComments().forEach(c -> reviewers.add(c.getUserName()));

        // Print reviewers
        reviewers.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }




}
