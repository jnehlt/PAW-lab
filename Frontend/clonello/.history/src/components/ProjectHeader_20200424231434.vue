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
          <b-nav-item-dropdown text="Nie pierdol i klikaj" right>
            <b-dropdown-item href="#">EN</b-dropdown-item>
            <b-dropdown-item href="#">ES</b-dropdown-item>
            <b-dropdown-item href="#">RU</b-dropdown-item>
            <b-dropdown-item href="#">FA</b-dropdown-item>
          </b-nav-item-dropdown>
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