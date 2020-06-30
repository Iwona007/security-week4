package pl.iwona.securityencoderweek4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.iwona.securityencoderweek4.encoder.AlgorytmImpl;
import pl.iwona.securityencoderweek4.model.Text;

@Controller("/")
public class TextController {

    private AlgorytmImpl algorytm;

    public TextController(AlgorytmImpl algorytm) {
        this.algorytm = algorytm;
    }

    @GetMapping("/code")
    public String Start(Model model) {
        model.addAttribute("textToCode", new Text());//tekst
        model.addAttribute("secretKey", new Text());//password
        model.addAttribute("publicKey", new Text());// szyfr
        return "code";
    }

    @PostMapping("/codeText")
    public String codeText(@ModelAttribute Text text, Model model) {
        text.setTextForCrypt(text.getTextForCrypt()); //text
        text.setSecretKey(text.getSecretKey());//password
        String encode = algorytm.encode(text.getTextForCrypt(), text.getSecretKey());
        text.setPublicKey(encode);//szyfr
        model.addAttribute("textToCode", text);
        model.addAttribute("secretKey", text);
        model.addAttribute("publicKey", text);
        return "result";
    }

    @GetMapping("/decode")
    public String Start2(Model model) {
        model.addAttribute("textToEncode", new Text());
        model.addAttribute("secretKey", new Text());
        model.addAttribute("publicKey", new Text());
        return "decrypt";
    }

    @PostMapping("/decryptText")
    public String postDecrypt(@ModelAttribute Text text, Model model) {
        text.setPublicKey(text.getPublicKey());
        text.setSecretKey(text.getSecretKey());
        String decrypt = algorytm.decrypt(text.getPublicKey(), text.getSecretKey().toString());
        text.setTextForCrypt(decrypt);
        model.addAttribute("textToCode", text); // text
        model.addAttribute("secretKey", text); // password
        model.addAttribute("publicKey", text); // szyfr -teraz odszyfrowuje
        return "decryptresult";
    }
}
