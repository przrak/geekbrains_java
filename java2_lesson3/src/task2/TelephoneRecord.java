package task2;

public class TelephoneRecord {

    private final String surname;

    private final String number;

    public TelephoneRecord(final String surname, final String number) {
        this.surname = surname;
        this.number = number;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "TelephoneRecord{" +
                "surname='" + surname + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
