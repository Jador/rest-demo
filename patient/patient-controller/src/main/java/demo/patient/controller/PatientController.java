package demo.patient.controller;

import demo.common.Response;
import demo.patient.api.PatientService;
import demo.patient.model.Patient;
import exception.ResourceNotFoundExcpetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * [Class Description Here]
 *
 * @author Tyler Graham
 */
@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Response<Patient> getPatients() {
        Response<Patient> response = new Response<Patient>();
        response.getEntities().addAll(service.getAll());
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Patient> getPatientById(@PathVariable String id, HttpServletResponse httpResponse) {
        Response<Patient> response = new Response<Patient>();
        try {
            response.setSuccess(true);
            response.getEntities().add(service.getById(id));
        } catch (ResourceNotFoundExcpetion resourceNotFoundExcpetion) {
            response.setSuccess(false);
            response.getErrors().add(resourceNotFoundExcpetion.getMessage());
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return response;
    }

    @RequestMapping(value = "/", params = "mrn")
    @ResponseBody
    public Response<Patient> getPatientByMrn(@RequestParam String mrn) {
        Response<Patient> response = new Response<Patient>();
        response.getEntities().add(service.getByMrn(mrn));
        return response;
    }

    @RequestMapping(value = "/", params = "lastName")
    @ResponseBody
    public Response<Patient> getPatientsByLastName(@RequestParam String lastName) {
        Response<Patient> response = new Response<Patient>();
        response.getEntities().addAll(service.getByLastName(lastName));
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, headers = "Content-type=application/json")
    @ResponseBody
    public Response<Patient> addPatient(@RequestBody Patient patient) {
        Response<Patient> response = new Response<Patient>();
        response.getEntities().add(service.updatePatient(patient));
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Content-type=application/json")
    @ResponseBody
    public Response<Patient> updatePatient(@PathVariable String id, @RequestBody Patient patient) {
        Response<Patient> response = new Response<Patient>();

        if(!patient.getId().equals(id)) {

            String mrn = patient.getMrn();
            String lastName = patient.getLastName();
            String firstName = patient.getFirstName();

            patient = new Patient(id, mrn, firstName, lastName);
        }

        response.getEntities().add(service.updatePatient(patient));
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable String  id) {
        Response response = new Response();
        response.setSuccess(true);
        service.removePatient(id);
        return response;
    }

}
