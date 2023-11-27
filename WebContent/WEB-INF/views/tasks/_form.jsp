<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<label for="content_task">タスク</label><br/>
<input type="text" name="content" id="content_task" value="${task.content}" />
<br/><br/>

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>