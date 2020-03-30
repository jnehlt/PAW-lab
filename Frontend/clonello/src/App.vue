<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg">
      <a class="navbar-brand" href="#"><i class="fas fa-thumbtack"></i> Clonello</a>
      <button class="navbar-toggler navbar-dark" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="#">Home <i class="fas fa-home"></i></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Tablice</a>
          </li>
          <li class="nav-item">
            <form class="form-inline my-2 my-lg-0">
              <input class="form-control mr-sm-2" type="search" placeholder="Szukaj" aria-label="Search">
              <button class="btn btn-success my-2 my-sm-0" type="submit">Szukaj</button>
            </form>
          </li>
        </ul>
        <ul class="navbar-right navbar-nav">
          <li>
            <button class="btn btn-success" type="button" data-toggle="collapse" data-target="#collapseAdd" aria-expanded="false" aria-controls="collapseAdd">Dodaj <i class="fas fa-plus"></i>
            </button>
            <div class="dropdown-menu dropdown-menu-right" id="collapseAdd">
              <a class="dropdown-item" href="#">Tablicę</a>     
              <a class="dropdown-item" href="#">Zespół</a>
            </div>
          </li>
          <li>
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseProfile" aria-expanded="false" aria-controls="collapseProfile">
                    Profil <i class="far fa-user"></i></button>
            <div class="dropdown-menu dropdown-menu-md-left dropdown-menu-lg-right" id="collapseProfile">
              <h5 class="dropdown-header">Profile name</h5>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </li>
        </ul>
      </div>
    </nav>
    <div class="navbar navbar-expand-lg">
      <ul class="nav nav-pills nav-fill">
        <li class="nav-item">
          <a class="nav-link active" href="#">Projekt</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" onclick="testingFunction()">Kliknij mnie!</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" onclick="testingFunction()">Nie słuchaj go, mnie kliknij!</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Albo zobacz menu</a>
          <div class="dropdown-menu">
            <h5 class="dropdown-header">Menu</h5>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">No dobra</a>
            <a class="dropdown-item" href="#">Trochę zamieszania</a>
            <a class="dropdown-item" href="#">Dziwnie wyszło</a>
            <a class="dropdown-item" href="#">Ale klinkij tamte</a>
          </div>
        </li>
      </ul>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-4 col-md-4 col-sm-4">
          <ul class="list-group">
            <li class="list-group-item disabled list-head" aria-disabled="true">Kategoria<button class="btn btn-kropki"><i class="fas fa-ellipsis-v"></i></button></li>
            <li class="list-group-item">Nazwa zadania</li>
            <li class="list-group-item">Kolejne zadanie</li>
            <li class="list-group-item">I jeszcze jedno</li>
            <li class="list-group-item">I tak w kółko Macieju</li>
          </ul>
        </div>
        <div class="col-4 col-md-4 col-sm-4">
          <ul class="list-group">
            <li class="list-group-item disabled list-head" aria-disabled="true">Kategoria<button class="btn btn-kropki"><i class="fas fa-ellipsis-v"></i></button></li>
            <li class="list-group-item">Nazwa zadania</li>
            <li class="list-group-item">Kolejne zadanie</li>
            <li class="list-group-item">I jeszcze jedno</li>
            <li class="list-group-item">I tak w kółko Macieju</li>
          </ul>
        </div>
        <div class="col-4 col-md-4 col-sm-4">
          <ul class="list-group">
            <li class="list-group-item disabled list-head" aria-disabled="true">Kategoria<button class="btn btn-kropki"><i class="fas fa-ellipsis-v"></i></button></li>
            <li class="list-group-item">Nazwa zadania</li>
            <li class="list-group-item">Kolejne zadanie</li>
            <li class="list-group-item">I jeszcze jedno</li>
            <li class="list-group-item">I tak w kółko Macieju</li>
          </ul>
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>

<style lang="scss">
body {
    background-image: url("../src/assets/running-track-sn-3000x2001.jpg");
    background-size: cover;
    background-position: center;
}

.navbar-brand {
    font-weight: bolder;
    font-size: 2em;
}

.dropdown-header {
    font-size: larger;
    font-weight: bolder;
}

html {
    height: 100%;
}

.list-head {
    font-weight: bolder;
    text-transform: uppercase;
}

.navbar {
    background-color: transparent;
}

.navbar-toggler {
    background-color: #4e555b;
}

.btn {
    margin-left: 2px;
}

.container {
    margin-top: 30px;
}


</style>
