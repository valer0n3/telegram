package ru.training.muscles.controller;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.training.muscles.service.MuscleServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MuscleController.class)
class MuscleControllerTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MuscleServiceImpl muscleService;

    @Test
    void saveNewMuscle() {
    }

    @SneakyThrows
    @Test
    void getMuscleTrainingProgram() {
        Long muscleId = null;
        MvcResult mvcResult = mockMvc.perform(get("/muscle?muscleId={muscleId}", muscleId))
                .andExpect(status().isBadRequest())
                .andReturn();
        mvcResult.getResponse();
    }

    @Test
    void getAllMuscles() {
    }
}