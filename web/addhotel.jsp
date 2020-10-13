<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
    <script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
    <script>
        tinymce.init({
            selector:'textarea'
        });
    </script>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container mt-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/home">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Add Hotel Page</li>
        </ol>
    </nav>
</div>
<div class="container mt-3">
    <div class="row">
        <div class="col-sm-8 offset-2">
            <%
                String success = request.getParameter("success");
                if (success!=null){
            %>
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                Hotel added successfully!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <form action="/addhotel" method="post">
                <div class="form-group">
                    <label>
                        NAME:
                    </label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>
                        DESCRIPTION:
                    </label>
                    <textarea name = "description" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <label>
                        STARS
                    </label>
                    <select class="form-control" name="stars" required>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>
                        PRICE FOR DAY:
                    </label>
                    <input type="number" name="price" min ="0" class="form-control" required>
                </div>
                <div class="form-group">
                    <button class="btn btn-success">ADD HOTEL</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
