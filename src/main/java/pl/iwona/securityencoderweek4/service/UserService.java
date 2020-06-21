package pl.iwona.securityencoderweek4.service;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.iwona.securityencoderweek4.model.ApiUser;
import pl.iwona.securityencoderweek4.model.Role;
import pl.iwona.securityencoderweek4.model.VerificationToken;
import pl.iwona.securityencoderweek4.repository.ApiUserRepository;
import pl.iwona.securityencoderweek4.repository.VerificationTokenRepo;

@Service
public class UserService {

    private ApiUserRepository apiUserRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepo verificationTokenRepo;
    private MailSenderService mailSenderService;

    public UserService(ApiUserRepository apiUserRepository,
                       PasswordEncoder passwordEncoder,
                       VerificationTokenRepo verificationTokenRepo, MailSenderService mailSenderService) {
        this.apiUserRepository = apiUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepo = verificationTokenRepo;
        this.mailSenderService = mailSenderService;
    }

//    public void addNewUser(ApiUser user, HttpServletRequest request, Role role) {
//        VerificationToken verificationToken = new VerificationToken();
//        switch (role) {
//            case ADMIN:
//                user.setRole(user.getRole();
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//                apiUserRepository.save(user);
//                generateToken(user);
//                generateUrl(user, request, verificationToken.getValue());
//                sentMailWithToken(user, request, verificationToken.getValue());
//                verifyToken(verificationToken.getValue(), user);
//                sendEmailWithAdminRoleTokenToModerator(user);
//                verifytokenAdmin(verificationToken.getValue());
//                break;
//            case USER:
//                user.setRole(Role.USER);
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//                apiUserRepository.save(user);
//                generateToken(user);
//                generateUrl(user, request, verificationToken.getValue());
//                sentMailWithToken(user, request, verificationToken.getValue());
//                verifyToken(verificationToken.getValue(), user);
//        }
//    }

    public void addUser(ApiUser user, HttpServletRequest request) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        apiUserRepository.save(user);
        String token = UUID.randomUUID().toString();
        generateToken(user, token);
        if (user.getRole() == Role.USER) {
            mailSenderService.sendMail(user.getUsername(), "Verification Token", url(token, request), false);
            removeOldToken(token);
        } else {
            mailToEnableAdmin(token, request);
            mailSenderService.sendMail(user.getUsername(), "Verification Token", adminUrl(token, request), false);
            removeOldToken(token);
        }
    }

    private VerificationToken generateToken(ApiUser user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        return verificationTokenRepo.save(verificationToken);
    }

    private String url(String token, HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath() +
                "/verify-token?token=" + token;
    }

    public void verifyToken(String token) {
        VerificationToken verificationToken = verificationTokenRepo.findByValue(token);
        ApiUser user = verificationToken.getApiUser();
        if (user.getRole() == Role.USER) {
            user.setEnabled(true);
        } else if (user.getRole() == Role.valueOf("ROLE_ADMIN")) {
            user.setEnabled(false);
        }
        apiUserRepository.save(user);
        removeOldToken(token);
    }

    public void isAdmin(String token) {
        VerificationToken verificationToken = verificationTokenRepo.findByValue(token);
        ApiUser user = verificationToken.getApiUser();
        user.setRole(user.getRole());
        user.setEnabled(true);
        apiUserRepository.save(user);
    }

    private String adminUrl(String token, HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath() +
                "/admin-token?token=" + token;
    }

    private void mailToEnableAdmin(String token, HttpServletRequest request) {
        mailSenderService.sendMail("test222test222mail@gmail.com",
                "Admin role request", adminUrl(token, request),
                false);
    }

    public void removeOldToken(String token ){
        VerificationToken verificationToken = verificationTokenRepo.findByValue(token);
        ApiUser user = verificationToken.getApiUser();
        if(user.isEnabled() && user.getRole() == Role.USER || user.getRole() == Role.ADMIN) {
            verificationTokenRepo.delete(verificationToken);
        }
    }
}
