package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      //_tokenのチェック
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {

            //EntityManagerの取得
            EntityManager em = DBUtil.createEntityManager();

            //削除対象タスクIDの取得
            int id;
            try {
                id  = (Integer)request.getSession().getAttribute("task_id");
            } catch(NumberFormatException e) {
                e.printStackTrace();
                em.close();
                return;
            }

            //削除対象タスクの取得
            Task task = em.find(Task.class, id);

            //DB上のデータを削除
            em.getTransaction().begin();
            em.remove(task);
            em.getTransaction().commit();
            em.close();

            //セッションスコープのタスクIDを削除
            request.getSession().removeAttribute("task_id");

            //一覧画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");

        }
    }

}
