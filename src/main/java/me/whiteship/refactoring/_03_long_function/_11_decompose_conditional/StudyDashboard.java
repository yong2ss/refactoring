package me.whiteship.refactoring._03_long_function._11_decompose_conditional;

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
 * 조건문 분해하기
 * - 다양한 조건에서 종종 긴 함수가 만들어지는 것을 목격할 수 있다
 * - "조건"과 "액션" 모두 "의도"를 표현해야한다.
 * - 기술적으로는 "함수 추출하기"와 동일한 리팩토링이지만 의도만 다르다
 * 
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

        new StudyPrinter(this.totalNumberOfEvents, participants).execute();
    }

    //TO-BE
    private Participant findParticipant(String username, List<Participant> participants) {
        return isNewParticipant(username, participants) ?
                createNewParticipant(username, participants) :
                findExistingParticipant(username, participants);
    }

    private static Participant findExistingParticipant(String username, List<Participant> participants) {
        return participants.stream().filter(p -> p.username().equals(username)).findFirst().orElseThrow();
    }

    private static Participant createNewParticipant(String username, List<Participant> participants) {
        Participant participant;
        participant = new Participant(username);
        participants.add(participant);
        return participant;
    }

    private static boolean isNewParticipant(String username, List<Participant> participants) {
        return participants.stream().noneMatch(p -> p.username().equals(username));
    }

    //AS-IS
/*    private Participant findParticipant(String username, List<Participant> participants) {
        Participant participant;
        if (participants.stream().noneMatch(p -> p.username().equals(username))) {
            participant = new Participant(username);
            participants.add(participant);
        } else {
            participant = participants.stream().filter(p -> p.username().equals(username)).findFirst().orElseThrow();
        }

        return participant;
    }*/
}
