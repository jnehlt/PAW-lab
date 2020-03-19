<template>
  <div>
    <!DOCTYPE html>
    <html lang="en">
      <head>
        <meta charset="UTF-8">
        <title>Clonello</title>
      </head>
      <body>
        <nav class="navbar navbar-expand-lg">
          <a class="navbar-brand" href="#">Clonello</a>
          <button class="navbar-toggler navbar-dark" type="button" data-toggle="collapse" data-target="#navbarNav"
                  aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item">
                <a class="nav-link" href="#">Home</a>
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
                <button class="btn btn-success" type="button" data-toggle="collapse" data-target="#collapseAdd"
                        aria-expanded="false" aria-controls="collapseAdd">
                  Dodaj
                </button>
                <div class="dropdown-menu dropdown-menu-right" id="collapseAdd">
                  <a class="dropdown-item" href="#">Tablicę</a>
                  <a class="dropdown-item" href="#">Zespół</a>
                </div>

              </li>
              <li>
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseProfile"
                        aria-expanded="false" aria-controls="collapseProfile">
                  Profil
                </button>
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
              <a class="nav-link" href="#">Opcja1</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Opcja2</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                 aria-expanded="false">Menu</a>
              <div class="dropdown-menu">
                <h5 class="dropdown-header">Menu</h5>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Separated link</a>
              </div>
            </li>
          </ul>
        </div>
        <div class="container">
          asdasd
        </div>
      </body>
    </html>
  </div>
</template>

<script src="">
  export default {
    name: 'Home',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  body {
    background-image: url("../../../running-track-sn-3000x2001.jpg");
    background-size: cover;
    background-position: center;
    height: 100%;
  }

  .navbar-brand {
    font-weight: bolder;
  }

  .dropdown-header {
    font-size: larger;
    font-weight: bolder;
  }

  #collapseProfile {
    background-color: white;
    text-align: right;
  }


  .navbar {
    background-color: transparent;
  }

  .navbar-toggler {
    border-color: #0069d9;
  }

  .btn {
    margin: 2px 2px 2px 2px;
  }
</style>
