package demo.patient.api;

import demo.patient.model.Patient;
import exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service for {@link demo.patient.model.Patient}s
 *
 * @author Tyler Graham
 */
public interface PatientService {


    //=============================//
    //            UPDATE
    //=============================//

    /**
     * Persists an updated {@link demo.patient.model.Patient} and returns the new object
     */
    public Patient updatePatient(Patient patient);


    //=============================//
    //          RETRIEVE
    //=============================//

    /**
     * @return all {@link Patient}s
     */
    public List<Patient> getAll();

    /**
     * @return the {@link demo.patient.model.Patient} with the given {@code ID}
     */
    public Patient getById(String id) throws ResourceNotFoundException;

    /**
     * @return the {@link demo.patient.model.Patient} with the given {@code MRN}
     */
    public Patient getByMrn(String mrn);

    /**
     * @return all {@link demo.patient.model.Patient}s with the given {@code last name}
     */
    public List<Patient> getByLastName(String lastName);


    //=============================//
    //           DELETE
    //=============================//

    /**
     * Deletes a {@link demo.patient.model.Patient} with the given {@code ID}
     */
    public void removePatient(String id) throws ResourceNotFoundException;
}
