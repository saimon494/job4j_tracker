package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProfilesTest {

    @Test
    public void collect() {
        List<Profile> profiles = new ArrayList<>();
        Address address1 = new Address("Moscow", "Lenina", 13, 3);
        Address address2 = new Address("Piter", "Lenina", 12, 5);
        Address address3 = new Address("Omsk", "Lenina", 1, 6);
        profiles.add(new Profile(address1));
        profiles.add(new Profile(address2));
        profiles.add(new Profile(address3));

        List<Address> rsl = Profiles.collect(profiles);
        List<Address> expected = Arrays.asList(address1, address2, address3);
        assertThat(rsl, is(expected));
    }

    @Test
    public void collectUnique() {
        List<Profile> profiles = new ArrayList<>();
        Address address1 = new Address("Moscow", "Lenina", 13, 3);
        Address address2 = new Address("Moscow", "Lenina", 13, 3);
        Address address3 = new Address("Rostov", "Lenina", 1, 6);
        profiles.add(new Profile(address1));
        profiles.add(new Profile(address2));
        profiles.add(new Profile(address3));

        List<Address> rsl = Profiles.collectUnique(profiles);
        List<Address> expected = Arrays.asList(address1, address3);
        assertThat(rsl, is(expected));
    }
}