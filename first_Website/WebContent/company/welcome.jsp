<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%--상단 공통 헤더영역 외부 포함파일 처리 => 중복코드를 안하는 효과가 발생. (사이트 로딩 실행속도가 빨라지고, 유지보수에 유리)
액티브 서브 페이지에서는 해당 코드와 비슷하게 들어감--%>

<%--서브 메인 이미지--%>
<div id="sub_img"></div>
<div class="clear"></div>

<%-- 서브 메뉴 --%>
<div id="sub_menu">
<ul>
<li><a href="welcome.jsp">Welcome</a></li>
<li><a href="history.jsp">History</a></li>
<li><a href="#">NewsRoom</a></li>
<li><a href="#">Public Policy</a></li>
</ul>
</div>

<%-- 서브메인 본문 --%>
<article id="sub_main_cont">
<h1>Welcome</h1>
    <figure class="ceo"><img src="../Images/company/CEO.jpg" width="196" height="226" alt="CEO" />
    <%-- figure는 html5에서 추가된 태드로 그림삽입해서 표현할 때 사용한다. 
    figcaption도 html5에서 추가된 태그로 그림에 대한 부연설명을 설정할 때 사용한다. --%>
    <figcaption>Fun Web CEO Michael</figcaption></figure>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam bibendum, libero faucibus porttitor bibendum, 
    lorem purus sollicitudin lacus, ut laoreet sem dui non nibh. Fusce tempor, eros vulputate luctus malesuada, 
    nunc nisi rutrum massa, vestibulum rhoncus metus tellus in eros. Ut vehicula luctus cursus. Aliquam nunc neque, 
    condimentum eu fringilla quis, feugiat in erat. Aliquam vel neque dolor, eu porttitor nulla. Cras ac dui metus, 
    non viverra ligula. Maecenas sed tortor justo. Duis vehicula nisl sed sapien egestas ut aliquet purus mattis. 
    Vestibulum vitae libero sapien, sed ultricies lacus. Morbi sapien metus, ullamcorper rhoncus cursus in, facilisis
     id felis. Aliquam id elit lacus, at laoreet enim. Nullam quis purus dui, eu molestie augue.</p>
    <p>Nam venenatis neque quis tortor laoreet egestas hendrerit id diam. Nam adipiscing urna non tortor ornare vel
     consectetur est cursus. Morbi sed libero quam, sed scelerisque ligula. Vivamus vitae massa in quam hendrerit 
     porttitor. Vivamus ac feugiat odio. In hac habitasse platea dictumst. Proin porta tempus ligula ut eleifend. 
     Maecenas tincidunt elementum purus, nec venenatis nisl sodales vel. Mauris at neque nisi, nec tincidunt nulla. 
     Mauris porta, nunc et semper lobortis, sem erat tempus massa, at porttitor dolor mi non sem.</p>
    <p>Phasellus lectus elit, sagittis quis aliquet vulputate, consectetur ac velit. Mauris molestie, quam et ultrices 
    fringilla, lectus magna auctor nisl, a congue tortor nisi eu magna. Phasellus eleifend tristique sapien sed eleifend. 
    Nunc nunc sem, interdum ac mattis in, mollis ut felis. Aenean varius, ligula id consequat condimentum, diam mauris
     ullamcorper arcu, ut porttitor libero neque sit amet velit. Ut gravida ultricies est, non mollis massa pharetra imperdiet.
      Duis commodo libero eget massa tempor eget lacinia mi elementum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
      Pellentesque at leo et massa euismod bibendum. Mauris consectetur dictum orci pretium hendrerit. Integer ipsum ligula, 
      fringilla nec iaculis nec, ultrices eu diam. Nunc tellus nulla, fringilla eu tempor ac, commodo at augue. Vestibulum vel congue tellus.</p>
</article>

<%--하단 공통페이지--%>
<jsp:include page="../include/footer.jsp" /><%--하단 공통페이지를 jsp:include 액션태그로 처리 jsp에서는 해당 코드를 자주사용함.--%>
