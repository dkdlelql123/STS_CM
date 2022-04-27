<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="회원정보 수정" />
<%@ include file="../common/head.jspf" %>
  
<script>
let doModify__submitDone = false;
function doModify__submit(form){
  if(doModify__submitDone) return ;
  
  let loginPwText = form.loginPw.value.trim();
  
  if(loginPwText.length > 0 ){ 

	  if(form.loginPw2.value.trim().length == 0){
		  alert("비밀번호 확인해주세요.")
		  form.loginPw2.focus();
		  return;
	  }
	  
	  if(loginPwText != form.loginPw2.value.trim()){
		  alert("비밀번호확인이 일치하지 않습니다.") 
		  return;
	  }
  } 
  
  if(form.name.value.trim().length == 0){
	  alert("이름을 작성해주세요.")
	  form.name.focus();
	  return;
  }
  
  if(form.nickname.value.trim().length == 0){
	  alert("별명을 작성해주세요.")
	  form.nickname.focus();
	  return;
  }
  
  if(form.phoneNumber.value.trim().length == 0){
	  alert("휴대전화번호를 작성해주세요.")
	  form.phoneNumber.focus();
	  return;
  }
  
  if(form.email.value.trim().length == 0){
	  alert("이메일을 작성해주세요.")
	  form.email.focus();
	  return;
  }
  
  doModify__submitDone = true;
  form.submit();
}
</script>
  
<div class="flex-grow"> 
  <div class="w-3/4 m-auto mt-12">  
    <form action="/usr/member/doModify" method="POST" class="w-3/4 m-auto"  onsubmit="doModify__submit(this); return false;">
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1">
        <span class="w-1/4 text-center p-1 " >아이디</span>
         ${rq.getLoginedMember().getLoginId()}
      </div>
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >비밀번호</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="password" id="loginPw" name="loginPw"  
        value="${rq.getLoginedMember().getLoginPw()}" placeholder="비밀번호를 입력해주세요."/>
      </div>
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >비밀번호 확인</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="password" id="loginPw2" name="loginPw2"  placeholder="비밀번호를 입력해주세요."/>
      </div>
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >이름</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="text" id="name" name="name" 
        value="${rq.getLoginedMember().getName()}" placeholder="이름을 입력해주세요."/>
      </div>
        <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >별명</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="text" id="nickname" name="nickname" 
        value="${rq.getLoginedMember().getNickname()}" placeholder="이름을 입력해주세요."/>
      </div>
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >Email</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="email" id="email" name="email" 
        value="${rq.getLoginedMember().getEmail()}" placeholder="이메일을 입력해주세요."/>
      </div>
     <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >휴대전화번호</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="tel" id="phoneNumber" name="phoneNumber" 
        value="${rq.getLoginedMember().getPhoneNumber()}" placeholder="휴대전화번호을 입력해주세요."/>
      </div>
   
      <div class="flex mt-2">
        <input class="flex-grow bg-gray-700 text-white fontM rounded rounded-full p-2" type="submit" value="회원 수정하기" >
      </div>
    </form>
  </div> 
</div>
 
<%@ include file="../common/tail.jspf" %>  