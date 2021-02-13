package ru.job4j.collection;

import org.junit.Test;
import java.util.Comparator;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

public class JobTest {

    @Test
    public void whenCmpAscByName() {
        Comparator<Job> cmpAscName = new JobAscByName();
        int rsl = cmpAscName.compare(
                new Job("Make task 1", 1),
                new Job("Make task again", 2)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCmpDescByName() {
        Comparator<Job> cmpDescName = new JobAscByName();
        int rsl = cmpDescName.compare(
                new Job("Make task again", 1),
                new Job("Make task 1", 2)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCmpAscByPriority() {
        Comparator<Job> cmpAscPriority = new JobAscByPriority();
        int rsl = cmpAscPriority.compare(
                new Job("Make task 1", 1),
                new Job("Make task again", 2)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCmpDescByPriority() {
        Comparator<Job> cmpDescPriority = new JobDescByPriority();
        int rsl = cmpDescPriority.compare(
                new Job("Make task 1", 1),
                new Job("Make task again", 2)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCmpDescByPriority1() {
        Comparator<Job> cmpDescPriority = new JobAscByPriority()
                .thenComparing(new JobAscByName());
        int rsl = cmpDescPriority.compare(
                new Job("Make task 1", 1),
                new Job("Make task again", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCmpByNameAndPriority2() {
        Comparator<Job> cmpNamePriority = new JobDescByName()
                .thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }
}