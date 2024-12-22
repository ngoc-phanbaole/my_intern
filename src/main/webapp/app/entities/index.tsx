import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Roles from './roles';
import Permissions from './permissions';
import AppUser from './app-user';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}roles`} component={Roles} />
      <ErrorBoundaryRoute path={`${match.url}permissions`} component={Permissions} />
      <ErrorBoundaryRoute path={`${match.url}app-user`} component={AppUser} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
