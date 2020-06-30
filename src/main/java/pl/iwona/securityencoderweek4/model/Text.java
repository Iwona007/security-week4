package pl.iwona.securityencoderweek4.model;

public class Text {

    private CharSequence textForCrypt; //alina
    private CharSequence secretKey; //admin
    private CharSequence publicKey; // xxxx

    public Text() {
    }

    public Text(CharSequence textForCrypt, CharSequence secretKey, CharSequence publicKey, CharSequence keyToEncode, String textAfterdecrypt) {
        this.textForCrypt = textForCrypt;
        this.secretKey = secretKey;
        this.publicKey = publicKey;
        this.keyToEncode = keyToEncode;
        this.textAfterdecrypt = textAfterdecrypt;
    }

    private CharSequence keyToEncode; // xxx
    private String secretKeyUse;// admin
    private String textAfterdecrypt;// alina

    public CharSequence getKeyToEncode() {
        return keyToEncode;
    }

    public void setKeyToEncode(CharSequence keyToEncode) {
        this.keyToEncode = keyToEncode;
    }

    public String getTextAfterdecrypt() {
        return textAfterdecrypt;
    }

    public void setTextAfterdecrypt(String textAfterdecrypt) {
        this.textAfterdecrypt = textAfterdecrypt;
    }

    public CharSequence getTextForCrypt() {
        return textForCrypt;
    }

    public void setTextForCrypt(CharSequence textForCrypt) {
        this.textForCrypt = textForCrypt;
    }

    public CharSequence getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(CharSequence secretKey) {
        this.secretKey = secretKey;
    }

    public CharSequence getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(CharSequence publicKey) {
        this.publicKey = publicKey;
    }
}
