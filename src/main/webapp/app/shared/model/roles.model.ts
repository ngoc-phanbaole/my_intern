import { IPermissions } from 'app/shared/model/permissions.model';

export interface IRoles {
  id?: number;
  roleName?: string;
  permissions?: IPermissions[] | null;
}

export const defaultValue: Readonly<IRoles> = {};
