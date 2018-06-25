'use strict'

/**
 *  Table view for unprotected client resources with Redux.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import React from 'react'
import CustomFooter from '../../Presentation/CustomFooter'
import './Table.scss'
import { gradient } from '../../../Helpers/GradientHelper'
import { checkArray } from '../../../Helpers/Generic'
import { asyncGet } from '../../../Helpers/Xhr/Get'
import { api, FUNCTIONAL_API } from '../../../Constants'

export class Table extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      users: [],
      ...this.props
    }
  }

  componentDidMount () {
    try {
      gradient('.mainTable > table')
      gradient('.mainTable > .content')
      gradient('.mainTable > .more')

      asyncGet(api(FUNCTIONAL_API)).then(success => {
        this.setState({
          users: success || []
        })
      })

    } catch (ex) {
      console.log(ex)
    }
  }

  render () {
    const {users} = this.state
    return (
      <div className="mainContentWrapper">
        <main className="mainTable">
          <table>
            <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
            </tr>
            </thead>
            <tbody>
            {
              checkArray(users) && (users).map(user =>
                <tr>
                  <th>{user.name}</th>
                  <th>{user.email.address}</th>
                </tr>
              )
            }
            </tbody>
          </table>
          <div className="content">
            <div className="text">Have some Lorem Ipsum.</div>
          </div>
          <div className="more">
            <div className="text">I am some super rad filler text!</div>
          </div>
        </main>
        <CustomFooter/>
      </div>
    )
  }
}