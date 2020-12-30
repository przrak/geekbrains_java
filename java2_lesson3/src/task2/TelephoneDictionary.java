package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TelephoneDictionary {

    private final List<TelephoneRecord> telephoneRecords;

    public TelephoneDictionary() {
        this.telephoneRecords = new ArrayList<>();
    }

    public void add(final TelephoneRecord telephoneRecord) {
        this.validateNewRecord(telephoneRecord);
        this.telephoneRecords.add(telephoneRecord);
    }

    private void validateNewRecord(final TelephoneRecord telephoneRecord) {
        if (telephoneRecord == null)  {
            throw new RuntimeException("Record cannot be null");
        }
        if (telephoneRecord.getSurname() == null || telephoneRecord.getSurname().isEmpty()) {
            throw new RuntimeException("Surname cannot be empty or null");
        }
        if (telephoneRecord.getNumber() == null || telephoneRecord.getNumber().isEmpty()) {
            throw new RuntimeException("Number cannot be empty or null");
        }
        for (final TelephoneRecord record : telephoneRecords) {
            if (record.getNumber().equals(telephoneRecord.getNumber())) {
                throw new RuntimeException("Number already exists");
            }
        }
    }

    public List<TelephoneRecord> getAllRecordsByFamily(final String family) {
        if (family == null || family.isEmpty()) {
            throw new RuntimeException("Family cannot be empty or null!");
        }
        List<TelephoneRecord> result = this.telephoneRecords.parallelStream()
                .filter(p -> p.getSurname().equals(family))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new RuntimeException("No numbers by family were found");
        } else {
            return result;
        }
    }
}
