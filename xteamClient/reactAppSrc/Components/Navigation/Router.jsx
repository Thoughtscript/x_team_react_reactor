'use strict'

/**
 *  Router.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import React from 'react'
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import CustomHeader from '../Presentation/CustomHeader'

import { BASE_PATH, HOME_PATH, SECURED_PATH, TABLE_PATH } from '../../Constants'

import { Landing } from '../Presentation/Landing'
import { AuthContainer } from '../Containers/Authentication'
import { TableContainer } from '../Containers/Table'
import { PageNotFound } from '../Presentation/PageNotFound'

export default () =>
  <BrowserRouter>
    <div className="innerRouterWrapper">
      <CustomHeader/>
      <Switch>
        <Route exact path={BASE_PATH} component={Landing}/>
        <Route exact path={HOME_PATH} component={Landing}/>
        <Route exact path={TABLE_PATH} component={TableContainer}/>
        <Route path={SECURED_PATH} render={(props) => <AuthContainer {...props}/>}/>
        <Route component={PageNotFound}/>
      </Switch>
    </div>
  </BrowserRouter>