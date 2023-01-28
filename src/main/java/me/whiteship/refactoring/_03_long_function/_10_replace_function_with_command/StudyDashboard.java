package me.whiteship.refactoring._03_long_function._10_replace_function_with_command;

import me.whiteship.refactoring.config.GithubConfig;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 함수를 명령으로 바꾸기
 * - 함수를 독립적인 객체인, Command로 만들어 사용할 수 있다.
 * - 커맨드 패턴을 적용하면 많은 장점
 *      - 부가적인 기능으로 undo 기능 만들 수 있다.
 *      - 더 복잡한 기능을 구현하는데 필요한 여러 메소드를 추가 가능
 *      - 상속이나 템플릿을 활용할 수 있다
 *      - 복잡한 메소드를 여러 메소드나 필드를 활용해 쪼갤 수 있다.
 * - 대부분의 경우에 "커맨드"보다는 "함수"를 사용하지만, 커맨드 말고 다른 방법이 없는 경우에만 사용
 */
public class StudyDashboard {

    private final int totalNumberOfEvents;

    public StudyDashboard(int totalNumberOfEvents) {
        this.totalNumberOfEvents = totalNumberOfEvents;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        StudyDashboard studyDashboard = new StudyDashboard(15);
        studyDashboard.print();
    }

    private Participant findParticipant(String username, List<Participant> participants) {
        Participant participant = null;
        if (participants.stream().noneMatch(p -> p.username().equals(username))) {
            participant = new Participant(username);
            participants.add(participant);
        } else {
            participant = participants.stream().filter(p -> p.username().equals(username)).findFirst().orElseThrow();
        }
        return participant;
    }

    private void print() throws IOException, InterruptedException {
        GitHub gitHub = GithubConfig.getInstance();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        List<Participant> participants = new CopyOnWriteArrayList<>();

        ExecutorService service = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(totalNumberOfEvents);

        for (int index = 1 ; index <= totalNumberOfEvents ; index++) {
            int eventId = index;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        GHIssue issue = repository.getIssue(eventId);
                        List<GHIssueComment> comments = issue.getComments();

                        for (GHIssueComment comment : comments) {
                            Participant participant = findParticipant(comment.getUserName(), participants);
                            participant.setHomeworkDone(eventId);
                        }

                        latch.countDown();
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            });
        }

        latch.await();
        service.shutdown();

        //마크 다운을 생성하는 코드 -> 앞으로 csv, excel, markdown등 다양하게 표현 할 수 있을 것 같다.
        new StudyPrinter(this.totalNumberOfEvents, participants).extracted();
    }
}
