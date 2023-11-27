package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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

            //更新対象のタスクの取得
            int id;
            try {
                id  = (Integer)request.getSession().getAttribute("task_id");
            } catch(NumberFormatException e) {
                e.printStackTrace();
                em.close();
                return;
            }
            Task task = em.find(Task.class, id);

            //タスク内容の更新
            String content = request.getParameter("content");
            task.setContent(content);

            Timestamp updated_at =  new Timestamp(System.currentTimeMillis());
            task.setUpdated_at(updated_at);

            //DB上のデータをアップデート
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            //セッションスコープのタスクIDを削除
            request.getSession().removeAttribute("task_id");

            //一覧画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");

        }
    }

}
