public class Avaliacao {
    private int id;
    private int nota;

    public Avaliacao(int id, int nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
        this.id = id;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", nota=" + nota +
                '}';
    }
}