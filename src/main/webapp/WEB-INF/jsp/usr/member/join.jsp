<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="회원가입" />
<%@ include file="../common/head.jspf" %>
  
<script>
let doJoin__submitDone = false;
function doJoin__submit(form){
	if(doJoin__submitDone) return;
  
   if(form.loginId.value.trim().length == 0){
        alert("아이디를 입력해주세요.")
        form.loginId.focus();
        return;
      }
   
   if(form.loginPw.value.trim().length == 0){
        alert("비밀번호를 입력해주세요.")
        form.loginPw.focus();
        return;
      }
   
   if(form.name.value.trim().length == 0){
        alert("이름을 입력해주세요.")
        form.name.focus();
        return;
      }
	 
   if(form.nickname.value.trim().length == 0){
      alert("이름을 입력해주세요.")
      form.nickname.focus();
      return;
    }
   
   if(form.email.value.trim().length == 0){
       alert("이메일을 입력해주세요.")
       form.email.focus();
       return;
     }
	 
  if(form.phoneNumber.value.trim().length == 0){
     alert("휴대전화번호를 입력해주세요.")
     form.phoneNumber.focus();
     return;
   }
	
	doJoin__submitDone = true;
	form.submit();
}
</script>

<div class="flex-grow">
  <div class="w-3/4 m-auto mt-12"> 
    
    <form action="/usr/member/doJoin" method="post" onsubmit="doJoin__submit(this); return false;" class="w-3/4 m-auto" >
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1">
        <span class="w-1/4 text-center p-1 " >아이디</span> 
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="text" id="loginId" name="loginId"  
         placeholder="비밀번호를 입력해주세요."/>
      </div>
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >비밀번호</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="password" id="loginPw" name="loginPw"  
         placeholder="비밀번호를 입력해주세요."/>
      </div> 
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >이름</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="text" id="name" name="name" 
        placeholder="이름을 입력해주세요."/>
      </div>
        <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >별명</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="text" id="nickname" name="nickname" 
        placeholder="이름을 입력해주세요."/>
      </div>
      <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >Email</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="email" id="email" name="email" 
        placeholder="이메일을 입력해주세요."/>
      </div>
     <div class="flex bg-gray-200 rounded rounded-full items-center p-1 mt-2">
        <span class="w-1/4 text-center p-1 " >휴대전화번호</span>
        <input class="flex-grow bg-gray-200 rounded rounded-full p-1" type="tel" id="phoneNumber" name="phoneNumber" 
        placeholder="휴대전화번호을 입력해주세요."/>
      </div>
   
      <div class="flex mt-2">
        <input class="flex-grow bg-gray-700 text-white fontM rounded rounded-full p-2" type="submit" value="회원가입" >
      </div>
    </form>
  </div>
</div>

<%@ include file="../common/tail.jspf" %>  