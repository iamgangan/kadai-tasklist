package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //EntityManagerの取得
        EntityManager em = DBUtil.createEntityManager();

        //idの取得
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch(NumberFormatException e) {
            e.printStackTrace();
            em.close();
            return;
        }

        //編集対象のタスクの取得
        Task task = em.find(Task.class, id);
        em.close();

        //_tokenのセット
        request.setAttribute("_token", request.getSession().getId());

        //タスクが存在していた場合、セッションスコープにidをセット
        if (task != null) {
            request.getSession().setAttribute("task_id", task.getId());
        }

        //リクエストスコープにタスクをセットして画面遷移
        request.setAttribute("task", task);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
        rd.forward(request, response);
    }

}
