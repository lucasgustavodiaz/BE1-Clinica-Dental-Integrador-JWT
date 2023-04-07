const user = localStorage.getItem('username')
const nodoNombreUsuario = document.querySelector('.user-info a small')
nodoNombreUsuario.innerHTML = user
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