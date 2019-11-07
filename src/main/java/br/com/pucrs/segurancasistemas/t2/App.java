package br.com.pucrs.segurancasistemas.t2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import com.google.gson.Gson;

/**
 * Trabalho 2 de Segurança de Sistemas
 * AES
 * Rodrigo Leão da Silveira
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Trabalho 2 de Segurança de Sistemas\nAES\nRodrigo Leão da Silveira");

        // Ler tarefas do arquivo Json
        Tarefa[] tarefas;
        try (Reader reader = new FileReader("src\\main\\java\\br\\com\\pucrs\\segurancasistemas\\t2\\tarefas.json")) {
            Gson gson = new Gson();
            tarefas = gson.fromJson(reader, Tarefa[].class);

        } catch (Exception e) {
            System.out.println("Erro ao ler tarefas do arquivo");
            System.out.println(e.getMessage());
            return;
        }

        Tarefa tarefa;

        // Selecionar tarefa (via digitação ou via console)
        if (args.length == 0) {
            // via digitação
            System.out.println("Selecione uma opção:");
            System.out.println("(ID - Operação - Modo - chave - text (cifrado ou não)))");
            for (Tarefa t : tarefas) {
                System.out.println(t.toString().substring(0, 128) + "...");
            }

            Scanner ss = new Scanner(System.in);
            String opt = ss.nextLine();
            ss.close();

            tarefa = tarefas[Integer.parseInt(opt) - 1];

        } else {
            // via console
            tarefa = tarefas[Integer.parseInt(args[0]) - 1];
        }

        System.out.println("\nOpção selecionada: " + tarefa);

        // *** Inicio ***

        AES aes = new AES(tarefa.mode, tarefa.key);

        String resultString;
        if (tarefa.IsOperacaoCifrar()) {
            resultString = aes.encrypt(tarefa.plaintext);

        } else if (tarefa.IsOperacaoDecifrar()) {
            resultString = aes.decrypt(tarefa.ciphertext);
            
        } else {
            System.out.println("Erro ao carregar tarefas");
            return;
        }

        System.out.printf("\n\nResult: %s\n", resultString);

    }
}
