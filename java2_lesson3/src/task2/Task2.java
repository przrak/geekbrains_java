package task2;

import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        final TelephoneDictionary telephoneDictionary = new TelephoneDictionary();
        telephoneDictionary.add(new TelephoneRecord("Bokach", "7-777-777-77-77"));
        telephoneDictionary.add(new TelephoneRecord("Bokach", "7-777-777-77-78"));
        telephoneDictionary.add(new TelephoneRecord("Bokach", "7-777-777-77-79"));
        telephoneDictionary.add(new TelephoneRecord("Ivanov", "7-777-777-77-88"));
        telephoneDictionary.add(new TelephoneRecord("Petrov", "7-777-777-77-99"));
        telephoneDictionary.add(new TelephoneRecord("Sidorov", "7-777-777-77-55"));
        telephoneDictionary.add(new TelephoneRecord("Alekseev", "7-777-777-77-44"));
        telephoneDictionary.add(new TelephoneRecord("Frolov", "7-777-777-77-22"));
        telephoneDictionary.add(new TelephoneRecord("Gubarev", "7-777-777-77-11"));
        telephoneDictionary.add(new TelephoneRecord("Sidorov", "7-777-777-77-00"));
        telephoneDictionary.add(new TelephoneRecord("Ivanov", "7-777-777-77-33"));
        telephoneDictionary.add(new TelephoneRecord("Frolov", "7-777-777-77-21"));
        telephoneDictionary.add(new TelephoneRecord("Zaycev", "7-777-777-77-32"));

        final List<TelephoneRecord> result1 = telephoneDictionary.getAllRecordsByFamily("Bokach");
        for (TelephoneRecord record : result1) {
            System.out.println(record.getSurname() + " | " + record.getNumber());
        }

        final List<TelephoneRecord> result2 = telephoneDictionary.getAllRecordsByFamily("Petrov");
        for (TelephoneRecord record : result2) {
            System.out.println(record.getSurname() + " | " + record.getNumber());
        }
    }
}
