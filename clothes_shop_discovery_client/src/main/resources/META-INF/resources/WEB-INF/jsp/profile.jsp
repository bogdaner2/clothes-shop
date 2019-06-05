<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Coats</title>
</head>
<jsp:include page="menu.jsp" />
<body>
<div class="container-fluid">
 <div class="row">
  <form method="POST" action="${contextPath}/userProfile/update/${profile.id}"  style="max-width: 600px;padding-left: 0;margin-top: 30px;" class="col-7 offset-4">
   <h2 class="form-heading">Edit profile</h2>

   <div class="form-group">
    <label for="name">Input name:</label>
    <input name="name" id="name" type="text" class="form-control" placeholder="${profile.name}"/>
    <br>
    <label for="email">Input email:</label>
    <input name="email" id="email" type="email" class="form-control" placeholder="${profile.email}"/>
    <br>
    <label for="phone">Input phone:</label>
    <input name="phone" id="phone" type="text" class="form-control" placeholder="${profile.phone}"/>
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Change</button>
   </div>
  </form>
 </div>
 <div class="row">
   <div style="max-width: 600px;padding-left: 0;" class="col-7 offset-4">
    <button class="btn btn-lg btn-danger btn-block" onclick="window.location.href='/userProfile/delete/${profile.id}'" >Delete account</button>
   </div>
 </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
