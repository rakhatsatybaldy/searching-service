<%@ page import="kz.bitlab.course.db.Hotel" %>
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


            <form action="/savehotel" method="post">
                <%
                    Hotel hotel = (Hotel) request.getAttribute("hotel");
                    if (hotel!=null){
                %>
                <input type="hidden" name="id" value="<%=hotel.getId()%>">
                <div class="form-group">
                    <label>
                        NAME:
                    </label>
                    <input type="text" name="name" class="form-control" value="<%=hotel.getName()%>">
                </div>
                <div class="form-group">
                    <label>
                        DESCRIPTION:
                    </label>
                    <textarea name = "description" class="form-control"><%=hotel.getDescription()%></textarea>
                </div>
                <div class="form-group">
                    <label>
                        STARS
                    </label>
                    <select class="form-control" name="stars">
                        <option <%if (hotel.getStars()==1){ out.print("selected");}%>>1</option>
                        <option <%if (hotel.getStars()==2){ out.print("selected");}%>>2</option>
                        <option <%if (hotel.getStars()==3){ out.print("selected");}%>>3</option>
                        <option <%if (hotel.getStars()==4){ out.print("selected");}%>>4</option>
                        <option <%if (hotel.getStars()==5){ out.print("selected");}%>>5</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="number" name="price" min ="0" class="form-control" value="<%=hotel.getPrice()%>">
                </div>
                <div class="form-group">
                    <button class="btn btn-success" >SAVE</button>
                    <button type="button" class="btn btn-danger ml-2" data-toggle="modal" data-target="#staticBackdrop">DELETE</button>
                </div>
            </form>
            <!-- Modal -->
            <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="/deletehotel" method="post">
                            <input type="hidden" name="id" value="<%=hotel.getId()%>">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Are you sure?</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Do you want to delete hotel?
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
            %>
        </div>
    </div>
</div>
</body>
</html>
