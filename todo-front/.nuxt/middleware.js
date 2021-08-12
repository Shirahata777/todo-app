const middleware = {}

middleware['keycloak'] = require('../middleware/keycloak.js')
middleware['keycloak'] = middleware['keycloak'].default || middleware['keycloak']

export default middleware
