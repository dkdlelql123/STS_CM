<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="홈" />
<%@ include file="../common/head.jspf" %>
  
<div class="flex-grow">
  
  <div class="w-3/4 mx-auto">
    <h1>Hello.</h1>
    
    <ul>
      <li>
        <a href="/usr/articles">게시물 전체 목록</a>
      </li>
      <li>
        <a href="/usr/article/write">게시물 글쓰기</a>
      </li>
      <li>
        <a href="">회원 전체 목록</a>
      </li>
      <li>
        <a href=""></a>
      </li>
    </ul>
  </div>
  
</div>

<%@ include file="../common/tail.jspf" %>  