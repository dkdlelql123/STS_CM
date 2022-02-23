<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 목록" />
<%@ include file="../common/head.jspf" %>
  
<table>
  <thead>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>시간</th>
  </tr>
  </thead>
  <tbody>
    <c:forEach var="article" items="${aritcles}">  
      <tr>
        <td>${article.id} </td>
        <td>${article.title} </td>
        <td>${article.regDate} </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="../common/tail.jspf" %>  