<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Bags</title>
</head>
<jsp:include page="menu.jsp" />
<body>

<div class="col-10 offset-1">
    <button type="button" class="btn btn-success btn-lg my-2 col-2 offset-5" data-toggle="modal" data-target="#exampleModalCenter">Create new</button>

    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Add new bag</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form method="POST" action="/bags/create">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="brand" class="col-form-label">Brand:</label>
                            <input type="text" name="brand" class="form-control" id="brand">
                        </div>
                        <div class="form-group">
                            <label for="liters" class="col-form-label">Liters:</label>
                            <input type="number" name="liters" class="form-control" id="liters">
                        </div>
                        <div class="form-group">
                            <label for="price" class="col-form-label">Price:</label>
                            <input type="text" name="price" class="form-control" id="price">
                        </div>
                        <div class="form-group">
                            <label for="pocket" class="col-form-label">Count of pockets:</label>
                            <input type="text" name="countOfPockets" class="form-control" id="pocket">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button class="btn btn-primary"  type="submit">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <table class="table table-striped table-dark">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Brand</th>
            <th scope="col">Liters</th>
            <th scope="col">Price</th>
            <th scope="col">Count of pockets</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach  items="${bags}" var ="bag">
            <tr>
                <th scope="row">${bag.id}</th>
                <td>${bag.brand}</td>
                <td>${bag.liters}</td>
                <td>${bag.price}</td>
                <td>${bag.countOfPockets}</td>
                <td>
                    <div>
                        <a type="button" class="btn btn-warning" href="/bags/${bag.id}" style="margin-right: 10px">Detail</a>
                        <a type="button" class="btn btn-danger" href="/bags/delete/${bag.id}" >Delete</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
