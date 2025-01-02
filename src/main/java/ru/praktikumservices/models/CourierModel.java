package ru.praktikumservices.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes null fields from JSON
public class CourierModel {

    private String login;
    private String password;
    private String firstName;

    // No-args constructor (required for serialization/deserialization)
    public CourierModel() {
    }

    // Constructor with main parameters
    public CourierModel(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    // Getters and setters for all fields
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {

        // The toString method is used for convenient display of object information.
        // This is useful, for example, in logs, debugging, and for generating descriptions of test parameters
        // that will be displayed in reports such as Allure.
        return "CourierModel{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + '}';
    }

}
