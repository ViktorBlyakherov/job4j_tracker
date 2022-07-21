package ru.job4j.map;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class AnalyzeByMapTest {
    @Test
    public void whenAverageScore() {
        double average = AnalyzeByMap.averageScore(
                List.of(
                        new AnalyzeByMap.Pupil("Ivanov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 100),
                                        new AnalyzeByMap.Subject("Lang", 70),
                                        new AnalyzeByMap.Subject("Philosophy", 80)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Petrov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 80),
                                        new AnalyzeByMap.Subject("Lang", 90),
                                        new AnalyzeByMap.Subject("Philosophy", 70)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Sidorov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 70),
                                        new AnalyzeByMap.Subject("Lang", 60),
                                        new AnalyzeByMap.Subject("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(average).isCloseTo(74.44, offset(0.01D));
    }

    @Test
    public void whenListOfPupilAverage() {
        List<AnalyzeByMap.Label> average = AnalyzeByMap.averageScoreByPupil(
                List.of(
                        new AnalyzeByMap.Pupil("Ivanov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 100),
                                        new AnalyzeByMap.Subject("Lang", 60),
                                        new AnalyzeByMap.Subject("Philosophy", 80)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Petrov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 80),
                                        new AnalyzeByMap.Subject("Lang", 90),
                                        new AnalyzeByMap.Subject("Philosophy", 70)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Sidorov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 70),
                                        new AnalyzeByMap.Subject("Lang", 60),
                                        new AnalyzeByMap.Subject("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(average).hasSameElementsAs(List.of(
                new AnalyzeByMap.Label("Ivanov", 80D),
                new AnalyzeByMap.Label("Petrov", 80D),
                new AnalyzeByMap.Label("Sidorov", 60D)
        ));
    }

    @Test
    public void whenListOfSubjectAverage() {
        List<AnalyzeByMap.Label> average = AnalyzeByMap.averageScoreBySubject(
                List.of(
                        new AnalyzeByMap.Pupil("Ivanov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 70),
                                        new AnalyzeByMap.Subject("Lang", 90),
                                        new AnalyzeByMap.Subject("Philosophy", 100)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Petrov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 60),
                                        new AnalyzeByMap.Subject("Lang", 60),
                                        new AnalyzeByMap.Subject("Philosophy", 60)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Sidorov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 80),
                                        new AnalyzeByMap.Subject("Lang", 60),
                                        new AnalyzeByMap.Subject("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(average).hasSameElementsAs(List.of(
                new AnalyzeByMap.Label("Math", 70D),
                new AnalyzeByMap.Label("Lang", 70D),
                new AnalyzeByMap.Label("Philosophy", 70D)
        ));
    }

    @Test
    public void whenBestPupil() {
        AnalyzeByMap.Label best = AnalyzeByMap.bestStudent(
                List.of(
                        new AnalyzeByMap.Pupil("Ivanov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 100),
                                        new AnalyzeByMap.Subject("Lang", 60),
                                        new AnalyzeByMap.Subject("Philosophy", 80)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Petrov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 80),
                                        new AnalyzeByMap.Subject("Lang", 80),
                                        new AnalyzeByMap.Subject("Philosophy", 70)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Sidorov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 70),
                                        new AnalyzeByMap.Subject("Lang", 60),
                                        new AnalyzeByMap.Subject("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(best).isEqualTo(new AnalyzeByMap.Label("Ivanov", 240D));
    }

    @Test
    public void whenBestSubject() {
        AnalyzeByMap.Label best = AnalyzeByMap.bestSubject(
                List.of(
                        new AnalyzeByMap.Pupil("Ivanov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 100),
                                        new AnalyzeByMap.Subject("Lang", 60),
                                        new AnalyzeByMap.Subject("Philosophy", 80)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Petrov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 80),
                                        new AnalyzeByMap.Subject("Lang", 90),
                                        new AnalyzeByMap.Subject("Philosophy", 70)
                                )
                        ),
                        new AnalyzeByMap.Pupil("Sidorov",
                                List.of(
                                        new AnalyzeByMap.Subject("Math", 70),
                                        new AnalyzeByMap.Subject("Lang", 60),
                                        new AnalyzeByMap.Subject("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(best).isEqualTo(new AnalyzeByMap.Label("Math", 250D));
    }
}