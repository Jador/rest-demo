package demo.patient.controller;

import demo.patient.api.PatientService;
import demo.patient.model.Patient;
import exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * [Class Description Here]
 *
 * @author Tyler Graham
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:dispatcher-servlet.xml"})
@WebAppConfiguration
public class PatientControllerTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        Mockito.reset(patientService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetPatientById() throws Exception {
        when(patientService.getById(anyString())).thenReturn(createPatient());

        MvcResult result = mockMvc.perform(get("/patients/someId"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entities", hasSize(1)))
                .andExpect(jsonPath("$.errors", hasSize(0)))
                .andExpect(jsonPath("$.success", is(equalTo(true))))
                .andExpect(jsonPath("$.entities[0].firstName", is(equalTo("John"))))
                .andReturn();

        verify(patientService, times(1)).getById(anyString());
        System.out.println("get patient result:\n" + result.getResponse().getContentAsString());
    }

    @Test
    public void testGetPatientByIdNotFound() throws Exception {
        when(patientService.getById(anyString())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String id = (String) invocationOnMock.getArguments()[0];
                throw new ResourceNotFoundException("Could not find patient with id: " + id);
            }
        });

        MvcResult result = mockMvc.perform(get("/patients/someId"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.entities", hasSize(0)))
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.success", is(equalTo(false))))
                .andExpect(jsonPath("$.errors[0]", containsString("Could not find patient")))
                .andReturn();

        verify(patientService, times(1)).getById(anyString());
        System.out.println("get patient not found result:\n" + result.getResponse().getContentAsString());
    }

    private List<Patient> createPatients(int num) {
        List<Patient> patients = new ArrayList<Patient>(num);

        for (int i = 0; i < num; i++) {
            patients.add(createPatient());
        }

        return patients;
    }

    private Patient createPatient() {
        return new Patient("mrn1", "Doe", "John");
    }

}
