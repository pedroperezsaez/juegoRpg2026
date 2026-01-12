



public class Lluitador {
    static final int WIZARD=1;
    static final int OGRE=2;
    static final int HIDRA=3;
    static final int DEMON=4;
    static final int DRAGON=5;
    String nom;
    int nivell;
    int clase;

    int punts;
    int puntsDeVida;
    int puntsDeVidaMaxim;
    int atac;
    int defensa;
    int atacOriginal;
    int defensaOriginal;
    int torn;
    int tornRecupera=0;

    //Constructor
    public  Lluitador(String nom, int clase, int punts,  int puntsDeVida,int puntsDeVidaMaxim, int atac, int defensa){
        this.nom=nom;
        this.clase=clase;
        this.punts=punts;
        this.puntsDeVida=puntsDeVida;
        this.puntsDeVidaMaxim=puntsDeVidaMaxim;
        this.atac=atac;
        this.defensa=defensa;
        this.atacOriginal=atac;
        this.defensaOriginal=defensa;
        this.nivell=1;
        this.torn=1;
    }
    void incTorn(){
        this.torn++;
        if (this.torn==this.tornRecupera){
            this.atac=this.atacOriginal;
            this.defensa=this.defensaOriginal;
        }
    }
void penalitza(){
        //divideix per 2 la capacitat d'atac o defensa(aleatori
    //nomes dura 1 torn
        if(Math.random()<0.5){
            this.defensa/=2;
        }else {
            this.atac/=2;
        }
        int tornRecupera=this.torn+2;
}
    void restaPuntsDeVida(int punts){
        this.puntsDeVida-=punts;
        if(this.puntsDeVida<0){
            this.puntsDeVida=0;
        }
    }
    void  restaPuntsDeVidaX2(int punts){
        this.puntsDeVida-=punts*2;
        if(this.puntsDeVida<0){
            this.puntsDeVida=0;
        }
    }
    void recuperaVida(int punts){
        this.puntsDeVida+=punts;
        if(this.puntsDeVida>this.puntsDeVidaMaxim){
            this.puntsDeVida=this.puntsDeVidaMaxim;
        }
    }

    public int getNivell() {
        return nivell;
    }

    public void setNivell(int nivell) {
        this.nivell = nivell;
    }

    public int getPuntsDeVida() {
        return puntsDeVida;
    }

    public void setPuntsDeVida(int puntsDeVida) {
        this.puntsDeVida = puntsDeVida;
    }

    String getNom(){
        return this.nom;
    }
    void setNom(String nom){
        this.nom=nom;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getPuntsDeVidaMaxim() {
        return puntsDeVidaMaxim;
    }

    public void setPuntsDeVidaMaxim(int puntsDeVidaMaxim) {
        this.puntsDeVidaMaxim = puntsDeVidaMaxim;
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
