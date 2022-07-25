package ru.job4j.collection;
import org.junit.Test;
import java.util.Comparator;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class JobTest {
    @Test
    public void whenComparatorByNameDesc() {
        Comparator<Job> cmpNamePriority = new JobDescByName();
        int rsl = cmpNamePriority.compare(
                new Job("aaaa", 0),
                new Job("bbbb", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByNameAsc() {
        Comparator<Job> cmpNamePriority = new JobAscByName();
        int rsl = cmpNamePriority.compare(
                new Job("aaaa", 0),
                new Job("bbbb", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByPriorityAsc() {
        Comparator<Job> cmpNamePriority = new JobAscByPriority();
        int rsl = cmpNamePriority.compare(
                new Job("aaaa", 0),
                new Job("bbbb", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByPriorityDesc() {
        Comparator<Job> cmpNamePriority = new JobDescByPriority();
        int rsl = cmpNamePriority.compare(
                new Job("aaaa", 0),
                new Job("bbbb", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByNameAscAndPriorityDesc() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("aaaa", 0),
                new Job("aaaa", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByPriorityAscAndByNameDesc() {
        Comparator<Job> cmpNamePriority = new JobAscByPriority().thenComparing(new JobDescByName());
        int rsl = cmpNamePriority.compare(
                new Job("bbbb", 1),
                new Job("aaaa", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }
}