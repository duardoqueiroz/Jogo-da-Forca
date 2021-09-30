import java.util.Date;

public class Estatisticas {
  private int vidas, tentativas;
  private String palavra;
  private String status;
  private Date data;

  public Estatisticas(String status, int vidas, Date data, String palavra, int tentativas) {
    this.vidas = vidas;
    this.data = data;
    this.palavra = palavra;
    this.status = status;
    this.tentativas = tentativas;
  }

  public void exibir() {
    System.out.println("\n" + status);
    System.out.println("     Quantidade de vidas: " + vidas);
    System.out.println("     Tentativas: " + tentativas);
    System.out.println("     Palavra: " + palavra);
    System.out.println("     Jogou em: " + data);
    System.out.println("__________________________________________________");
  }

}
