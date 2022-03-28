<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 작성" />
<%@ include file="../common/head.jspf" %>
  
<div class="flex-grow">
  
  <div class="w-3/4 mx-auto"> 
       
    <div class="flex items-center gap-1 my-4">
      <a href="#" onClick="history.go(-1);">뒤로가기</a>
    </div>
       
     <form action="/usr/article/doWrite" method="post">
      <table class="table border border-1 flex w-full">
      <colgroup>
        <col width="150"><col>
      </colgroup> 
       <tr class="border-b border-gray-100">
          <td class="text-center py-2">
            작성자
          </td>
          <td>
            ${rq.loginedMember.nickname}
          </td>
        </tr>
        <tr class="border-b border-gray-100">
          <td class="text-center py-2">
            제목
          </td>
          <td>
            <input type="text" id="title" name="title" class="w-full p-1 bg-gray-100 my-1" placeholder="제목을 입력해주세요."/>
          </td>
        </tr>
        <tr>
          <td class="text-center py-2">
            내용
          </td>
          <td>
            <textarea id="body" name="body" class="w-full p-1 bg-gray-100 my-1" rows=5 placeholder="내용을 입력해주세요."></textarea>
          </td>
        </tr>
      </table> 
      
      <div class="flex items-center my-4 justify-center">  
        <button type="input">저장</button>
      </div>
     
    </form>
  </div>
</div>
<%@ include file="../common/tail.jspf" %>  