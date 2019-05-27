<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
<form method="POST" action="${contextPath}/registration"  style="max-width: 600px;padding-left: 0;margin-top: 30px;" class="col-7 offset-4">
        <h2 class="form-heading">Registration</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <label for="username">Input username:</label>
            <input name="username"  id="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <br>
            <label for="password">Input password:</label>
            <input name="password" id="password" type="password" class="form-control" placeholder="Password"/>
            <span>${errorMsg}</span>
            <br>
            <div class="form-group">
                <label for="role">Select role:</label>
                <select name="role" class="form-control" id="role">
                    <option>Admin</option>
                    <option>User</option>
                </select>
            </div>
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </div>
    </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
