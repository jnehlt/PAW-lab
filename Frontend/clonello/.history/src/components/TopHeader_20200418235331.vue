<template>
  <div>
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
            <b-button v-b-toggle.sidebar-right>Toggle Sidebar</b-button>
              <b-sidebar id="sidebar-right" title="Sidebar" right shadow>
                <div class="px-3 py-2">
                  <p>
                    Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis
                    in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
                  </p>
                  <b-img src="https://picsum.photos/500/500/?image=54" fluid thumbnail></b-img>
                </div>
              </b-sidebar>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>



export default {
  
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.navbar-brand {
    font-weight: bolder;
    font-size: 2em;
}

.navbar {
    background-color: transparent;
}

.navbar-toggler {
    background-color: #4e555b;
}
</style>
