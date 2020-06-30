package pl.iwona.securityencoderweek4.encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TextService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public TextService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String cryptText(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean encodeText(CharSequence rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
