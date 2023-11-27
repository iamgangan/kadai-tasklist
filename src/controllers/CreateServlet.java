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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //_tokenのチェック
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            //Taskインスタンスの用意
            Task task = new Task();

            String content = request.getParameter("content");
            task.setContent(content);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            task.setCreated_at(timestamp);
            task.setUpdated_at(timestamp);

            //EntityManagerの取得
            EntityManager em = DBUtil.createEntityManager();

            //データベースへ登録
            em.getTransaction().begin();
            em.persist(task);
            em.getTransaction().commit();
            em.close();

            //一覧表示画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");

        }

    }

}
