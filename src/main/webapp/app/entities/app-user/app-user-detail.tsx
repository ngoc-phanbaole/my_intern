import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './app-user.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const AppUserDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const appUserEntity = useAppSelector(state => state.appUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="appUserDetailsHeading">AppUser</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{appUserEntity.id}</dd>
          <dt>
            <span id="username">Username</span>
          </dt>
          <dd>{appUserEntity.username}</dd>
          <dt>
            <span id="password">Password</span>
          </dt>
          <dd>{appUserEntity.password}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{appUserEntity.email}</dd>
          <dt>
            <span id="phoneNumber">Phone Number</span>
          </dt>
          <dd>{appUserEntity.phoneNumber}</dd>
          <dt>
            <span id="resetToken">Reset Token</span>
          </dt>
          <dd>{appUserEntity.resetToken}</dd>
          <dt>
            <span id="resetTokenCreatedAt">Reset Token Created At</span>
          </dt>
          <dd>
            {appUserEntity.resetTokenCreatedAt ? (
              <TextFormat value={appUserEntity.resetTokenCreatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="otpCode">Otp Code</span>
          </dt>
          <dd>{appUserEntity.otpCode}</dd>
          <dt>
            <span id="otpCodeCreatedAt">Otp Code Created At</span>
          </dt>
          <dd>
            {appUserEntity.otpCodeCreatedAt ? (
              <TextFormat value={appUserEntity.otpCodeCreatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="otpCodeExpiredAt">Otp Code Expired At</span>
          </dt>
          <dd>
            {appUserEntity.otpCodeExpiredAt ? (
              <TextFormat value={appUserEntity.otpCodeExpiredAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="otpVerified">Otp Verified</span>
          </dt>
          <dd>{appUserEntity.otpVerified ? 'true' : 'false'}</dd>
          <dt>
            <span id="rememberToken">Remember Token</span>
          </dt>
          <dd>{appUserEntity.rememberToken}</dd>
          <dt>
            <span id="remembered">Remembered</span>
          </dt>
          <dd>{appUserEntity.remembered ? 'true' : 'false'}</dd>
          <dt>
            <span id="deviceInfo">Device Info</span>
          </dt>
          <dd>{appUserEntity.deviceInfo}</dd>
          <dt>
            <span id="createdAt">Created At</span>
          </dt>
          <dd>{appUserEntity.createdAt ? <TextFormat value={appUserEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedAt">Updated At</span>
          </dt>
          <dd>{appUserEntity.updatedAt ? <TextFormat value={appUserEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{appUserEntity.status}</dd>
          <dt>Roles</dt>
          <dd>
            {appUserEntity.roles
              ? appUserEntity.roles.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {appUserEntity.roles && i === appUserEntity.roles.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/app-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/app-user/${appUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AppUserDetail;
