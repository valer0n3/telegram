package ru.training.trainingprogram.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.training.trainingprogram.dto.DtoTrainingProgramGet;
import ru.training.trainingprogram.dto.DtoTrainingProgramPost;
import ru.training.trainingprogram.mapper.TrainingProgramMapper;
import ru.training.trainingprogram.repository.TrainingProgramRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class TrainingProgramServiceImpl implements TrainingProgramService {
    private final TrainingProgramMapper trainingProgramMapper;
    private final TrainingProgramRepository trainingProgramRepository;

    @Override
    @Transactional
    public DtoTrainingProgramGet createNewProgram(DtoTrainingProgramPost dtoTrainingProgramPost) {
        return trainingProgramMapper.TrainingProgramModelToTrainingProgramGet(trainingProgramRepository
                .save(trainingProgramMapper.DtoTrainingProgramPostToTrainingProgramModel(dtoTrainingProgramPost)));
    }

    @Override
    public List<DtoTrainingProgramGet> getAllTrainingPrograms(int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size, Sort.by("trainingProgramName"));
        return trainingProgramRepository.findAll(pageable).stream()
                .map(trainingProgramMapper::TrainingProgramModelToTrainingProgramGet).collect(Collectors.toList());
    }
}
