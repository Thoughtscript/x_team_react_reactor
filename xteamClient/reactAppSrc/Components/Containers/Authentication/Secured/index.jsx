'use strict'

/**
 *  Secured client resource with Redux.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import './Secured.scss'
import React from 'react'
import CustomFooter from '../../../Presentation/CustomFooter/index'
import { checkArray } from '../../../../Helpers/Generic'
import { gradient } from '../../../../Helpers/GradientHelper'
import { api, FLUX_API } from '../../../../Constants'
import { asyncDataPost } from '../../../../Helpers/Xhr/Post'

export class Secured extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      users: [],
      ...this.props
    }
    this.save = this.save.bind(this)
    this.remove = this.remove.bind(this)
    this.clear = this.clear.bind(this)
    this.get = this.get.bind(this)
  }

  save (key, s) {
    const {save} = this.props
    this.save(key, s)
  }

  remove () {
    const {remove} = this.props
    this.remove()
  }

  clear () {
    const {clear} = this.props
    this.clear()
  }

  get (key) {
    const {get} = this.props
    this.get(key)
  }

  componentDidMount () {
    gradient('.mainTable > table')
    gradient('.mainTable > .content')
    gradient('.mainTable > .more')

    const {auth} = this.props

    asyncDataPost(api(FLUX_API), {
      'token': '33333',
      'username': 'solomon'
    }).then(success => {
      this.setState({
        users: success || []
      })
    })

  }

  render () {
    const {users} = this.state
    return (
      <div className="mainContentWrapper">
        <main className="secured">
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
            <div className="text">Super rad filler text!</div>
          </div>

          <div className="more">
            <div className="text">More super rad filler text!</div>
          </div>

        </main>
        <CustomFooter/>
      </div>
    )
  }
}