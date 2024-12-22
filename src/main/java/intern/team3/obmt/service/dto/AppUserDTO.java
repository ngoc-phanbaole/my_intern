package intern.team3.obmt.service.dto;

import intern.team3.obmt.domain.enumeration.Status;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link intern.team3.obmt.domain.AppUser} entity.
 */
public class AppUserDTO implements Serializable {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    private String resetToken;

    private Instant resetTokenCreatedAt;

    private String otpCode;

    private Instant otpCodeCreatedAt;

    private Instant otpCodeExpiredAt;

    @NotNull
    private Boolean otpVerified;

    private String rememberToken;

    @NotNull
    private Boolean remembered;

    private String deviceInfo;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    @NotNull
    private Status status;

    private Set<RolesDTO> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Instant getResetTokenCreatedAt() {
        return resetTokenCreatedAt;
    }

    public void setResetTokenCreatedAt(Instant resetTokenCreatedAt) {
        this.resetTokenCreatedAt = resetTokenCreatedAt;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Instant getOtpCodeCreatedAt() {
        return otpCodeCreatedAt;
    }

    public void setOtpCodeCreatedAt(Instant otpCodeCreatedAt) {
        this.otpCodeCreatedAt = otpCodeCreatedAt;
    }

    public Instant getOtpCodeExpiredAt() {
        return otpCodeExpiredAt;
    }

    public void setOtpCodeExpiredAt(Instant otpCodeExpiredAt) {
        this.otpCodeExpiredAt = otpCodeExpiredAt;
    }

    public Boolean getOtpVerified() {
        return otpVerified;
    }

    public void setOtpVerified(Boolean otpVerified) {
        this.otpVerified = otpVerified;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Boolean getRemembered() {
        return remembered;
    }

    public void setRemembered(Boolean remembered) {
        this.remembered = remembered;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<RolesDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesDTO> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUserDTO)) {
            return false;
        }

        AppUserDTO appUserDTO = (AppUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, appUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserDTO{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", resetToken='" + getResetToken() + "'" +
            ", resetTokenCreatedAt='" + getResetTokenCreatedAt() + "'" +
            ", otpCode='" + getOtpCode() + "'" +
            ", otpCodeCreatedAt='" + getOtpCodeCreatedAt() + "'" +
            ", otpCodeExpiredAt='" + getOtpCodeExpiredAt() + "'" +
            ", otpVerified='" + getOtpVerified() + "'" +
            ", rememberToken='" + getRememberToken() + "'" +
            ", remembered='" + getRemembered() + "'" +
            ", deviceInfo='" + getDeviceInfo() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", roles=" + getRoles() +
            "}";
    }
}
