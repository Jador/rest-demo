package demo.patient.service;

import demo.patient.api.PatientService;
import demo.patient.model.Patient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Tests to ensure that the {@link demo.patient.service.PatientServiceImpl} functions as expected
 *
 * @author Tyler Graham
 */
public class PatientServiceImplTest {

    private PatientService service;

    @Before
    public void setup() {
        service = new PatientServiceImpl();
    }

    @Test
    public void testAdd() {
        Patient p = createPatient("mrn", "Doe", "John");

        assertEquals(p.getLastName(), "Doe");
        assertNotNull(p.getId());
        assertEquals(1, service.getAll().size());
    }

    @Test
    public void testUpdate() {
        Patient p = createPatient("mrn", "Doe", "John");
        p.setFirstName("Jane");
        p = service.updatePatient(p);

        assertEquals(p.getFirstName(), "Jane");
    }

    @Test
    public void testGetById() {
        Patient p = createPatient("mrn", "Doe", "John");
        Patient p2 =  service.getById(p.getId());
        assertEquals(p, p2);
    }

    @Test
    public void testGetByMrn() {
        Patient p = createPatient("mrn", "Doe", "John");
        Patient p2 = service.getByMrn("mrn");
        assertEquals(p, p2);
    }

    @Test
    public void testRemove() {
        Patient p = createPatient("mrn", "Doe", "John");
        createPatient("mrn", "Doe", "Jane");

        assertEquals(2, service.getAll().size());

        service.removePatient(p.getId());

        assertEquals(1, service.getAll().size());
        assertFalse(p.equals(service.getAll().get(0)));
    }

    @Test
    public void testGetByLastName() {
        createPatient("mrn", "Doe", "John");
        createPatient("mrn", "Herp", "Derp");
        createPatient("mrn", "Doe", "Jane");

        List<Patient> list = service.getByLastName("Doe");

        assertEquals(2, list.size());
    }

    public Patient createPatient(String mrn, String lastName, String firstName) {
        return service.addPatient(mrn, lastName, firstName);
    }

}
