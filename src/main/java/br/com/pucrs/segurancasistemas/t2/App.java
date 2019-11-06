package br.com.pucrs.segurancasistemas.t2;

import java.io.FileNotFoundException;
//mport com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.security.Principal;
import java.util.Scanner;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello Java");

        int argc = 0;
        for (String arg : args) {
            System.out.printf("arg %d: %s\n", argc++, arg);
        }

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

        // Selecionar operação
        if (args.length == 0) {
            System.out.println("Selecione uma opção:");
            for (Tarefa t : tarefas) {
                System.out.println(t);
            }

            Scanner ss = new Scanner(System.in);
            String opt = ss.nextLine();
            ss.close();

            tarefa = tarefas[Integer.parseInt(opt) - 1];

        } else {

            tarefa = tarefas[Integer.parseInt(args[0]) - 1];

        }

        System.out.println("Tarefa selecionada: " + tarefa);

    }
}
