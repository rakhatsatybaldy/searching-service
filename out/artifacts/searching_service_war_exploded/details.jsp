<%@ page import="kz.bitlab.course.db.Hotel" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.course.db.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
</head>
<body class="mb-5 pd-5">
<%@include file="navbar.jsp"%>
<div class="container mt-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/home">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Hotel</li>
        </ol>
    </nav>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <%
                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                Hotel hotel = (Hotel) request.getAttribute("hotel");
                if (hotel!=null){
                    if (currentUser!=null && currentUser.getId()==hotel.getAuthor().getId()){

            %>
            <div class="jumbotron">
                <h1 class="display-4"><%=hotel.getName()%> - <%=hotel.getStars()%> stars</h1>
                <p class="lead">For <%=hotel.getPrice()%> <b><span>&#36;</span></b></p>
                <hr class="my-4">
                <p><%=hotel.getDescription()%></p>
                <p class="lead float-left ">Posted by <b><%=hotel.getAuthor().getFullName()%></b> at <b><%=format.format(hotel.getAdddedDate())%></b></p>

                <p class="lead">
                    <a class="btn btn-outline-dark btn-lg float-right" href="/edithotel?id=<%=hotel.getId()%>" role="button">Edit</a>
                </p>
                <p class="lead">
                    <a class="btn btn-outline-dark btn-lg float-right" href="/home" role="button">Back</a>
                </p>
            </div>
            <%
            }
                else {
            %>
            <div class="jumbotron">
                <h1 class="display-4"><%=hotel.getName()%> - <%=hotel.getStars()%> stars</h1>
                <p class="lead">For <%=hotel.getPrice()%> <b><span>&#36;</span></b></p>
                <hr class="my-4">
                <p><%=hotel.getDescription()%></p>
                <hr class="my-4">
                <p class="lead float-left ">Posted by <b><%=hotel.getAuthor().getFullName()%></b> at <b><%=format.format(hotel.getAdddedDate())%></b></p>
                <p class="lead float-right">
                    <a class="btn btn-outline-dark btn-lg" href="/home" role="button">Back</a>
                </p>
            </div>
            <%
                }
                }
                if (currentUser!=null){

            %>
            <div class="row mt-5" id="addCommentDiv">
                <div class="col-sm-12">
                    <form action="/addcomment" method="post">
                        <input type="hidden" name="hotel_id" value="<%=hotel.getId()%>">
                        <textarea class="form-control" rows="3" placeholder="Write a comment..." name="comment"></textarea>
                        <button class="btn btn-success btn-sm float-right mt-1">ENTER</button>
                    </form>
                </div>
            </div>

            <h4>Comments</h4>
            <br>
            <br>
            <%
                String success = request.getParameter("success");
                if (success!=null){
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Comment deleted!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>

            <%
                ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");


                if (comments!=null){
                    for (Comment c:comments){
            %>

            <div class="list-group">
                <a href="JavaScript:void(0)" data-toggle="modal" data-target="#staticBackdrop" class="list-group-item list-group-item-action">

                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1"><%=c.getUser().getFullName()%></h5>
                        <small class="text-muted"><%=format.format(c.getAddedDate())%></small>
                    </div>
                    <p class="mb-1"><%=c.getComment()%></p>

                </a>
            </div>

            <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="/deletecomment" method="post">
                            <input type="hidden" name="comment_id" value="<%=c.getId()%>">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Are you sure?</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Do you want to delete comment?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                <button class="btn btn-primary">Yes</button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
            <%
                }
                }
                }else {
            %>
            <h4>Comments</h4>
            <br>
            <br>

            <%
                ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");


                if (comments!=null){
                    for (Comment c:comments){
            %>

            <div class="list-group">
                <a href="JavaScript:void(0)"  class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1"><%=c.getUser().getFullName()%></h5>
                        <small class="text-muted"><%=format.format(c.getAddedDate())%></small>
                    </div>
                    <p class="mb-1"><%=c.getComment()%></p>

                </a>
            </div>
            <%
                }
                }
            %>
            <%
                }
            %>


        </div>
    </div>
</div>
</body>
</html>
