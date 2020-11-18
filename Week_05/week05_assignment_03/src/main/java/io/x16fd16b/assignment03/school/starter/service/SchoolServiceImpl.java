package io.x16fd16b.assignment03.school.starter.service;

import io.x16fd16b.assignment03.school.starter.School;

/**
 * @author David Liu
 */
public class SchoolServiceImpl implements SchoolService {
    @Override
    public void print(School school) {
        System.out.println(school);
    }
}
