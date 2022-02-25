<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="ERROR" />
<%@ include file="./common/head.jspf" %>
  
<div class="flex-grow">
  
  <div class="w-3/4 mx-auto">
    <h1>ERROR</h1>
    <div>${error}</div>
  </div>
  
</div>
    
<%@ include file="./common/tail.jspf" %>  