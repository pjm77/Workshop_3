package pl.pjm77.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.pjm77.DAO.UserGroupDAO;
import pl.pjm77.misc.ValPar;
import pl.pjm77.model.UserGroup;

@WebServlet("/addeditgroup")
public class UserGroupsAdminAddEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserGroupsAdminAddEdit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int groupId = ValPar.intVar(idParam, "Incorrect group Id!");
        if(groupId == 0) {
            request.setAttribute("groupId", groupId);
            request.setAttribute("groupName", null);
            request.setAttribute("button", "Add group");
            getServletContext().getRequestDispatcher("/jsp/usergroupsadminaddeditview.jsp").forward(request, response);
        }else if(groupId > 0){
            UserGroup userGroup = new UserGroupDAO().loadUserGroupById(groupId);
            request.setAttribute("groupId", groupId);
            request.setAttribute("groupName", userGroup.getName());
            request.setAttribute("button", "Edit group");
            getServletContext().getRequestDispatcher("/jsp/usergroupsadminaddeditview.jsp").forward(request, response);
        }else {
            getServletContext().getRequestDispatcher("/groupsadminpanel").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String groupName = request.getParameter("name");
        int groupId = ValPar.intVar(idParam, "Incorrect group Id!");
        if(groupName!=null && !groupName.equals("") && groupId >= 0) {
            UserGroupDAO userGroupDAO = new UserGroupDAO();
            UserGroup userGroup = new UserGroup();
            if(groupId!=0) {
                userGroup = new UserGroupDAO().loadUserGroupById(groupId);
                userGroup.setName(groupName);
            }else {
                userGroup.setName(groupName);
            }
            userGroupDAO.saveUserGroupToDB(userGroup);
        }else{
            request.setAttribute("errorMessage", "Group name can't be empty!"); // detect empty fields and pass error message
        }
        getServletContext().getRequestDispatcher("/groupsadminpanel").forward(request, response);
    }
}