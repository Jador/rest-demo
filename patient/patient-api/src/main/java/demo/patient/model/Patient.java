package demo.patient.model;

import java.util.UUID;

/**
 * Patient Bean
 *
 * @author Tyler Graham
 */
public class Patient {

    private final String id;

    private String mrn;
    private String lastName;
    private String firstName;

    public Patient() {
        id = UUID.randomUUID().toString();
    }

    public Patient(String mrn, String lastName, String firstName) {
        this();
        this.mrn = mrn;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Patient(String id) {
        this.id = id;
    }

    public Patient(String id, String mrn, String lastName, String firstName) {
        this.id = id;
        this.mrn = mrn;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Patient)) {
            return false;
        }

        Patient p = (Patient) obj;

        return (p.getId().equals(this.getId())
                && p.getMrn().equals(this.getMrn())
                && p.getLastName().equals(this.getLastName())
                && p.getFirstName().equals(this.getFirstName())
        );

    }
}
