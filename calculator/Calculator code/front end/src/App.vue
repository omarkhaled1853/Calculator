<template>
  <Calculator />
</template>

<script>
import Calculator from "./components/Calculator"
export default {
  name: 'App',
  components: {
    Calculator
  }
}
</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-color: #f7f2f3;
}
</style>
