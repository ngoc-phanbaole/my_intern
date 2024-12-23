import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './app-user.reducer';
import { IAppUser } from 'app/shared/model/app-user.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const AppUser = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const appUserList = useAppSelector(state => state.appUser.entities);
  const loading = useAppSelector(state => state.appUser.loading);
  const totalItems = useAppSelector(state => state.appUser.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const { match } = props;

  return (
    <div>
      <h2 id="app-user-heading" data-cy="AppUserHeading">
        App Users
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new App User
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {appUserList && appUserList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('username')}>
                  Username <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('password')}>
                  Password <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('email')}>
                  Email <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('phoneNumber')}>
                  Phone Number <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('resetToken')}>
                  Reset Token <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('resetTokenCreatedAt')}>
                  Reset Token Created At <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('otpCode')}>
                  Otp Code <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('otpCodeCreatedAt')}>
                  Otp Code Created At <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('otpCodeExpiredAt')}>
                  Otp Code Expired At <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('otpVerified')}>
                  Otp Verified <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rememberToken')}>
                  Remember Token <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('remembered')}>
                  Remembered <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('deviceInfo')}>
                  Device Info <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  Created At <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('updatedAt')}>
                  Updated At <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('status')}>
                  Status <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {appUserList.map((appUser, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${appUser.id}`} color="link" size="sm">
                      {appUser.id}
                    </Button>
                  </td>
                  <td>{appUser.username}</td>
                  <td>{appUser.password}</td>
                  <td>{appUser.email}</td>
                  <td>{appUser.phoneNumber}</td>
                  <td>{appUser.resetToken}</td>
                  <td>
                    {appUser.resetTokenCreatedAt ? (
                      <TextFormat type="date" value={appUser.resetTokenCreatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{appUser.otpCode}</td>
                  <td>
                    {appUser.otpCodeCreatedAt ? <TextFormat type="date" value={appUser.otpCodeCreatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {appUser.otpCodeExpiredAt ? <TextFormat type="date" value={appUser.otpCodeExpiredAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{appUser.otpVerified ? 'true' : 'false'}</td>
                  <td>{appUser.rememberToken}</td>
                  <td>{appUser.remembered ? 'true' : 'false'}</td>
                  <td>{appUser.deviceInfo}</td>
                  <td>{appUser.createdAt ? <TextFormat type="date" value={appUser.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{appUser.updatedAt ? <TextFormat type="date" value={appUser.updatedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{appUser.status}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${appUser.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${appUser.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${appUser.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No App Users found</div>
        )}
      </div>
      {totalItems ? (
        <div className={appUserList && appUserList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default AppUser;
