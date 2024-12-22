import { IPermissions } from 'app/shared/model/permissions.model';
import { IAppUser } from 'app/shared/model/app-user.model';

export interface IRoles {
  id?: number;
  roleName?: string;
  permissions?: IPermissions[] | null;
  appusers?: IAppUser[] | null;
}

export const defaultValue: Readonly<IRoles> = {};
