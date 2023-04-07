// chequear que exista un usuario loggeado
const jwt = localStorage.getItem('jwt')
if (!jwt) {
  location.replace('/login.html')
}

const apiBaseUrl = 'http://localhost:8080'

window.addEventListener('load', function () {
  /* -------------------------------------------------------------------------- */
  /*                             logica de la vista                             */
  /* -------------------------------------------------------------------------- */
  const jwt = localStorage.getItem('jwt')

  const nodoNombreUsuario = document.querySelector('.user-info a')
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
  obtenerNombreUsuario(`${apiBaseUrl}/user`, jwt)

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
        nodoNombreUsuario.innerHTML =
          `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-power"
                 viewBox="0 0 16 16">
              <path d="M7.5 1v7h1V1h-1z"/>
              <path
                d="M3 8.812a4.999 4.999 0 0 1 2.578-4.375l-.485-.874A6 6 0 1 0 11 3.616l-.501.865A5 5 0 1 1 3 8.812z"/>
            </svg>` +
          ' ' +
          data.username
      })
  }
})
