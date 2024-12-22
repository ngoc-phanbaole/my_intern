import dayjs from 'dayjs';
import { IRoles } from 'app/shared/model/roles.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IAppUser {
  id?: number;
  username?: string;
  password?: string;
  email?: string;
  phoneNumber?: string;
  resetToken?: string | null;
  resetTokenCreatedAt?: string | null;
  otpCode?: string | null;
  otpCodeCreatedAt?: string | null;
  otpCodeExpiredAt?: string | null;
  otpVerified?: boolean;
  rememberToken?: string | null;
  remembered?: boolean;
  deviceInfo?: string | null;
  createdAt?: string;
  updatedAt?: string;
  status?: Status;
  roles?: IRoles[] | null;
}

export const defaultValue: Readonly<IAppUser> = {
  otpVerified: false,
  remembered: false,
};
