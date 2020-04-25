<template>
  <div>
      <div class="navbar navbar-expand-lg">
      <ul class="nav nav-pills nav-fill">
        <li class="nav-item">
          <a class="nav-link active" href="#">Projekt</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Kliknij mnie!</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Nie słuchaj go, mnie kliknij!</a>
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
  </div>
</template>

<script>
export default {

}
</script>

<style>
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