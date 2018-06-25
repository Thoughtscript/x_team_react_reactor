'use strict'

/**
 *  Navigation Menu.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import React from 'react'
import { Link } from 'react-router-dom'

import { BASE_PATH, HOME_PATH, SECURED_PATH, TABLE_PATH } from '../../Constants'

export class Menu extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      ...this.props
    }
  }

  render () {
    const w = window.location.pathname

    return (
      <nav>
        <ul>
          <li className="home">
            <Link className={(w === BASE_PATH || w === HOME_PATH) ? 'active' : 'inactive'} to={BASE_PATH}>Home</Link>
          </li>
          <li>
            <Link className={w === TABLE_PATH ? 'active' : 'inactive'} to={TABLE_PATH}>Table</Link>
          </li>
          <li>
            <Link className={w === SECURED_PATH ? 'active' : 'inactive'} to={SECURED_PATH}>Secured</Link>
          </li>
        </ul>
      </nav>
    )
  }
}