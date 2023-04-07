// chequear que exista un usuario loggeado
const jwt = localStorage.getItem('jwt')
if (!jwt) {
  location.replace('/login.html')
}


window.addEventListener('load', function () {
  /* -------------------------------------------------------------------------- */
  /*                             logica de la vista                             */
  /* -------------------------------------------------------------------------- */
  const jwt = localStorage.getItem('jwt')

  const nodoNombreUsuario = document.querySelector('.user-info a small')

  /* --------------- funciones que se disparan al iniciar la app -------------- */

  const user = localStorage.getItem('username')

  if (!user) {
    obtenerNombreUsuario('http://localhost:8080/user', jwt)
  }

  /* ---------------------- GET: obtener info del usuario --------------------- */
  function obtenerNombreUsuario(url, token) {
    const configuraciones = {
      method: 'GET',
      headers: {
        authorization: 'Bearer ' + token
      }
    }

    fetch(url, configuraciones)
      .then(respuesta => respuesta.json())
      .then(data => {
        console.log(data)
        localStorage.setItem('username', data.username);
        nodoNombreUsuario.innerHTML = data.username
      })
  }
})
