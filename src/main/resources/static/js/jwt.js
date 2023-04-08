const jwt = localStorage.getItem('jwt')
if (!jwt) {
  location.replace('/login.html')
}
