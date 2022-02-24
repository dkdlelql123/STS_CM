<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 상세" />
<%@ include file="../common/head.jspf" %>
  
<div class="container">
<div class="boardHd">
  <div class="title">${article.title}</div>
  <div>${article.memberId}</div>
  <div>${article.regDate}</div>
</div>
<div >
  <div>${article.body}</div>
</div>
</div>

<%@ include file="../common/tail.jspf" %>  