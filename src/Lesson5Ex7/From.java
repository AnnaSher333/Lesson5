package Lesson5Ex7;

public class From {
    private String name;
    private String lastname;
    private int birthday;
    private char pol;


    public From(String name, String lastname, int birthday, char pol) {
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.pol = pol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String surname) {
        this.lastname = surname;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public char getPol() {
        return pol;
    }

    public void setPol(char pol) {
        this.pol = pol;
    }
}
