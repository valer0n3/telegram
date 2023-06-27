package ru.training.service.Strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.training.DtoMuscleGet;
import ru.training.WebClientController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BotButtonsTest {
    @Mock
    private WebClientController webClientController;
    private BotButtons botButtons;
    private String expectedErrorMessage = "Программа тренировок отсутствует";

    @BeforeEach
    public void beforeEach() {
        botButtons = new BotButtons(webClientController);
    }

    @Test
    void checkIfTrainingExistsIfListIsNullOrEmptyThenReturnErrorMessage() {
        List<DtoMuscleGet> dtoMuscleGets = null;
        String errorMessage = botButtons.checkIfTrainingExists(dtoMuscleGets);
        assertEquals(expectedErrorMessage, errorMessage, "No check when list is null");
        dtoMuscleGets = new ArrayList<>();
        assertEquals(expectedErrorMessage, errorMessage, "No check when list is null");
    }

    @Test
    void checkIfTrainingExistsIfListOfDtoTrainingProgramGetIsNullOrEmptyThenReturnErrorMessage() {
        DtoMuscleGet dtoMuscleGet = new DtoMuscleGet();
        List<DtoMuscleGet> dtoMuscleGets = new ArrayList<>();
        dtoMuscleGets.add(dtoMuscleGet);
        String errorMessage = botButtons.checkIfTrainingExists(dtoMuscleGets);
        assertEquals(expectedErrorMessage, errorMessage, "No check when list is null");
        dtoMuscleGets.get(0).setListOfTrainingPrograms(new ArrayList<>());
        assertEquals(expectedErrorMessage, errorMessage, "No check when list is null");
    }

    @Test
    void createButtonsInLine() {
    }

    @Test
    void generatedMessage() {
    }

    @Test
    void generateResponseOnButtonClick() {
    }
}