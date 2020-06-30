package pl.iwona.securityencoderweek4.encoder;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.iwona.securityencoderweek4.model.Text;

@Service
@Primary
public class AlgorytmImpl implements PasswordEncoder {

    private char[][] alphabet = new char['z' + 1]['z' + 1];
    private Text text;

    

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

    char c = ' ';

    public String cryptData(CharSequence rawPassword) {
        char[] textCharTable = rawPassword.toString().toCharArray(); //ala
        char[] correctCharTable = rawPassword.toString().toCharArray();// admin
        System.out.print("\n    Rezultat: ");

        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < textCharTable.length; index++) {
            System.out.print(alphabet[textCharTable[index]][correctCharTable[index]]);
            c = alphabet[textCharTable[index]][correctCharTable[index]];
            sb.append(c);
        }
//        sb.append(generateSafeToken());
        return sb.toString();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return cryptData(rawPassword);
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
        char[] textCharTable = inputText.toString().toCharArray();
        char[] correctCharTable = correctString.toCharArray();
        System.out.print("\n    Rezultat:");
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < textCharTable.length; index++) {
            if (textCharTable[index] != ' ') {
                for (int z = 'a'; z <= 'z'; z++) {
                    if (textCharTable[index] == alphabet[correctCharTable[index]][z]) {
                        System.out.print(alphabet['a'][z]);
                        c = alphabet['a'][z];
                    }
                }
            } else {
                System.out.print(" ");
            }
        }
        return sb.append(c).toString();
    }

    private Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        AlgorytmImpl app = new AlgorytmImpl();
        app.createAlphabetTable();
        app.getDataFromUser();
    }
    public void getDataFromUser() {
        System.out.println("\n\n Wybierz opcje: \n\n 1. Szyfruj\n 2. Deszyfruj\n 3. Koniec\n");
        System.out.print("#:");
        String option = input.nextLine();
        if (option.equals("1")) {
            System.out.println("Podaj text do szyfrowania (max 255 znakow)");
            System.out.print("  text:");
            String inputText = input.nextLine();
            System.out.println("\nPodaj Haslo nie krrotsze niz " + inputText.length() + "(max 255 znakow)");
            System.out.print("  haslo:");
            String   correctString = input.nextLine();
            cryptData(inputText);
            getDataFromUser();
        } else if (option.equals("2")) {
            System.out.println("Podaj text do odszyfrowania (max 255 znakow)");
            System.out.print("  text:");
            String inputText = input.nextLine();
            System.out.println("\nPodaj Haslo nie krrotsze niz " + inputText.length() + "(max 255 znakow)");
            System.out.print("  haslo:");
            String correctString = input.nextLine();
            decrypt(inputText, correctString);
            getDataFromUser();
        } else if (option.equals("3")) {
            System.out.println("Koniec.....");
        } else {
            getDataFromUser();
        }
    }

}
