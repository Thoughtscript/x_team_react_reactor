'use strict'

/**
 *  Login with Redux.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import React from 'react'
import CustomFooter from '../../../Presentation/CustomFooter/index'
import './Login.scss'
import { gradient } from '../../../../Helpers/GradientHelper'

export class Login extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      ...this.props
    }
    this.handleLogin = this.handleLogin.bind(this)
  }

  handleLogin (e) {
    this.props.handleLogin()
  }

  componentDidMount () {
    try {
      gradient('.login > .top')
      gradient('.login > .content')
      gradient('.login > .more')
    } catch (ex) {
      console.log(ex)
    }
  }

  render () {
    return (
      <div className="mainContentWrapper">
        <main className="login">

          <div className="top">
            <div className="text" onClick={e => this.handleLogin(e)}>Please Login!</div>
          </div>

          <div className="content">
            <div className="text">Super rad filler text!</div>
          </div>

          <div className="more">
            <div className="text">More super rad filler text!</div>
          </div>

          <CustomFooter/>
        </main>
      </div>
    )
  }
}