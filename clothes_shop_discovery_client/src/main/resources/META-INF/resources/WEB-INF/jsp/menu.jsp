<nav class="navbar navbar-expand-lg navbar-dark ${headerTitle}">
    <a class="navbar-brand" href="#">Clothes shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav mr-auto" >
            <a class="nav-item nav-link" href="/brands">Brands</a>
            <a class="nav-item nav-link" href="/products">Products</a>
            <a class="nav-item nav-link" href="/shops">Shops</a>
            <a class="nav-item nav-link" href="/shopProducts">Shop Products</a>
            <a style="display: ${isAdmin ? 'block': 'none'}" class="nav-item nav-link" href="/logs">Logs</a>
        </div>
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="/userProfile" style="margin-right: 25px">${name}</a>
            <a class="nav-item nav-link" href="/logout">Logout</a>
        </div>
    </div>
</nav>