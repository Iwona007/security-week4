package pl.iwona.securityencoderweek4.model;

public class Text {

    private CharSequence textForCrypt; //ala - tekst
    private CharSequence secretKey; //admin - password
    private CharSequence publicKey; //aom - szyfr

    public Text() {
    }

    public Text(CharSequence textForCrypt, CharSequence secretKey, CharSequence publicKey) {
        this.textForCrypt = textForCrypt;
        this.secretKey = secretKey;
        this.publicKey = publicKey;
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
