<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Medical Clinic</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
</head>
<body>
<div id="main_container">
  <div class="header">
    <div id="logo"><a href="#"><img src="images/logo.png" alt="" width="162" height="54" border="0" /></a></div>
    <div class="right_header">
      <div class="top_menu"> <a href="vendorLogout.do" class="login">log out</a> <a href="#" class="sign_up">report</a> </div>
      <div id="menu">
        <ul>
          <li><a href="vendorHome.jsp">Home</a></li>
          <li><a href="aboutUs.html">About Us</a></li>
          <li><a href="services.html">Services</a></li>
          <li><a href="contactUs.html">Contact Us</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div id="middle_box">
    <div class="middle_box_content"><img src="images/banner_content.jpg" alt="" /></div>
  </div>
    <div class="pattern_bg">
    <div class="pattern_box">
      <div class="pattern_box_icon"><img src="images/icon1.png" alt="" width="70" height="112" /></div>
      <div class="pattern_content">
        <h1>Project<span class="blue"> Vision</span></h1>
        <p class="pat">Project Mission is to Touch Billion of Lives.</p>
      </div>
    </div>
    <div class="pattern_box">
      <div class="pattern_box_icon"><img src="images/icon2.png" alt="" width="70" height="112" /></div>
      <div class="pattern_content">
        <h1> Mission <span class="blue">Statement</span></h1>
        <p class="pat"> "Our mission is to bring healthcare of International standards within the reach of every individual. We are
committed to the achievement and maintenance of excellence in education, research and healthcare for the
benefit of humanity" </p>
      </div>
    </div>
  </div>
  <div id="main_content">
    <div class="box_content">
      <div class="box_title">
        <div class="title_icon"><img src="images/mini_icon1.gif" alt="" /></div>
        <h2><span class="dark_blue">Order id</span></h2></div>
      <div class="box_text_content">
        <div class="box_text"><h3>
        	<table>
        		<c:forEach var="admin_id" items="${admin_order_ids}">
        		<tr>
        			<td>${admin_id.aord_id}</td>
        		</tr>
        		</c:forEach>
        	</table>
        </div>
      </div>
      
    </div>
    <div class="box_content">
      <div class="box_title">
        <div class="title_icon"><img src="images/mini_icon2.gif" alt="" /></div>
        <h2><span class="dark_blue">Approve</span></h2></div>
      <div class="box_text_content"> 
        <div class="box_text"><br /><table>
        		<c:forEach var="order_id" items="${admin_order_ids}">
        		<tr>
        			<td><img src="images/checked.gif" alt="" class="box_icon" /></td><td><a href="approveAdminOrder.do?order_id=${order_id.aord_id}" style="color: teal;">Approve</a></td>  <td><a href="rejectAdminOrder.do?order_id=${order_id.aord_id}" style="color: teal;">Reject</a></td>
        		</tr>
        		</c:forEach>
        	</table></div>
      </div>
    </div>
    <div class="box_content">
      <div class="box_title">
        <div class="title_icon"><img src="images/mini_icon3.gif" alt="" /></div>
        <h2><span class="dark_blue">View details</span></h2>
      </div>
      <div class="box_text_content">
        <div class="box_text"><br /><table>
        		<c:forEach var="order_id" items="${admin_order_ids}">
        		<tr>
        			<td><img src="images/checked.gif" alt="" class="box_icon" /></td><td><a href="adminOrderStatusDetails.do?ord_id=${order_id.aord_id}" style="color: teal;">View details</a></td>
        		</tr>
        		</c:forEach>
        	</table></div>
      </div>
    </div>
    <div class="clear"></div>
  </div>
</div>
</body>
</html>
