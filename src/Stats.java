public class Stats {
    int vida;
    int atac;
    int defensa;
    Stats(int vida, int atac, int defensa){
        this.vida=vida;
        this.atac=atac;
        this.defensa=defensa;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtac() {
        return atac;
    }

    public void setAtac(int atac) {
        this.atac = atac;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}
