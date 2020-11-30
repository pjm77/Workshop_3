package mockDAOs;

import com.panpawelw.DAO.ExerciseDAO;
import com.panpawelw.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class MockExerciseDAO implements ExerciseDAO {

    @Override
    public int saveExerciseToDB(Exercise exercise) {
        return 1;
    }

    @Override
    public Exercise loadExerciseById(int id) {
        return new Exercise();
    }

    @Override
    public int deleteExercise(Exercise exercise) {
        return 1;
    }

    @Override
    public List<Exercise> loadAllExercises() {
        return new ArrayList<>();
    }
}