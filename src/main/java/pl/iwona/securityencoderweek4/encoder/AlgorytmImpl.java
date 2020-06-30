package pl.iwona.securityencoderweek4.encoder;

import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.iwona.securityencoderweek4.model.Text;

@Service
@Primary
public class AlgorytmImpl implements PasswordEncoder {

    private char[][] alphabet = new char['z' + 1]['z' + 1];
    private String correctString;
    private Text text;
    char c = ' ';

    public AlgorytmImpl() {
        createAlphabetTable();
    }

    public AlgorytmImpl(Text text) {
        this.text = text;
    }

    public void createAlphabetTable() {
        System.out.println("Tworze tablice Vigenera.");
        for (char a = 'a'; a <= 'z'; a++) {
            System.out.println("");
            char b = a;
            for (int z = 'a'; z <= 'z'; z++) {
                if (b == 'z' + 1) {
                    b = 'a';
                }
                alphabet[a][z] = b;
                System.out.print(alphabet[a][z] + ",");
                b++;
            }
        }
    }

    public String cryptData(CharSequence rawPassword, CharSequence correctString) {
        char[] textCharTable = rawPassword.toString().toCharArray(); //ala
        char[] correctCharTable = correctString.toString().toCharArray();// admin
        System.out.print("\n    Rezultat: ");

        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < textCharTable.length; index++) {
            System.out.print(alphabet[textCharTable[index]][correctCharTable[index]]);
            c = alphabet[textCharTable[index]][correctCharTable[index]];
            sb.append(c);//aom
        }
        sb.append(generateSafeToken());//salt
        return sb.toString();//szyfr+salt
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encode(rawPassword, correctString);
    }

    public String encode(CharSequence rawPassword, CharSequence correctString) {
        System.out.println(rawPassword); // tylko żeby zobaczyć wynik na konsoli
        System.out.println(correctString); // tylko żeby zobaczyć wynik na konsoli
        return cryptData(rawPassword, correctString);
    }

    private String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        System.out.println(token);
        return token;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword != null) {
            decrypt(encode(rawPassword), encodedPassword);
            return true;
        }
        return false;
    }

    public String decrypt(CharSequence inputText, String correctString) {
        char[] textCharTable = inputText.toString().toCharArray();//szyfr
        char[] correctCharTable = correctString.toCharArray();//haslo
        System.out.print("\n    Rezultat:");

        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < textCharTable.length; index++) {
            if (textCharTable[index] != ' ') {
                for (int z = 'a'; z <= 'z'; z++) {
                    if (textCharTable[index] == alphabet[correctCharTable[index]][z]) {
                        System.out.print(alphabet['a'][z]);
                        c = alphabet['a'][z];
                        sb.append(c);//text
                    }
                }
            } else {
                System.out.print(" ");
            }
        }
        return sb.toString();
    }
}
