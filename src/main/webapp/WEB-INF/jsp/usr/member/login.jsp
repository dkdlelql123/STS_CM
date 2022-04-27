<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="로그인" />
<%@ include file="../common/head.jspf" %>
  
<div class="flex-grow">
  
  <div class="w-3/4 m-auto mt-12"> 
    
    <form action="/usr/member/doLogin" method="POST" id="loginForm" class="w-3/4 m-auto" >
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1">
        <span class="w-1/4 text-center p-1 " >아이디</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="text" id="loginId" name="loginId"  placeholder="아아디를 입력해주세요."/>
      </div>
       <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >비밀번호</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="password" id="loginPw" name="loginPw"  placeholder="비밀번호를 입력해주세요."/>
      </div>
      <c:if test="${loginAlert}">
        <div class="text-center py-4 text-red-600">${loginAlert}</div>
      </c:if>
      <div class="flex mt-2">
        <input class="flex-grow bg-gray-700 text-white fontM rounded rounded-full p-2" type="submit" value="로그인" >
      </div>
    </form>
  </div>
  
</div>



<%@ include file="../common/tail.jspf" %>  