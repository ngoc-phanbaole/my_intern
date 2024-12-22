package intern.team3.obmt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import intern.team3.obmt.domain.enumeration.Status;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AppUser.
 */
@Entity
@Table(name = "app_user")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_created_at")
    private Instant resetTokenCreatedAt;

    @Column(name = "otp_code")
    private String otpCode;

    @Column(name = "otp_code_created_at")
    private Instant otpCodeCreatedAt;

    @Column(name = "otp_code_expired_at")
    private Instant otpCodeExpiredAt;

    @NotNull
    @Column(name = "otp_verified", nullable = false)
    private Boolean otpVerified;

    @Column(name = "remember_token")
    private String rememberToken;

    @NotNull
    @Column(name = "remembered", nullable = false)
    private Boolean remembered;

    @Column(name = "device_info")
    private String deviceInfo;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToMany
    @JoinTable(
        name = "rel_app_user__roles",
        joinColumns = @JoinColumn(name = "app_user_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    @JsonIgnoreProperties(value = { "permissions", "appusers" }, allowSetters = true)
    private Set<Roles> roles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AppUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public AppUser username(String username) {
        this.setUsername(username);
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public AppUser password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public AppUser email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public AppUser phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResetToken() {
        return this.resetToken;
    }

    public AppUser resetToken(String resetToken) {
        this.setResetToken(resetToken);
        return this;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Instant getResetTokenCreatedAt() {
        return this.resetTokenCreatedAt;
    }

    public AppUser resetTokenCreatedAt(Instant resetTokenCreatedAt) {
        this.setResetTokenCreatedAt(resetTokenCreatedAt);
        return this;
    }

    public void setResetTokenCreatedAt(Instant resetTokenCreatedAt) {
        this.resetTokenCreatedAt = resetTokenCreatedAt;
    }

    public String getOtpCode() {
        return this.otpCode;
    }

    public AppUser otpCode(String otpCode) {
        this.setOtpCode(otpCode);
        return this;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Instant getOtpCodeCreatedAt() {
        return this.otpCodeCreatedAt;
    }

    public AppUser otpCodeCreatedAt(Instant otpCodeCreatedAt) {
        this.setOtpCodeCreatedAt(otpCodeCreatedAt);
        return this;
    }

    public void setOtpCodeCreatedAt(Instant otpCodeCreatedAt) {
        this.otpCodeCreatedAt = otpCodeCreatedAt;
    }

    public Instant getOtpCodeExpiredAt() {
        return this.otpCodeExpiredAt;
    }

    public AppUser otpCodeExpiredAt(Instant otpCodeExpiredAt) {
        this.setOtpCodeExpiredAt(otpCodeExpiredAt);
        return this;
    }

    public void setOtpCodeExpiredAt(Instant otpCodeExpiredAt) {
        this.otpCodeExpiredAt = otpCodeExpiredAt;
    }

    public Boolean getOtpVerified() {
        return this.otpVerified;
    }

    public AppUser otpVerified(Boolean otpVerified) {
        this.setOtpVerified(otpVerified);
        return this;
    }

    public void setOtpVerified(Boolean otpVerified) {
        this.otpVerified = otpVerified;
    }

    public String getRememberToken() {
        return this.rememberToken;
    }

    public AppUser rememberToken(String rememberToken) {
        this.setRememberToken(rememberToken);
        return this;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Boolean getRemembered() {
        return this.remembered;
    }

    public AppUser remembered(Boolean remembered) {
        this.setRemembered(remembered);
        return this;
    }

    public void setRemembered(Boolean remembered) {
        this.remembered = remembered;
    }

    public String getDeviceInfo() {
        return this.deviceInfo;
    }

    public AppUser deviceInfo(String deviceInfo) {
        this.setDeviceInfo(deviceInfo);
        return this;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public AppUser createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public AppUser updatedAt(Instant updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return this.status;
    }

    public AppUser status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Roles> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public AppUser roles(Set<Roles> roles) {
        this.setRoles(roles);
        return this;
    }

    public AppUser addRoles(Roles roles) {
        this.roles.add(roles);
        roles.getAppusers().add(this);
        return this;
    }

    public AppUser removeRoles(Roles roles) {
        this.roles.remove(roles);
        roles.getAppusers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUser)) {
            return false;
        }
        return id != null && id.equals(((AppUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUser{" +
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
            "}";
    }
}
