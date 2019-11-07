package br.com.pucrs.segurancasistemas.t2;

public class Tarefa {

    public int id;
    public String mode;
    public String key;
    public String ciphertext;
    public String plaintext;

    @Override
    public String toString() {
        return id + " - " + (ciphertext.isEmpty() ? "cifrar  " : "decifrar") + " - " + mode + " - " + key + " - "
                + ciphertext + plaintext;
    }

    public boolean IsOperacaoCifrar() {
        return !plaintext.isEmpty();
    }

    public boolean IsOperacaoDecifrar() {
        return !ciphertext.isEmpty();
    }

}