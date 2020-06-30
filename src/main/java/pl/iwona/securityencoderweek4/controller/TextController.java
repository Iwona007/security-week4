package pl.iwona.securityencoderweek4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.iwona.securityencoderweek4.encoder.AlgorytmImpl;
import pl.iwona.securityencoderweek4.encoder.TextService;
import pl.iwona.securityencoderweek4.model.Text;

@Controller("/")
public class TextController {

    private TextService textService;
    private AlgorytmImpl algorytm;

    public TextController(TextService textService, AlgorytmImpl algorytm) {
        this.textService = textService;
        this.algorytm = algorytm;
    }

    @GetMapping("/code")
    public String Start(Model model) {
        model.addAttribute("textToCode", new Text());
        model.addAttribute("secretKey", new Text());
        model.addAttribute("publicKey", new Text());
        return "code";
    }

    @PostMapping("/codeText")
    public String codeText(@ModelAttribute Text text, Model model) {
        text.setTextForCrypt(text.getTextForCrypt());
        text.setSecretKey(text.getSecretKey());
        String encode = algorytm.encode(text.getSecretKey());
        text.setPublicKey(algorytm.encode(encode));
        return "result";
    }


    @GetMapping("/decode")
    public String Start2(Model model) {
        model.addAttribute("textToEncode", new Text());
        model.addAttribute("secretKey", new Text());
        model.addAttribute("textAfterdecrypt", new Text());
        model.addAttribute("publicKey", new Text());
        return "decrypt";
    }

    @PostMapping("/decryptText")
    public String postDecrypt(@ModelAttribute Text text, Model model) {
//        model.addAttribute("secretKey", text);
//        model.addAttribute("keyToEncode", text );
//        model.addAttribute("textAfterdecrypt", text);
//        model.addAttribute("publicKey", text);
        text.setKeyToEncode(algorytm.decrypt(text.getKeyToEncode(), text.getSecretKey().toString()));
        text.setSecretKey(text.getSecretKey());
//        algorytm.decrypt(text.getSecretKey());
        text.setTextAfterdecrypt(text.getTextAfterdecrypt());
        text.setTextForCrypt(text.getTextForCrypt());
        return "decryptresult";
    }
}
