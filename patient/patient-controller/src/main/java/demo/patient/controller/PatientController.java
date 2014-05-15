package demo.patient.controller;

import demo.common.Response;
import demo.patient.api.PatientService;
import demo.patient.model.Patient;
import exception.ResourceNotFoundException;
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
    public Response<Patient> getAll() {
        Response<Patient> response = new Response<Patient>();

        response.getEntities().addAll(service.getAll());
        response.setSuccess(true);

        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Patient> getById(@PathVariable String id, HttpServletResponse httpResponse) {
        Response<Patient> response = new Response<Patient>();

        try {
            response.getEntities().add(service.getById(id));
            response.setSuccess(true);
        } catch (ResourceNotFoundException e) {
            response.getErrors().add(e.getMessage());
            response.setSuccess(false);
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return response;
    }

    @RequestMapping(value = "/", params = "lastName", method = RequestMethod.GET)
    @ResponseBody
    public Response<Patient> getByLastName(@RequestParam String lastName) {
        Response<Patient> response = new Response<Patient>();

        response.getEntities().addAll(service.getByLastName(lastName));
        response.setSuccess(true);

        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response<Patient> addPatient(@RequestBody Patient patient, HttpServletResponse httpResponse) {
        Response<Patient> response = new Response<Patient>();

        response.getEntities().add(service.updatePatient(patient));
        response.setSuccess(true);
        httpResponse.setStatus(HttpServletResponse.SC_CREATED);

        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Response<Patient> update(@PathVariable String id, @RequestBody Patient patient) {
        if (!id.equals(patient.getId())) {
            String firstName = patient.getFirstName();
            String lastName = patient.getLastName();

            patient = new Patient(id, firstName, lastName);
        }

        Response<Patient> response = new Response<Patient>();
        response.getEntities().add(service.updatePatient(patient));
        response.setSuccess(true);

        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable String id, HttpServletResponse httpResponse) {
        Response response = new Response();
        response.setSuccess(true);

        try {
            service.removePatient(id);
        } catch (ResourceNotFoundException e) {
            response.setSuccess(false);
            response.getErrors().add(e.getMessage());
        }

        return response;
    }

}
