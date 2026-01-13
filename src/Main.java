import java.util.Scanner;

public class Main {

    static final int ATAC=1;
    static final int DEFENSA=2;
    static final int MANIOBRA=3;
    static final int ENGANY=4;

    public static void main(String[] args) {
        /*
        L'objectiu del programa consisteix en implementar un joc de combat amb unes regles molt ben definides:
        lluitador:
        -Nom
        -Clase o tipo(Wizard, Ogre, Hidra...)
        -Nivell: Capacitat egneral de combat del lluitador
        -Punts que ha obtingut
        -Punts de vida:la vida del lluitador.
        -Punts de vida maxim
        -Atac
        -Defensa


        Estrategia: ATAC, DEFENSA, ENGANY I MANIOBRA

        Efectes:
        -Res: no pasa res
        -Dany: el lluitador perd una cuantitat de punts de vida igual al grau d'èxit del contrincant
        -Guarit: El lluitador recupera punts de vida, sense superar mai els punts de vida maxims

        -Penalitzat: el lluitador veu penalitzat el seu valor d'atac o de defensa(es tria aleatoriament)


                ATAC            DEFENSA         ENGANY          MANIOBRA
       ATAC     dany2           adv.guarit      adv. dany       adv. dany
       DEFENSA  jug. guarit     guarit2         jug. dany*2     jug. pen
       ENGANY   jug. dany       adv. dany*2     dany2           jug.pen
       MANIOBRA jug.dany        adv.pen         adv. pen        pen2




         */
        Stats[] stats= new Stats[5];
        stats[Lluitador.DEMON-1]=new Stats(35,50,6);
        stats[Lluitador.WIZARD-1]=new Stats(40,15,4);
        stats[Lluitador.OGRE-1]=new Stats(45,15,3);
        stats[Lluitador.HIDRA-1]=new Stats(25,20,8);
        stats[Lluitador.DRAGON-1]=new Stats(35,12,6);
        Lluitador l1=newLluitador("Jack", stats, eleccionJugador());
        Lluitador l2=newLluitador("P", stats, eleccionBot());


        //Lluitador l1=new Lluitador("Jack",Lluitador.HIDRA,0,50,70,10,8);
        //Lluitador l2=new Lluitador("John", Lluitador.WIZARD,0,30,50,12,5);

        while (true){

            lluita(l1,l2);
            if (!l1.teVida()){
                System.out.println("Ha guanyat el bot");
                break;
            }else if (!l2.teVida()){
                System.out.println("HAS GUANYAT");

            }
        }
        //implementar reglas de combate

    }

    static int eleccionJugador(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("escribe si la clase que quieres(demon, wizard, ogre, hidra, dragon");
        String eleccion= scanner.nextLine().toUpperCase();
        if (eleccion.equals("DEMON")){
            return Lluitador.DEMON;
        }if (eleccion.equals("WIZARD")){
            return Lluitador.WIZARD;
        }if (eleccion.equals("OGRE")){
            return Lluitador.OGRE;
        }if (eleccion.equals("HIDRA")){
            return Lluitador.HIDRA;
        }if (eleccion.equals("DRAGON")){
            return Lluitador.DRAGON;
        }
        return 0;

    }

    static int eleccionBot(){
        int n=(int)(Math.random()*5);
        if (n==1){
            return Lluitador.DEMON;
        }if (n==2){
            return Lluitador.WIZARD;
        }if (n==3){
            return Lluitador.OGRE;
        }if (n==4){
            return Lluitador.HIDRA;
        }
        return Lluitador.DRAGON;


    }

    static void imprimeixPantalla(Lluitador l1, Lluitador l2){
        Screen.clearScreen();
        Screen.liniaVertical(0,65,12);
        int colJug=40;
        int colJug2= 65+colJug;
        //jugador1
        Screen.printGraphics(Graphics.getGraphic(l1.getClase()),0,1);
        Screen.print("Nom: "+l1.getNom(),1,colJug);
        Screen.print("Vida: "+l1.getPuntsDeVida()+ " / " +l1.puntsDeVidaMaxim,2,colJug);
        Screen.print("Atac: "+l1.getAtac(),3,colJug);
        Screen.print("Defensa: "+l1.getDefensa(),4,colJug);

        //jugador2
        Screen.printGraphics(Graphics.getGraphic(l2.getClase()),0,70);

        Screen.print("Nom: "+l2.getNom(),1,colJug2);
        Screen.print("Vida: "+l2.getPuntsDeVida()+ " / " +l2.puntsDeVidaMaxim,2,colJug2);
        Screen.print("Atac: "+l2.getAtac(),3,colJug2);
        Screen.print("Defensa: "+l2.getDefensa(),4,colJug2);
        Screen.show();
    }
    static  Lluitador newLluitador(String nom, Stats[] stats, int clase){
        Stats s=stats[clase-1];
        return new Lluitador(nom,clase,0,s.vida, s.vida , s.atac, s.defensa);
    }
    static void imprimeixJugador(Lluitador lluitador){
        System.out.printf("Nom: %s, Vida: %d, Atac: %d, Defensa: %d \n ",lluitador.getNom(),lluitador.getPuntsDeVida(),lluitador.getAtac(),lluitador.getDefensa());

    }
    static void imprimeixEstrategiaICares(String nom, int estrategia, int cares){
        String[] ar={"ATAC", "DEFENSA", "MANIOBRA", "ENGANY"};
        System.out.printf("Estategia (%s): %s, cares: %d \n",nom,ar[estrategia-1], cares);
    }
    static  void lluita(Lluitador l1, Lluitador l2){
        //imprimeixJugador(l1);
        //imprimeixJugador(l2);
        imprimeixPantalla(l1,l2);
        //El jugador l1 tira primer


        //Triar estategia
        int estrategia1=triaEstrategiaHuma();
        int estrategia2=triaEstrategiaBot();
        //Llençar monedes
        int cares1= tiraMonedes(l1,estrategia1);
        int cares2= tiraMonedes(l2, estrategia2);
        imprimeixEstrategiaICares(l1.getNom(), estrategia1,cares1);
        imprimeixEstrategiaICares(l2.getNom(), estrategia2,cares2);

        l1.incTorn();
        l2.incTorn();
        resolCombat(l1,l2,estrategia1,estrategia2,cares1,cares2);



    }

    static void resolCombat(Lluitador l1, Lluitador l2, int estrategia1, int estrategia2, int cares1, int cares2){

        if(estrategia1== ATAC && estrategia2==ATAC){
            l1.restaPuntsDeVida(cares2);
            l2.restaPuntsDeVida(cares1);
        }else if(estrategia1==ATAC && estrategia2== DEFENSA){
            l2.recuperaVida(cares1);
        } else if(estrategia1==ATAC && estrategia2==ENGANY){
            l2.restaPuntsDeVida(cares1);
        }else if(estrategia1==ATAC && estrategia2==MANIOBRA){
            l2.restaPuntsDeVida(cares1);
        }else if(estrategia1==DEFENSA && estrategia2==ATAC){
            l1.restaPuntsDeVida(cares2);
        } else if (estrategia1==DEFENSA && estrategia2== DEFENSA) {
        } else if(estrategia1==DEFENSA && estrategia2==ENGANY){
            l1.restaPuntsDeVidaX2(cares2);
        }else if(estrategia1==DEFENSA && estrategia2==MANIOBRA){
            l1.penalitza();
        } else if (estrategia1==ENGANY && estrategia2==ATAC) {
            l1.restaPuntsDeVida(cares2);
        } else if (estrategia1==ENGANY && estrategia2==DEFENSA) {
            l2.restaPuntsDeVidaX2(cares1);
        } else if (estrategia1==ENGANY && estrategia2==ENGANY) {
            l1.restaPuntsDeVida(cares2);
            l2.restaPuntsDeVida(cares1);
        } else if (estrategia1==ENGANY && estrategia2==MANIOBRA) {
            l1.penalitza();
        } else if (estrategia1==MANIOBRA && estrategia2==ATAC) {
            l1.restaPuntsDeVida(cares2);
        } else if (estrategia1==MANIOBRA && estrategia2==DEFENSA) {
            l2.penalitza();
        } else if (estrategia1==MANIOBRA && estrategia2==ENGANY) {
            l2.penalitza();

        } else if (estrategia1==MANIOBRA && estrategia2== MANIOBRA) {
            l2.penalitza();
            l1.penalitza();

        }
    }

    /*
              ATAC            DEFENSA         ENGANY          MANIOBRA
       ATAC     dany2           adv.guarit      adv. dany       adv. dany
       DEFENSA  jug. guarit     guarit2         jug. dany*2     jug. pen
       ENGANY   jug. dany       adv. dany*2     dany2           jug.pen
       MANIOBRA jug.dany        adv.pen         adv. pen        pen2
     */
    static int tiraMonedes(Lluitador lluitador, int estrategia){
        int ncares=0;
        int quantes=0;
        if (estrategia==ATAC || estrategia==ENGANY) quantes=lluitador.getAtac();
        if (estrategia==DEFENSA || estrategia==MANIOBRA) quantes= lluitador.getDefensa();
        for (int i = 0; i < quantes; i++) {
            if(Math.random()<0.5){
                ncares++;
            }

        }
        return ncares;
    }

    static int triaEstrategiaBot(){
        int[] estrategies={ATAC,DEFENSA,ENGANY,MANIOBRA};
        int n=(int)(Math.random()*estrategies.length);
        return estrategies[n];
    }
    static int triaEstrategiaHuma(){

        while (true){

            Scanner scanner=new Scanner(System.in);
            System.out.println("Tria estategia; (A/D/E/M");
            String resp= scanner.nextLine().toUpperCase();
            if(resp.length()==0)continue;
            if(resp.charAt(0)=='A'){
                return ATAC;
            }
            if(resp.charAt(0)=='D'){
                return DEFENSA;
            } if(resp.charAt(0)=='E'){
                return ENGANY;
            }if (resp.charAt(0)=='M'){
                return MANIOBRA;
            }
        }  //tengo que seguir
      /*  //int estrategia=0;
        if(resp.charAt(0)=='A'){
            estrategia=ATAC;
        } else if (resp.charAt(0)=='D') {
            estrategia=DEFENSA;
        } else if (resp.charAt(0)=='E') {
            estrategia=ENGANY;
        } else if (resp.charAt(0)=='M') {
            estrategia=MANIOBRA;
        }
        return estrategia;
         */

    }


}