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
  const btnCerrar = document.querySelector('#closeApp')

  btnCerrar.addEventListener('click', function () {
    Swal.fire({
      title: '¿Desea cerrar sesión?',
      text: 'Para ingresar nuevamente tendrá que introducir sus credenciales.',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '¡Si!',
      cancelButtonText: '¡Mejor no!'
    }).then(result => {
      if (result.isConfirmed) {
        //  cerrar sesion del usuario
        localStorage.clear()
        location.href = '/login.html'
      }
    })
  })

  /* --------------- funciones que se disparan al iniciar la app -------------- */

  const user = localStorage.getItem('username')

  if (!user) {
    obtenerNombreUsuario('http://localhost:8080/user', jwt)
  } else {
    nodoNombreUsuario.innerHTML = user
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
