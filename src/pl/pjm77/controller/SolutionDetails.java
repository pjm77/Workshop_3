package pl.pjm77.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.pjm77.DAO.RealExerciseDAO;
import pl.pjm77.DAO.RealSolutionDAO;
import pl.pjm77.DAO.RealUserDAO;
import pl.pjm77.DAO.UserDAO;
import pl.pjm77.misc.RealDataSource;
import pl.pjm77.model.Exercise;
import pl.pjm77.model.Solution;
import pl.pjm77.model.User;

@WebServlet("/solutiondetails")
public class SolutionDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public SolutionDetails() {
        super();
    }

    public void init() {
        userDAO = new RealUserDAO(RealDataSource.initDB());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        long solutionId = 0;
        if (idParam != null && !idParam.equals("")) {
            try {
                solutionId = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect solution Id!");
                e.printStackTrace();
            }
        }
        response.getWriter().append("Solution Id : ").append(String.valueOf(solutionId));
        Solution solution = new RealSolutionDAO().loadSolutionById(solutionId);
        request.setAttribute("solution", solution);
        User user = userDAO.loadUserById(solution.getUser_id());
        request.setAttribute("user", user);
        Exercise exercise = new RealExerciseDAO().loadExerciseById(solution.getExercise_id());
        request.setAttribute("exercise", exercise);
        getServletContext().getRequestDispatcher("/jsp/solutiondetailsview.jsp").forward(request, response);
    }
}