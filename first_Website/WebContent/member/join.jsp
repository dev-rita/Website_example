<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="../include/header.jsp" />

<%--서브 메인 이미지 --%>
<div id="sub_img_member"></div>
<div id="clear"></div>

<%--서브 메뉴 --%>
<div id="sub_menu">
<ul>
<li><a href="join.jsp">Join Us</a></li>
<li><a href="#">Privacy policy</a></li>
</ul>
</div>

<%--서브 메인 본문 --%>
<article id="sub_main_cont">
<h1>Join Us</h1>
<form id="join">
<fieldset><!-- fieldset은 html5에서 추가된 태그로 폼 입력필드를 그룹화 시키고 그룹 경계선을 만든다. -->
<legend>Basic Info</legend><!--legend도 html5에서 추가된 태그로 그룹에 대한 제목을 지정한다. -->
<label>User ID</label><input type="text" class="id"/><input type="button" value="IDCheck" class="dup"/><br/>

<label>Password</label><input type="password" class="pass" /><br/>
<label>Retype Password</label><input type="password" class="pass"/><br/>

<label>Name</label><input type="text" class="nick"/><br/>
<label>E-mail</label><input type="email" class="email"/><br/>
<label>Retype E-mail</label><input type="email" class="email"><br/>
</fieldset>

<fieldset>
<legend>Optional</legend>

<label>Address</label><input type="text" class="address"/><br/>
<label>Tel Number</label><input type="tel" class="phone"/><br/>
<label>Mobile Phone Number</label><input type="tel" class="phone"/>
</fieldset>

<div class="clear"></div>

<div id="buttons">
<input type="button" value="Submit" class="submit"/><input type="button" value="Cancel" class="cancel"/>
</div>
</form>
</article>
<jsp:include page="../include/footer.jsp" />