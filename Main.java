import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    int cont = 0;
    Scanner ler = new Scanner(System.in);
    Forca forca = new Forca();

    do {
      System.out.println("__________________________________________________");
      System.out.println("\n               JOGO DA FORCA                    ");
      System.out.println("\n1-Jogar");
      System.out.println("\n2-Ver estatísticas");
      System.out.println("\n3-Inserir nova palavra");
      System.out.println("\n4-Sair do jogo");
      System.out.println("__________________________________________________");
      cont = ler.nextInt();

      switch (cont) {
        case 1:
          forca.main();
          break;
        case 2:
          if (forca.estatisticas.isEmpty()) {
            System.out.println("\n               Não há estatísticas                ");
            break;
          }
          forca.exibirQuantVitoriaDerrota();
          for (int i = forca.estatisticas.size(); i > (forca.estatisticas.size() - forca.estatisticas.size()); i--) {
            int sla = forca.estatisticas.size() - forca.estatisticas.size();
            forca.estatisticas.get(i - 1).exibir();
          }
          break;
        case 3:
          forca.inserirPalavra();
          break;
        case 4:
          System.out.println("\n-----Saindo do jogo-----");
          break;

      }

    } while (cont != 4);

  }

}