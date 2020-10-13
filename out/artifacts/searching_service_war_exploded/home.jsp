<%@ page import="kz.bitlab.course.db.Hotel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container mt-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/home">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">All Hotels</li>
        </ol>
    </nav>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <%
                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                String success = request.getParameter("success");
                if (success!=null){
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Hotel  edited successfuly!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String delete = request.getParameter("delete");
                if (delete!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Hotel  deleted successfuly!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                ArrayList<Hotel> allHotels = (ArrayList<Hotel>)request.getAttribute("hotels");
                if (allHotels!=null){
                    for (Hotel h: allHotels){
            %>
            <div class="jumbotron">
                <h1 class="display-4"><%=h.getName()%></h1>
                <p class="lead">For <b><%=h.getPrice()%><span>&#36;</span></b></p>
                <hr class="my-4">
                <p class="lead float-right ">Posted by <b><%=h.getAuthor().getFullName()%></b> at <b><%=format.format(h.getAdddedDate())%></b></p>
                <br>
                <br>
                <p class="lead float-right">
                    <a class="btn btn-primary btn-lg" href="/details?id=<%=h.getId()%>" role="button">Learn more</a>
                </p>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>

