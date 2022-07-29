package ru.job4j.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class AnalyzeTest {
    @Test
    public void whenAverageScore() {
        double average = Analyze.averageScore(
                List.of(
                        new Analyze.Pupil("Ivanov",
                                List.of(
                                        new Analyze.Subject("Math", 100),
                                        new Analyze.Subject("Lang", 70),
                                        new Analyze.Subject("Philosophy", 80)
                                )
                        ),
                        new Analyze.Pupil("Petrov",
                                List.of(
                                        new Analyze.Subject("Math", 80),
                                        new Analyze.Subject("Lang", 90),
                                        new Analyze.Subject("Philosophy", 70)
                                )
                        ),
                        new Analyze.Pupil("Sidorov",
                                List.of(
                                        new Analyze.Subject("Math", 70),
                                        new Analyze.Subject("Lang", 60),
                                        new Analyze.Subject("Philosophy", 50)
                                )
                        )
                ).stream()
        );
        assertThat(average).isCloseTo(74.44, offset(0.01D));
    }

    @Test
    public void whenListOfPupilAverage() {
        List<Analyze.Tuple> average = Analyze.averageScoreByPupil(
                List.of(
                        new Analyze.Pupil("Ivanov",
                                List.of(
                                        new Analyze.Subject("Math", 100),
                                        new Analyze.Subject("Lang", 60),
                                        new Analyze.Subject("Philosophy", 80)
                                )
                        ),
                        new Analyze.Pupil("Petrov",
                                List.of(
                                        new Analyze.Subject("Math", 80),
                                        new Analyze.Subject("Lang", 90),
                                        new Analyze.Subject("Philosophy", 70)
                                )
                        ),
                        new Analyze.Pupil("Sidorov",
                                List.of(
                                        new Analyze.Subject("Math", 70),
                                        new Analyze.Subject("Lang", 60),
                                        new Analyze.Subject("Philosophy", 50)
                                )
                        )
                ).stream()
        );
        assertThat(average).hasSameElementsAs(List.of(
                new Analyze.Tuple("Ivanov", 80D),
                new Analyze.Tuple("Petrov", 80D),
                new Analyze.Tuple("Sidorov", 60D)
        ));
    }

    @Test
    public void whenListOfSubjectAverage() {
        List<Analyze.Tuple> average = Analyze.averageScoreBySubject(
                List.of(
                        new Analyze.Pupil("Ivanov",
                                List.of(
                                        new Analyze.Subject("Math", 70),
                                        new Analyze.Subject("Lang", 90),
                                        new Analyze.Subject("Philosophy", 100)
                                )
                        ),
                        new Analyze.Pupil("Petrov",
                                List.of(
                                        new Analyze.Subject("Math", 60),
                                        new Analyze.Subject("Lang", 60),
                                        new Analyze.Subject("Philosophy", 60)
                                )
                        ),
                        new Analyze.Pupil("Sidorov",
                                List.of(
                                        new Analyze.Subject("Math", 80),
                                        new Analyze.Subject("Lang", 60),
                                        new Analyze.Subject("Philosophy", 50)
                                )
                        )
                ).stream()
        );
        assertThat(average).hasSameElementsAs(List.of(
                new Analyze.Tuple("Math", 70D),
                new Analyze.Tuple("Lang", 70D),
                new Analyze.Tuple("Philosophy", 70D)
        ));
    }

    @Test
    public void whenBestPupil() {
        Analyze.Tuple best = Analyze.bestStudent(
                List.of(
                        new Analyze.Pupil("Ivanov",
                                List.of(
                                        new Analyze.Subject("Math", 100),
                                        new Analyze.Subject("Lang", 60),
                                        new Analyze.Subject("Philosophy", 80)
                                )
                        ),
                        new Analyze.Pupil("Petrov",
                                List.of(
                                        new Analyze.Subject("Math", 80),
                                        new Analyze.Subject("Lang", 80),
                                        new Analyze.Subject("Philosophy", 70)
                                )
                        ),
                        new Analyze.Pupil("Sidorov",
                                List.of(
                                        new Analyze.Subject("Math", 70),
                                        new Analyze.Subject("Lang", 60),
                                        new Analyze.Subject("Philosophy", 50)
                                )
                        )
                ).stream()
        );
        assertThat(best).isEqualTo(new Analyze.Tuple("Ivanov", 240D));
    }

    @Test
    public void whenBestSubject() {
        Analyze.Tuple best = Analyze.bestSubject(
                List.of(
                        new Analyze.Pupil("Ivanov",
                                List.of(
                                        new Analyze.Subject("Math", 100),
                                        new Analyze.Subject("Lang", 60),
                                        new Analyze.Subject("Philosophy", 80)
                                )
                        ),
                        new Analyze.Pupil("Petrov",
                                List.of(
                                        new Analyze.Subject("Math", 80),
                                        new Analyze.Subject("Lang", 90),
                                        new Analyze.Subject("Philosophy", 70)
                                )
                        ),
                        new Analyze.Pupil("Sidorov",
                                List.of(
                                        new Analyze.Subject("Math", 70),
                                        new Analyze.Subject("Lang", 60),
                                        new Analyze.Subject("Philosophy", 50)
                                )
                        )
                ).stream()
        );
        assertThat(best).isEqualTo(new Analyze.Tuple("Math", 250D));
    }
}