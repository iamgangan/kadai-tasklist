<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>id : <c:out value="${task.id}" />のメッセージ詳細ページ</h2>

        <ul>
            <li>
                タスク : <c:out value="${task.content}" />
            </li>
            <li>
                作成日時 : <c:out value="${task.created_at}" />
            </li>
            <li>
                更新日時 : <c:out value="${task.updated_at}" />
            </li>
        </ul>

        <a href="${pageContext.request.contextPath}/index">
            一覧に戻る
        </a>
        <br />
        <a href="${pageContext.request.contextPath}/edit?id=${task.id}">
            このメッセージを編集する
        </a>

    </c:param>
</c:import>