'use strict'

/**
 *  Constants - clientside config. Publicly exposed!
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

//React Client Routes
export const
  BASE_PATH = '/',
  HOME_PATH = '/home',
  TABLE_PATH = '/table',
  SECURED_PATH = '/secured'

//WebFlux Endpoints
export const
  API_PROTOCOL = 'http',
  API_HOST_PORT = 'localhost:8080',
  FLUX_API = '/api/flux/user/all',
  FUNCTIONAL_API = '/api/functional/user/all',
  PASSWORDLESS_SIGN_IN = '/magiclink'

export const
  DEFAULT_EMAIL = 'adam.gerard@gmail.com',
  DEFAULT_USER = 'adam'

export const api = suffix => `${API_PROTOCOL}://${API_HOST_PORT}${suffix}`
