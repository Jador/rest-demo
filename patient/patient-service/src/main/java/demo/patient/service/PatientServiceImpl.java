package demo.patient.service;

import demo.patient.api.PatientService;
import demo.patient.model.Patient;
import exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring based implementation of {@link demo.patient.api.PatientService}
 *
 * @author Tyler Graham
 */
@Service(value = "patientService")
public class PatientServiceImpl implements PatientService {

    private Map<String, Patient> patientMap;

    public PatientServiceImpl() {
        patientMap = new HashMap<String, Patient>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Patient updatePatient(Patient patient) {
        patientMap.put(patient.getId(), patient);
        return patient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Patient> getAll() {
        return new ArrayList<Patient>(patientMap.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Patient getById(String id) throws ResourceNotFoundException {
        if(patientMap.containsKey(id)) {
            return patientMap.get(id);
        }
        else {
            throw new ResourceNotFoundException("Could not find patient with id: " + id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Patient getByMrn(String mrn) {
        Patient patient = null;

        for(Patient p : patientMap.values()) {
            if(p.getMrn().equals(mrn)) {
                patient = p;
                break;
            }
        }

        return patient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Patient> getByLastName(String lastName) {
        List<Patient> patients = new ArrayList<Patient>();

        for(Patient p : patientMap.values()) {
            if(p.getLastName().equals(lastName)) {
                patients.add(p);
            }
        }

        return patients;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePatient(String id) throws ResourceNotFoundException {
        if (patientMap.containsKey(id)) {
            patientMap.remove(id);
        } else {
            throw new ResourceNotFoundException("Could not remove patient with id: " + id + " as it did not exist");
        }
    }
}
