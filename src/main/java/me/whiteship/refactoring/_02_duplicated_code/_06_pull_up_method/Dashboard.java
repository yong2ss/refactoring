package me.whiteship.refactoring._02_duplicated_code._06_pull_up_method;

import me.whiteship.refactoring.config.GithubConfig;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 여러 하위 클래스에 동일한 코드가 있다면, 메소드 올리기
 *      - 중복코드는 당장은 잘 되더라도, 나중에 버그를 만들어 낼 빌미를 제공
 *      - 비슷하지만 일부 값만 다르다면, "함수 매개변수화하기" 리팩토링 적용 후 사용
 *      - 하위 클래스에 있는 코드가 상위가 아닌 하위 클래스 기능에 의존하고 있다면, "필드 올리기"를 적용한 후 적용
 *      - 두 메소드가 비스한 절차를 따른다면, "템플릿 메소드 패턴" 적용 고려
 *      - IDE의 Refactor -> Pull Members Up 사용
 */
public class Dashboard {

    public static void main(String[] args) throws IOException {
        ReviewerDashboard reviewerDashboard = new ReviewerDashboard();
        reviewerDashboard.printReviewers();

        ParticipantDashboard participantDashboard = new ParticipantDashboard();
        participantDashboard.printUsernames(15);
    }

    public static void printUsernames(int eventId) throws IOException {
        // Get github issue to check homework
        GitHub gitHub = GithubConfig.getInstance();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);

        // Get participants
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));

        // Print participants
        participants.forEach(System.out::println);
    }
}
