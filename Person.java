package Home_work;
import java.time.LocalDate;

class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate dateOfBirth;
    private long phoneNumber;
    private char gender;

    public Person(String lastName, String firstName, String middleName, LocalDate dateOfBirth, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }
}
