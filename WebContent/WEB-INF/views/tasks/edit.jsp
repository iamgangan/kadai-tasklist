<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>id : <c:out value="${task.id}" />のタスク編集ページ</h2>

        <form method="POST" action="${pageContext.request.contextPath}/update">
            <c:import url="/WEB-INF/views/tasks/_form.jsp"></c:import>
        </form>

        <a href="${pageContext.request.contextPath}/index">
            一覧に戻る
        </a>
        <br />
        <a href="${pageContext.request.contextPath}/destroy">
            このメッセージを削除する
        </a>

    </c:param>
</c:import>