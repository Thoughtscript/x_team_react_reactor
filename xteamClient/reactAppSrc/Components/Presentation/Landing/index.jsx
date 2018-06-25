'use strict'

/**
 *  Grid-only Landing page.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import React from 'react'
import './Landing.scss'
import CustomFooter from '../CustomFooter'
import { GoogleMap } from '../GoogleMap'
import { gradient } from '../../../Helpers/GradientHelper'

export class Landing  extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      ...this.props
    }
  }

  componentDidMount () {
    try {
      gradient('.landingPage > .content')
    } catch (ex) {
      console.log(ex)
    }
  }

  render () {
    return (
      <div className="mainContentWrapper">
        <main className="landingPage">
          <div className="hero"/>
          <div className="content">
            <div className="text">
              Grid-Only Landing Page Section!
            </div>
          </div>
          <div className="more">
            <GoogleMap/>
          </div>
        </main>
        <CustomFooter/>
      </div>)
  }
}