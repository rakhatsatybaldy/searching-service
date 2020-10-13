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
            <li class="breadcrumb-item"><a href="/login">Login Page</a></li>
            <li class="breadcrumb-item active" aria-current="page">Login Page</li>
        </ol>
    </nav>
</div>
<div class="container mt-3">
    <div class="row">
        <div class="col-sm-6 offset-3">
            <%
                String emailError = request.getParameter("usererror");
                String email  = request.getParameter("email");
                if (emailError!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                User with email "<%=email%>" doesn't exist!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String asd = request.getParameter("forthis");
                if (asd!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                You can't delete current comment! For this, log in system by this user.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String passError = request.getParameter("passworderror");
                if (passError!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Password is not correct!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String logout = request.getParameter("logout");
                if (logout!=null){
            %>
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                You logged out!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String mustlog = request.getParameter("mustlog");
                if (mustlog!=null){
            %>
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                Firstly , log in system!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <form action="/login" method="post">
                <div class="form-group">
                    <label>
                        EMAIL :
                    </label>
                    <input type="email" name="email" class="form-control">
                </div>
                <div class="form-group">
                    <label>
                        PASSWORD :
                    </label>
                    <input type="password" name="password" class="form-control">
                </div>
                <div class="form-group">
                    <button class="btn btn-success">SIGN IN</button>
                </div>
            </form>
            <div style="display: flex">
                <p>Don't have account? <li class="breadcrumb-item"><a href="/register">Register here!</a></li></p>
            </div>
        </div>

    </div>
</div>
</body>
</html>

