<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Brands</title>
</head>
<jsp:include page="../menu.jsp" />
<body>

<div class="col-10 offset-1">
    <button type="button" class="btn btn-success btn-lg my-2 col-2 offset-5" data-toggle="modal" data-target="#exampleModalCenter">Create new</button>

    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Add new brand</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form method="POST" action="/shopProducts/create">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="shop" class="col-form-label">Shops :</label>
                            <select class="form-control" id="shop" name="shopId">
                                <c:forEach  items="${shops}" var="shop">
                                    <option value="${shop.id}">${shop.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="product" class="col-form-label">Product :</label>
                            <select class="form-control" id="product" name="productId">
                                <c:forEach  items="${products}" var="product">
                                    <option value="${product.id}">${product.brand.name} - ${product.type} - ${product.price}</option>
                                </c:forEach>
                            </select>
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
            <th scope="col">Product</th>
            <th scope="col">Shop</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach  items="${shopProducts}" var ="shopProduct">
            <tr>
                <th scope="row">${shopProduct.id}</th>
                <td>${shopProduct.product.brand.name} - ${shopProduct.product.price}$</td>
                <td>${shopProduct.shop.name}</td>
                <td>
                    <div>
                        <button
                            class="btn btn-warning"
                            onclick="window.location.href='/shopProducts/${shopProduct.id}'"
                            style="margin-right: 10px">Detail</button>
                        <button
                            style="display: ${allowDelete ? 'inline-block' : 'none'}"
                            class="btn btn-danger"
                            onclick="window.location.href='/shopProducts/delete/${shopProduct.id}'" >Delete</button>
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
