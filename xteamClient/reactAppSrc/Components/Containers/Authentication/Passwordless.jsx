'use strict'

/**
 *  Passwordless authentication component and handlers.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import React from 'react'
import { Login } from './Login'
import { Secured } from './Secured'
import { checkObj, paramFromUrlString, redirect } from '../../../Helpers/Generic'
import { api, DEFAULT_EMAIL, DEFAULT_USER, PASSWORDLESS_SIGN_IN, SECURED_PATH } from '../../../Constants'
import { NotificationContainer, NotificationManager } from 'react-notifications'
import { asyncDataPost } from '../../../Helpers/Xhr/Post'

export class Passwordless extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      isValid: false,
      ...this.props
    }

    this.login = this.login.bind(this)
    this.logout = this.logout.bind(this)
    this.handleAuthentication = this.handleAuthentication.bind(this)
    this.isAuthenticated = this.isAuthenticated.bind(this)
  }

  login () {
    const data = {'username': DEFAULT_USER, 'email': DEFAULT_EMAIL}
    asyncDataPost(api(PASSWORDLESS_SIGN_IN), JSON.stringify(data)).then(r => {
      NotificationManager.success('Check your email!', 'Success!')
    })
  }

  logout () {
    const {remove, history} = this.props
    remove('auth')
    redirect(history, '/')
  }

  isAuthenticated () {
    const {auth} = this.props
    if (checkObj(auth) && checkObj(auth['expires_at'])) return new Date().getMilliseconds() < auth['expires_at']
    return false
  }

  handleAuthentication () {
    const {save, location} = this.props,
      search = location.search,
      tokenParam = paramFromUrlString(search, /token=[01234567890]+/),
      usernameParam = paramFromUrlString(search, /username=[a-zA-Z0123456789]+/),
      isValidated = (tokenParam != null && usernameParam != null && location.pathname === SECURED_PATH)

    try {
      if (isValidated) {
        const username = usernameParam[0].substr(9, usernameParam[0].length),
          token = tokenParam[0].substr(6, tokenParam[0].length)
        save('auth', {
          'token': token,
          'expires_at': new Date().getMilliseconds() + 15 * 60 * 1000,
          'username': username
        })
      }
    } catch (ex) {
      console.log('Exception encountered validation authentication: ' + ex)
    }

  }

  componentDidMount () {
    this.handleAuthentication()
  }

  render () {
    const {save, remove, get, clear, auth} = this.props
    return (
      <div className="secureDomPaddingWrapper">
        {
          this.isAuthenticated() ? <Secured save={save} remove={remove} get={get} clear={clear} auth={auth}/> : <Login handleLogin={this.login}/>
        }
        <NotificationContainer/>
      </div>
    )
  }
}