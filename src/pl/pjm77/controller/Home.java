package pl.pjm77.controller;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.pjm77.DAO.LastSolutionDAO;
import pl.pjm77.model.LastSolution;

@WebServlet(value = "/")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Home() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contextParam = getServletContext().getInitParameter("number-solutions");
        int recentSolutions = 5;
        if (contextParam != null & !Objects.equals(contextParam, "")) {
            try {
                recentSolutions = Integer.parseInt(contextParam);
            } catch (NumberFormatException n) {
                System.out.println("Parameter must be an integer value, using default" +
                        " value of 5 instead!");
            }
        }
        LastSolution[] lastSolutions =
                LastSolutionDAO.loadAllSolutions(recentSolutions);
        request.setAttribute("lastsolutions", lastSolutions);

        getServletContext().getRequestDispatcher("/jsp/index.jsp")
                           .forward(request, response);
    }
}