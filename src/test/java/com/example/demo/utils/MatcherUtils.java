package com.example.demo.utils;

import com.example.demo.domain.entity.User;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class MatcherUtils {

    public static Matcher<List<User>> hasUserWithId(final Long expected) {
        return new BaseMatcher<List<User>>() {

            protected Long theExpected = expected;

            @Override
            public boolean matches(Object o) {
                ArrayList<User> list = (ArrayList<User>) o;
                return list.stream().filter( x -> x.getId().equals(expected)).findFirst().isPresent();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(theExpected.toString());
            }
        };
    }


    public static Matcher<List<User>> hasUserWithUsername(final String expected) {
        return new BaseMatcher<List<User>>() {

            protected String theExpected = expected;

            @Override
            public boolean matches(Object o) {
                ArrayList<User> list = (ArrayList<User>) o;
                return list.stream().filter( x -> x.getUsername().equals(expected)).findFirst().isPresent();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(theExpected.toString());
            }
        };
    }

    public static Matcher<List<User>> hasUserWithFirstnameOrLastnameContaining(final String expected) {
        return new BaseMatcher<List<User>>() {

            protected String theExpected = expected.toLowerCase();

            @Override
            public boolean matches(Object o) {
                ArrayList<User> list = (ArrayList<User>) o;
                return list.stream()
                        .filter( x -> x.getFirstname().toLowerCase().contains(expected) || x.getLastname().toLowerCase().contains(expected))
                        .findFirst()
                        .isPresent();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(theExpected.toString());
            }
        };
    }
}
