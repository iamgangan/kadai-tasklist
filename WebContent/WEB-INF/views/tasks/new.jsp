<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <form method="POST" action="${pageContext.request.contextPath}/create">
        <c:import url="./_form.jsp" />
        </form>

    </c:param>
</c:import>