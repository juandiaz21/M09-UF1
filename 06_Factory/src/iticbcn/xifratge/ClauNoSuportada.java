package iticbcn.xifratge;

public class ClauNoSuportada extends Exception{
    public ClauNoSuportada(String message){
        super(message);
    }

    public void esNull(String entrada) throws ClauNoSuportada {
        if (!entrada.isEmpty()) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
    }
}
