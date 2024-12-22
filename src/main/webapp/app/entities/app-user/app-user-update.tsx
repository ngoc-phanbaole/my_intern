import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRoles } from 'app/shared/model/roles.model';
import { getEntities as getRoles } from 'app/entities/roles/roles.reducer';
import { getEntity, updateEntity, createEntity, reset } from './app-user.reducer';
import { IAppUser } from 'app/shared/model/app-user.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { Status } from 'app/shared/model/enumerations/status.model';

export const AppUserUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const roles = useAppSelector(state => state.roles.entities);
  const appUserEntity = useAppSelector(state => state.appUser.entity);
  const loading = useAppSelector(state => state.appUser.loading);
  const updating = useAppSelector(state => state.appUser.updating);
  const updateSuccess = useAppSelector(state => state.appUser.updateSuccess);
  const statusValues = Object.keys(Status);
  const handleClose = () => {
    props.history.push('/app-user' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getRoles({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.resetTokenCreatedAt = convertDateTimeToServer(values.resetTokenCreatedAt);
    values.otpCodeCreatedAt = convertDateTimeToServer(values.otpCodeCreatedAt);
    values.otpCodeExpiredAt = convertDateTimeToServer(values.otpCodeExpiredAt);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...appUserEntity,
      ...values,
      roles: mapIdList(values.roles),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          resetTokenCreatedAt: displayDefaultDateTime(),
          otpCodeCreatedAt: displayDefaultDateTime(),
          otpCodeExpiredAt: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          status: 'ACTIVE',
          ...appUserEntity,
          resetTokenCreatedAt: convertDateTimeFromServer(appUserEntity.resetTokenCreatedAt),
          otpCodeCreatedAt: convertDateTimeFromServer(appUserEntity.otpCodeCreatedAt),
          otpCodeExpiredAt: convertDateTimeFromServer(appUserEntity.otpCodeExpiredAt),
          createdAt: convertDateTimeFromServer(appUserEntity.createdAt),
          updatedAt: convertDateTimeFromServer(appUserEntity.updatedAt),
          roles: appUserEntity?.roles?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="obmtApp.appUser.home.createOrEditLabel" data-cy="AppUserCreateUpdateHeading">
            Create or edit a AppUser
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="app-user-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Username"
                id="app-user-username"
                name="username"
                data-cy="username"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Password"
                id="app-user-password"
                name="password"
                data-cy="password"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Email"
                id="app-user-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Phone Number"
                id="app-user-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Reset Token" id="app-user-resetToken" name="resetToken" data-cy="resetToken" type="text" />
              <ValidatedField
                label="Reset Token Created At"
                id="app-user-resetTokenCreatedAt"
                name="resetTokenCreatedAt"
                data-cy="resetTokenCreatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Otp Code" id="app-user-otpCode" name="otpCode" data-cy="otpCode" type="text" />
              <ValidatedField
                label="Otp Code Created At"
                id="app-user-otpCodeCreatedAt"
                name="otpCodeCreatedAt"
                data-cy="otpCodeCreatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label="Otp Code Expired At"
                id="app-user-otpCodeExpiredAt"
                name="otpCodeExpiredAt"
                data-cy="otpCodeExpiredAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label="Otp Verified"
                id="app-user-otpVerified"
                name="otpVerified"
                data-cy="otpVerified"
                check
                type="checkbox"
              />
              <ValidatedField label="Remember Token" id="app-user-rememberToken" name="rememberToken" data-cy="rememberToken" type="text" />
              <ValidatedField label="Remembered" id="app-user-remembered" name="remembered" data-cy="remembered" check type="checkbox" />
              <ValidatedField label="Device Info" id="app-user-deviceInfo" name="deviceInfo" data-cy="deviceInfo" type="text" />
              <ValidatedField
                label="Created At"
                id="app-user-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Updated At"
                id="app-user-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Status" id="app-user-status" name="status" data-cy="status" type="select">
                {statusValues.map(status => (
                  <option value={status} key={status}>
                    {status}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Roles" id="app-user-roles" data-cy="roles" type="select" multiple name="roles">
                <option value="" key="0" />
                {roles
                  ? roles.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/app-user" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default AppUserUpdate;
