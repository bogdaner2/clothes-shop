<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Brand Info</title>
</head>
<jsp:include page="../menu.jsp" />
<body>

<div class="justify-content-center">
    <h1>Shop Product Info</h1>
</div>
<div class="card mx-auto card-width">
    <form method="post" action="/shopProducts/update/${shopProduct.id}">
        <div class="card-heading p-2"><h4>Shop Product id : ${shopProduct.id}</h4></div>
        <div class="card-heading p-2">
            Shop: ${shopProduct.shop.name}
            <br>
            <select placeholder="${shopProduct.shop.name}" class="form-control" name="shopId">
                <c:forEach  items="${shops}" var="shop">
                    <option value="${shop.id}" ${shop.id == shopProduct.shop.id? 'selected' : ''}>${shop.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="card-heading p-2">
            Product: ${shopProduct.product.brand.name} - ${shopProduct.product.price}$
            <br>
            <select placeholder="${shopProduct.product.brand.name} - ${shopProduct.product.price}$" class="form-control" name="productId">
                <c:forEach  items="${products}" var="product">
                    <option value="${product.id}" ${product.id == shopProduct.product.id? 'selected' : ''}>${shopProduct.product.brand.name} - ${shopProduct.product.price}$</option>
                </c:forEach>
            </select>
        </div>
        <div class="card-heading p-2 mx-auto" style="max-width: 70px">
            <button type="submit" class="btn btn-outline-secondary" >Update</button>
        </div>
</form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
