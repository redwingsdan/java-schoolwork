package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
/**
 *
 * @author clotifoth
 */
public class RegisterBean implements Serializable{
    
    private String firstName;
    private String lastName;
    private String email;
    private String password; // not secure practice haha
    private String username;
    private String country;
    private String city;
    private String securityQuestion;
    private String securityAnswer;
    private String language;
    
    public RegisterBean() {
        super();
    }
    
    public String toString()
    {
        return this.firstName + "\n" +
                this.lastName + "\n" +
                this.email + "\n" +
                this.password + "\n" +
                this.username + "\n" +
                this.country + "\n" +
                this.city + "\n" +
                this.language + "\n" +
                this.securityQuestion + "\n" +
                this.securityAnswer + "\n";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    public boolean isValid()
    {
        return !(this.email == null || this.email.isEmpty());
    }
    
    
}
