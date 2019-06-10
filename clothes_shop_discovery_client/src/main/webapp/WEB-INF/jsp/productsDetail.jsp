<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>products</title>
</head>
<jsp:include page="menu.jsp" />
<body>

<div class="justify-content-center">
    <h1>Product Info</h1>
</div>
<div class="card mx-auto card-width">
    <form method="post" action="/products/update/${product.id}">
        <div class="card-heading p-2"><h4>Product id : ${product.id}</h4></div>
        <div class="card-heading p-2">
            <div class="card-heading p-2">
                Brand: ${product.brand.name}
                <br>
                <select placeholder="${product.brand.name}" class="form-control" name="brandId">
                    <c:forEach  items="${brands}" var="brand">
                        <option value="${brand.id}" ${brand.id == product.brand.id? 'selected' : ''}>${brand.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="card-heading p-2">
                Type: ${product.type}
                <br><input class="form-control"
                            name="type"
                            type="text"
                            placeholder="${product.type}"/>
            </div>
            <div class="card-heading p-2">
                Price: ${product.price}$
                <br><input class="form-control"
                       name="price"
                       type="number"
                       placeholder="${product.price}"/>
            </div>
        <div class="card-heading p-2 mx-auto" style="max-width: 70px">
            <button type="submit" class="btn btn-outline-secondary" >Update</button>
        </div>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
