<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.nyj.exam.demo.util.Util"%>

<c:set var="pageTitle" value="MYPAGE" />
<%@ include file="../common/head.jspf"%>

<div class="flex-grow">

  <div class="w-3/4 mx-auto flex">
    <div class="card card-side bg-base-100 shadow-xl">
      <div class="p-4 pr-0 flex flex-col items-center justify-center gap-2">
        <figure class="avatar">
          <div class="w-24 rounded-full" style="max-height:100">
            <img src="https://api.lorem.space/image/movie?w=100&h=100"alt="Movie">
          </div>
        </figure>
         <h2 class="font-bold">${member.loginId}</h2>
      </div>
      <div class="card-body size-sm">
        <p>Level : ${member.authLevel}</p>
        <p>📧 ${member.email}</p>
        <p>👤 ${member.name} (${member.nickname})</p>
        <p>📞 ${member.phoneNumber}</p>
        <div class="card-actions justify-end">
          <a href="/usr/member/checkPassword?replaceUri=${Util.getUriEncoded('/usr/member/modify')}" class="btn btn-xs btn-primary">회원정보수정</a>
        </div>
      </div>
    </div>
    
    <div class=""></div>  
  </div>

</div>

<%@ include file="../common/tail.jspf"%>
