package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.SubjectUser;
import com.example.IDATT2015QS3REST.model.User;

import java.util.List;

/**
 * An interface that represents SubjectStudentRepository
 */
public interface SubjectStudentRepository {

    /**
     * An overrideable method for adding a student to a subject
     * @param lastName The lastname of the student who is added to the subject
     * @param name The firstname of the student who is added to the subject
     * @param subjectId The subjectId
     * @return status int
     */
    int addStudent(String lastName, String name, int subjectId);

    /**
     * An overrideable method for creating a student
     * @param lastName The lastname of the student who is created
     * @param name The firstname of the student who is created
     * @param email The email of the student who is created
     * @return status int
     */
    int createStudent(String lastName, String name, String email);

    /**
     * An overrideable method for checking if a user exist in the system
     * @param lastName The lastname of the student
     * @param name The firstname the student
     * @return boolean
     */
    boolean findUser(String lastName, String name);

    /**
     * An overrideable method for checking if the user exist in a subject
     * @param lastName The lastname of the student
     * @param name The fistname of the student
     * @param subjectid The subjectId of the subject
     * @return boolean
     */
    boolean findUserInSubject(String lastName, String name, int subjectid);

    /**
     * An overrideable method for adding a teacher to a subject
     * @param subjectUser The subjectUser object that hold userDetails and subjectId
     * @return status int
     */
    int addTeacher(SubjectUser subjectUser);

    /**
     * An overrideable method for fetching all students and stud.ass in a subject
     * @param subjectId The subjectId for the subject
     * @return A list of users
     */
    List<User> getUsersSubject(int subjectId);

}
