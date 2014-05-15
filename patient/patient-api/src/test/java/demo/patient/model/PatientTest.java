package demo.patient.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Tyler Graham
 */
public class PatientTest {

    /**
     * {@link demo.patient.model.Patient#equals(Object)} tests to make sure that all fields are equal including id
     * which can only be set during instantiation
     * <br/><br/>
     * This tests ensures that two {@link demo.patient.model.Patient}s created with the same values are {@code equal}
     */
    @Test
    public void testEquals() {
        Patient p1 = createPatient();
        Patient p2 = new Patient(p1.getId(), p1.getMrn(), p1.getLastName(), p1.getFirstName());

        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));
    }


    /**
     * This test ensures that when referencing itself {@link demo.patient.model.Patient#equals(Object)} returns {@code true}
     */
    @Test
    public void testEqualsSameReference() {
        Patient p1 = createPatient();
        Patient p2 = p1;

        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));
    }


    /**
     * This test ensures that if the ids are different the result is {@code false}
     */
    @Test
    public void testNotEqualsWithDiffId() {
        Patient p1 = createPatient();
        Patient p2 = createPatient();

        assertFalse(p1.equals(p2));
        assertFalse(p2.equals(p1));
    }


    /**
     * This test ensures that if an object that is not a {@link demo.patient.model.Patient}, or a derivative there of,
     * the result is {@code false}
     */
    @Test
    public void testNotEqualsDiffObj() {
        Patient p = createPatient();
        Object obj = new Object();

        assertFalse(p.equals(obj));
        assertFalse(obj.equals(p));
    }

    private Patient createPatient() {
        return new Patient("mrn", "Doe", "John");
    }
}
