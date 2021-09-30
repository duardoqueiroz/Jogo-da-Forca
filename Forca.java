import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Forca {
  ArrayList<String> palavras = new ArrayList<>();
  ArrayList<Estatisticas> estatisticas = new ArrayList<>();
  Date data;
  String palavraInserida, palavraCensurada, tentativa, status;
  char[] charArray;
  char letra;
  int verificar, quantTentativas, indicePalavra, vidas, quantVitorias = 0, quantDerrotas = 0, quantVezesJogadas = 0;
  Random sortear = new Random();
  Scanner ler = new Scanner(System.in);
  boolean acertou, ganhou = false;
  StringBuilder palavraNova;

  public void main() {
    if (palavras.isEmpty()) {
      System.out.println("\nNão é possível jogar sem haver palavras inseridas no sistema.\n");
    } else {
      inicializar();
    }
  }

  public void jogar() {
    do {
      exibirCena();
    } while (vidas > 0 && !palavraCensurada.equalsIgnoreCase(palavras.get(indicePalavra)));
    quantVezesJogadas++;
    estatisticas.add(new Estatisticas(status, vidas, data, palavras.get(indicePalavra), quantTentativas));
  }

  // FUNÇÃO DE INICIALIZAÇÃO DO JOGO, PARA RESETAR AS VARIÁVEIS
  public void inicializar() {
    quantTentativas = 0;
    data = new Date();
    indicePalavra = sortear.nextInt(palavras.size());
    palavraCensurada = "";
    vidas = 6;
    censurarPalavra(indicePalavra);
    jogar();
  }

  // FUNÇÃO PARA CENSURAR A PALAVRA SORTEADA
  public void censurarPalavra(int indice) {
    for (int i = 0; i < palavras.get(indice).length(); i++) {
      palavraCensurada = palavraCensurada + "*";
    }
  }

  // FUNÇÃO PARA EXIBIR A CENA DA FORCA
  public void exibirCena() {
    System.out.println("__________________________________________________");
    System.out.println("        ACERTE A PALAVRA     ");
    System.out.println("\nQuantidade de vidas: " + vidas);
    System.out.println("Palavra: " + palavraCensurada);
    System.out.print("Digite uma letra/palavra: ");
    tentativa = ler.nextLine();
    System.out.println("__________________________________________________");
    checarTentativa(tentativa);
  }

  // FUNÇÕES PARA CHECAR A TENTATIVA DO USUÁRIO

  public void checarTentativa(String tentativa) {
    if (!tentativaInvalida(tentativa)) {
      if (tentouLetra(tentativa)) {
        quantTentativas++;
        if (!acertouLetra()) {
          vidas -= 1;
        }
        if (palavraCensurada.equalsIgnoreCase(palavras.get(indicePalavra))) {
          ganhou();
        }
      }
      if (tentouPalavra(tentativa)) {
        acertouPalavra();
        quantTentativas++;
      }
      if (vidas == 0) {
        perdeu();
      }
    } else {
      System.out
          .println("\nTentativa inválida, você não perderá vidas por isso, mas seu número de tentativas aumentará.\n");
      quantTentativas++;
    }
  }

  public boolean tentativaInvalida(String tentativa) {
    for (int i = 0; i < tentativa.length(); i++) {
      if ((tentativa.charAt(i) <= '9' && tentativa.charAt(i) >= '0') || tentativa.charAt(i) == ' ') {
        return true;
      }
    }
    return false;
  }

  public boolean tentouLetra(String tentativa) {
    if (tentativa.length() == 1 && !tentativaInvalida(tentativa)) {
      letra = tentativa.charAt(0);
      return true;
    }
    return false;
  }

  public boolean tentouPalavra(String tentativa) {
    if (tentativa.length() > 1 && !tentativaInvalida(tentativa)) {
      return true;
    }
    return false;
  }

  // FUNÇÃO PARA CHECAR SE A TENTATIVA FOI CORRETA
  public boolean acertouPalavra() {
    if (tentouPalavra(tentativa) && tentativa.equalsIgnoreCase(palavras.get(indicePalavra))) {
      palavraCensurada = tentativa;
      ganhou();
      return true;
    }
    vidas -= 1;
    return false;
  }

  public boolean acertouLetra() {
    acertou = false;
    for (int i = 0; i < palavras.get(indicePalavra).length(); i++) {
      if (letra == (palavras.get(indicePalavra).charAt(i))) {
        substituirSimbolo(i);
        acertou = true;
      }
    }
    return acertou;
  }

  // FUNÇÃO PARA INSERIR PALAVRA
  public void inserirPalavra() {
    System.out.println("__________________________________________________");
    System.out.print("Palavra: ");
    palavraInserida = ler.nextLine();
    System.out.println("__________________________________________________");
    validarPalavra(palavraInserida);
  }

  // FUNÇÃO RESPONSÁVEL PELA VALIDAÇÃO DA PALAVRA INSERIDA
  public void validarPalavra(String palavra) {
    verificar = 0;
    for (int i = 0; i < palavra.length(); i++) {
      if (palavra.charAt(i) <= '9' && palavra.charAt(i) >= '0') {
        System.out.println("\nPalavras com números são inválidas\n");
        verificar = 1;
        break;
      }
      if (palavra.charAt(i) == ' ') {
        System.out.println("\nPalavras com espaços são inválidas\n");
        verificar = 1;
        break;
      }

    }
    if (verificar == 0) {
      palavras.add(palavra);
      System.out.println("\nA palavra '" + palavra + "' foi inserida com sucesso!\n");
    }

  }

  // FUNÇÃO DE EXIBIR QUANTIDADE DE VITORIAS/DERROTAS
  public void exibirQuantVitoriaDerrota() {
    System.out.println("\n                Vitórias: " + quantVitorias);
    System.out.println("                Derrotas: " + quantDerrotas + "\n");
  }

  // FUNÇÕES PARA EXIBIR O RESULTADO DO JOGO
  public void ganhou() {
    status = "---------------------VITÓRIA----------------------";
    quantVitorias++;
    System.out.println("\nParabéns, você venceu! A palavra era: " + palavraCensurada);
  }

  public void perdeu() {
    status = "---------------------DERROTA----------------------";
    quantDerrotas++;
    System.out.println("\nInfelizmente você perdeu, a palavra era: " + palavras.get(indicePalavra));
  }

  // FUNÇÃO PARA SUBSTITUIR OS SIMBOLOS PELAS LETRAS DIGITADAS
  public void substituirSimbolo(int index) {
    palavraNova = new StringBuilder(palavraCensurada);
    palavraNova.setCharAt(index, letra);
    palavraCensurada = palavraNova.toString();
  }

}
