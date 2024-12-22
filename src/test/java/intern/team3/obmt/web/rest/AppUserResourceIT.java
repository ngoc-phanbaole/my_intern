package intern.team3.obmt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import intern.team3.obmt.IntegrationTest;
import intern.team3.obmt.domain.AppUser;
import intern.team3.obmt.domain.enumeration.Status;
import intern.team3.obmt.repository.AppUserRepository;
import intern.team3.obmt.service.AppUserService;
import intern.team3.obmt.service.dto.AppUserDTO;
import intern.team3.obmt.service.mapper.AppUserMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AppUserResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class AppUserResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RESET_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_RESET_TOKEN = "BBBBBBBBBB";

    private static final Instant DEFAULT_RESET_TOKEN_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RESET_TOKEN_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_OTP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_OTP_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_OTP_CODE_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OTP_CODE_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_OTP_CODE_EXPIRED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OTP_CODE_EXPIRED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_OTP_VERIFIED = false;
    private static final Boolean UPDATED_OTP_VERIFIED = true;

    private static final String DEFAULT_REMEMBER_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_REMEMBER_TOKEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_REMEMBERED = false;
    private static final Boolean UPDATED_REMEMBERED = true;

    private static final String DEFAULT_DEVICE_INFO = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_INFO = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.INACTIVE;

    private static final String ENTITY_API_URL = "/api/app-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AppUserRepository appUserRepository;

    @Mock
    private AppUserRepository appUserRepositoryMock;

    @Autowired
    private AppUserMapper appUserMapper;

    @Mock
    private AppUserService appUserServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppUserMockMvc;

    private AppUser appUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppUser createEntity(EntityManager em) {
        AppUser appUser = new AppUser()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .resetToken(DEFAULT_RESET_TOKEN)
            .resetTokenCreatedAt(DEFAULT_RESET_TOKEN_CREATED_AT)
            .otpCode(DEFAULT_OTP_CODE)
            .otpCodeCreatedAt(DEFAULT_OTP_CODE_CREATED_AT)
            .otpCodeExpiredAt(DEFAULT_OTP_CODE_EXPIRED_AT)
            .otpVerified(DEFAULT_OTP_VERIFIED)
            .rememberToken(DEFAULT_REMEMBER_TOKEN)
            .remembered(DEFAULT_REMEMBERED)
            .deviceInfo(DEFAULT_DEVICE_INFO)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .status(DEFAULT_STATUS);
        return appUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppUser createUpdatedEntity(EntityManager em) {
        AppUser appUser = new AppUser()
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .resetToken(UPDATED_RESET_TOKEN)
            .resetTokenCreatedAt(UPDATED_RESET_TOKEN_CREATED_AT)
            .otpCode(UPDATED_OTP_CODE)
            .otpCodeCreatedAt(UPDATED_OTP_CODE_CREATED_AT)
            .otpCodeExpiredAt(UPDATED_OTP_CODE_EXPIRED_AT)
            .otpVerified(UPDATED_OTP_VERIFIED)
            .rememberToken(UPDATED_REMEMBER_TOKEN)
            .remembered(UPDATED_REMEMBERED)
            .deviceInfo(UPDATED_DEVICE_INFO)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS);
        return appUser;
    }

    @BeforeEach
    public void initTest() {
        appUser = createEntity(em);
    }

    @Test
    @Transactional
    void createAppUser() throws Exception {
        int databaseSizeBeforeCreate = appUserRepository.findAll().size();
        // Create the AppUser
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);
        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isCreated());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeCreate + 1);
        AppUser testAppUser = appUserList.get(appUserList.size() - 1);
        assertThat(testAppUser.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testAppUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testAppUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAppUser.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAppUser.getResetToken()).isEqualTo(DEFAULT_RESET_TOKEN);
        assertThat(testAppUser.getResetTokenCreatedAt()).isEqualTo(DEFAULT_RESET_TOKEN_CREATED_AT);
        assertThat(testAppUser.getOtpCode()).isEqualTo(DEFAULT_OTP_CODE);
        assertThat(testAppUser.getOtpCodeCreatedAt()).isEqualTo(DEFAULT_OTP_CODE_CREATED_AT);
        assertThat(testAppUser.getOtpCodeExpiredAt()).isEqualTo(DEFAULT_OTP_CODE_EXPIRED_AT);
        assertThat(testAppUser.getOtpVerified()).isEqualTo(DEFAULT_OTP_VERIFIED);
        assertThat(testAppUser.getRememberToken()).isEqualTo(DEFAULT_REMEMBER_TOKEN);
        assertThat(testAppUser.getRemembered()).isEqualTo(DEFAULT_REMEMBERED);
        assertThat(testAppUser.getDeviceInfo()).isEqualTo(DEFAULT_DEVICE_INFO);
        assertThat(testAppUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAppUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAppUser.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createAppUserWithExistingId() throws Exception {
        // Create the AppUser with an existing ID
        appUser.setId(1L);
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        int databaseSizeBeforeCreate = appUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUsernameIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setUsername(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setPassword(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setEmail(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setPhoneNumber(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOtpVerifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setOtpVerified(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRememberedIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setRemembered(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setCreatedAt(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setUpdatedAt(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = appUserRepository.findAll().size();
        // set the field null
        appUser.setStatus(null);

        // Create the AppUser, which fails.
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        restAppUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isBadRequest());

        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAppUsers() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);

        // Get all the appUserList
        restAppUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].resetToken").value(hasItem(DEFAULT_RESET_TOKEN)))
            .andExpect(jsonPath("$.[*].resetTokenCreatedAt").value(hasItem(DEFAULT_RESET_TOKEN_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].otpCode").value(hasItem(DEFAULT_OTP_CODE)))
            .andExpect(jsonPath("$.[*].otpCodeCreatedAt").value(hasItem(DEFAULT_OTP_CODE_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].otpCodeExpiredAt").value(hasItem(DEFAULT_OTP_CODE_EXPIRED_AT.toString())))
            .andExpect(jsonPath("$.[*].otpVerified").value(hasItem(DEFAULT_OTP_VERIFIED.booleanValue())))
            .andExpect(jsonPath("$.[*].rememberToken").value(hasItem(DEFAULT_REMEMBER_TOKEN)))
            .andExpect(jsonPath("$.[*].remembered").value(hasItem(DEFAULT_REMEMBERED.booleanValue())))
            .andExpect(jsonPath("$.[*].deviceInfo").value(hasItem(DEFAULT_DEVICE_INFO)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllAppUsersWithEagerRelationshipsIsEnabled() throws Exception {
        when(appUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAppUserMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(appUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllAppUsersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(appUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAppUserMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(appUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getAppUser() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);

        // Get the appUser
        restAppUserMockMvc
            .perform(get(ENTITY_API_URL_ID, appUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appUser.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.resetToken").value(DEFAULT_RESET_TOKEN))
            .andExpect(jsonPath("$.resetTokenCreatedAt").value(DEFAULT_RESET_TOKEN_CREATED_AT.toString()))
            .andExpect(jsonPath("$.otpCode").value(DEFAULT_OTP_CODE))
            .andExpect(jsonPath("$.otpCodeCreatedAt").value(DEFAULT_OTP_CODE_CREATED_AT.toString()))
            .andExpect(jsonPath("$.otpCodeExpiredAt").value(DEFAULT_OTP_CODE_EXPIRED_AT.toString()))
            .andExpect(jsonPath("$.otpVerified").value(DEFAULT_OTP_VERIFIED.booleanValue()))
            .andExpect(jsonPath("$.rememberToken").value(DEFAULT_REMEMBER_TOKEN))
            .andExpect(jsonPath("$.remembered").value(DEFAULT_REMEMBERED.booleanValue()))
            .andExpect(jsonPath("$.deviceInfo").value(DEFAULT_DEVICE_INFO))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAppUser() throws Exception {
        // Get the appUser
        restAppUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAppUser() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);

        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();

        // Update the appUser
        AppUser updatedAppUser = appUserRepository.findById(appUser.getId()).get();
        // Disconnect from session so that the updates on updatedAppUser are not directly saved in db
        em.detach(updatedAppUser);
        updatedAppUser
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .resetToken(UPDATED_RESET_TOKEN)
            .resetTokenCreatedAt(UPDATED_RESET_TOKEN_CREATED_AT)
            .otpCode(UPDATED_OTP_CODE)
            .otpCodeCreatedAt(UPDATED_OTP_CODE_CREATED_AT)
            .otpCodeExpiredAt(UPDATED_OTP_CODE_EXPIRED_AT)
            .otpVerified(UPDATED_OTP_VERIFIED)
            .rememberToken(UPDATED_REMEMBER_TOKEN)
            .remembered(UPDATED_REMEMBERED)
            .deviceInfo(UPDATED_DEVICE_INFO)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS);
        AppUserDTO appUserDTO = appUserMapper.toDto(updatedAppUser);

        restAppUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, appUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(appUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
        AppUser testAppUser = appUserList.get(appUserList.size() - 1);
        assertThat(testAppUser.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testAppUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testAppUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAppUser.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAppUser.getResetToken()).isEqualTo(UPDATED_RESET_TOKEN);
        assertThat(testAppUser.getResetTokenCreatedAt()).isEqualTo(UPDATED_RESET_TOKEN_CREATED_AT);
        assertThat(testAppUser.getOtpCode()).isEqualTo(UPDATED_OTP_CODE);
        assertThat(testAppUser.getOtpCodeCreatedAt()).isEqualTo(UPDATED_OTP_CODE_CREATED_AT);
        assertThat(testAppUser.getOtpCodeExpiredAt()).isEqualTo(UPDATED_OTP_CODE_EXPIRED_AT);
        assertThat(testAppUser.getOtpVerified()).isEqualTo(UPDATED_OTP_VERIFIED);
        assertThat(testAppUser.getRememberToken()).isEqualTo(UPDATED_REMEMBER_TOKEN);
        assertThat(testAppUser.getRemembered()).isEqualTo(UPDATED_REMEMBERED);
        assertThat(testAppUser.getDeviceInfo()).isEqualTo(UPDATED_DEVICE_INFO);
        assertThat(testAppUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAppUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAppUser.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingAppUser() throws Exception {
        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();
        appUser.setId(count.incrementAndGet());

        // Create the AppUser
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, appUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(appUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAppUser() throws Exception {
        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();
        appUser.setId(count.incrementAndGet());

        // Create the AppUser
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(appUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAppUser() throws Exception {
        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();
        appUser.setId(count.incrementAndGet());

        // Create the AppUser
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppUserMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appUserDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAppUserWithPatch() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);

        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();

        // Update the appUser using partial update
        AppUser partialUpdatedAppUser = new AppUser();
        partialUpdatedAppUser.setId(appUser.getId());

        partialUpdatedAppUser
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .resetToken(UPDATED_RESET_TOKEN)
            .resetTokenCreatedAt(UPDATED_RESET_TOKEN_CREATED_AT)
            .otpCodeExpiredAt(UPDATED_OTP_CODE_EXPIRED_AT)
            .otpVerified(UPDATED_OTP_VERIFIED)
            .rememberToken(UPDATED_REMEMBER_TOKEN)
            .remembered(UPDATED_REMEMBERED)
            .deviceInfo(UPDATED_DEVICE_INFO)
            .status(UPDATED_STATUS);

        restAppUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAppUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAppUser))
            )
            .andExpect(status().isOk());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
        AppUser testAppUser = appUserList.get(appUserList.size() - 1);
        assertThat(testAppUser.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testAppUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testAppUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAppUser.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAppUser.getResetToken()).isEqualTo(UPDATED_RESET_TOKEN);
        assertThat(testAppUser.getResetTokenCreatedAt()).isEqualTo(UPDATED_RESET_TOKEN_CREATED_AT);
        assertThat(testAppUser.getOtpCode()).isEqualTo(DEFAULT_OTP_CODE);
        assertThat(testAppUser.getOtpCodeCreatedAt()).isEqualTo(DEFAULT_OTP_CODE_CREATED_AT);
        assertThat(testAppUser.getOtpCodeExpiredAt()).isEqualTo(UPDATED_OTP_CODE_EXPIRED_AT);
        assertThat(testAppUser.getOtpVerified()).isEqualTo(UPDATED_OTP_VERIFIED);
        assertThat(testAppUser.getRememberToken()).isEqualTo(UPDATED_REMEMBER_TOKEN);
        assertThat(testAppUser.getRemembered()).isEqualTo(UPDATED_REMEMBERED);
        assertThat(testAppUser.getDeviceInfo()).isEqualTo(UPDATED_DEVICE_INFO);
        assertThat(testAppUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAppUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAppUser.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateAppUserWithPatch() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);

        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();

        // Update the appUser using partial update
        AppUser partialUpdatedAppUser = new AppUser();
        partialUpdatedAppUser.setId(appUser.getId());

        partialUpdatedAppUser
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .resetToken(UPDATED_RESET_TOKEN)
            .resetTokenCreatedAt(UPDATED_RESET_TOKEN_CREATED_AT)
            .otpCode(UPDATED_OTP_CODE)
            .otpCodeCreatedAt(UPDATED_OTP_CODE_CREATED_AT)
            .otpCodeExpiredAt(UPDATED_OTP_CODE_EXPIRED_AT)
            .otpVerified(UPDATED_OTP_VERIFIED)
            .rememberToken(UPDATED_REMEMBER_TOKEN)
            .remembered(UPDATED_REMEMBERED)
            .deviceInfo(UPDATED_DEVICE_INFO)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS);

        restAppUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAppUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAppUser))
            )
            .andExpect(status().isOk());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
        AppUser testAppUser = appUserList.get(appUserList.size() - 1);
        assertThat(testAppUser.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testAppUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testAppUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAppUser.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAppUser.getResetToken()).isEqualTo(UPDATED_RESET_TOKEN);
        assertThat(testAppUser.getResetTokenCreatedAt()).isEqualTo(UPDATED_RESET_TOKEN_CREATED_AT);
        assertThat(testAppUser.getOtpCode()).isEqualTo(UPDATED_OTP_CODE);
        assertThat(testAppUser.getOtpCodeCreatedAt()).isEqualTo(UPDATED_OTP_CODE_CREATED_AT);
        assertThat(testAppUser.getOtpCodeExpiredAt()).isEqualTo(UPDATED_OTP_CODE_EXPIRED_AT);
        assertThat(testAppUser.getOtpVerified()).isEqualTo(UPDATED_OTP_VERIFIED);
        assertThat(testAppUser.getRememberToken()).isEqualTo(UPDATED_REMEMBER_TOKEN);
        assertThat(testAppUser.getRemembered()).isEqualTo(UPDATED_REMEMBERED);
        assertThat(testAppUser.getDeviceInfo()).isEqualTo(UPDATED_DEVICE_INFO);
        assertThat(testAppUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAppUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAppUser.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingAppUser() throws Exception {
        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();
        appUser.setId(count.incrementAndGet());

        // Create the AppUser
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, appUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(appUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAppUser() throws Exception {
        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();
        appUser.setId(count.incrementAndGet());

        // Create the AppUser
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(appUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAppUser() throws Exception {
        int databaseSizeBeforeUpdate = appUserRepository.findAll().size();
        appUser.setId(count.incrementAndGet());

        // Create the AppUser
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppUserMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(appUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AppUser in the database
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAppUser() throws Exception {
        // Initialize the database
        appUserRepository.saveAndFlush(appUser);

        int databaseSizeBeforeDelete = appUserRepository.findAll().size();

        // Delete the appUser
        restAppUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, appUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppUser> appUserList = appUserRepository.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
