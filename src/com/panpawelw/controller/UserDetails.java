package com.panpawelw.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panpawelw.DAO.*;
import com.panpawelw.misc.DbUtils;
import com.panpawelw.model.LastSolution;
import com.panpawelw.model.User;
import com.panpawelw.model.UserGroup;

@WebServlet("/userdetails")
public class UserDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;
    private UserGroupDAO userGroupDAO;
    private LastSolutionDAO lastSolutionDAO;

    public UserDetails() {
        super();
    }

    public void init() {
        userDAO = new RealUserDAO(DbUtils.initDB());
        userGroupDAO = new RealUserGroupDAO(DbUtils.initDB());
        lastSolutionDAO = new RealLastSolutionDAO(DbUtils.initDB());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int userId = 0;
        if (idParam != null && !idParam.equals("")) {
            try {
                userId = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect user Id!");
                e.printStackTrace();
            }
        }
        User user = userDAO.loadUserById(userId);
        request.setAttribute("user", user);
        UserGroup userGroup = userGroupDAO.loadUserGroupById(user.getGroup_id());
        request.setAttribute("groupname", userGroup.getName());
        List<LastSolution> usersSolutions = lastSolutionDAO.loadMostRecentSolutionsByUserId(userId);
        request.setAttribute("userssolutions", usersSolutions);
        getServletContext()
                .getRequestDispatcher("/jsp/userdetailsview.jsp").forward(request, response);
        response.getWriter().append("")
                .append(String.valueOf(userId)).append(request.getContextPath());
    }
}