package show;

/**
 * Created by marg27 on 21/10/15.
 */
public class ProgressBar {
    public static ProgressBar instance = null;
    public static ProgressBar getInstance(){
        if(instance == null){
            instance = new ProgressBar();
        }
        return(instance);
    }

    public void printProgressBar(int percent,String message){
        StringBuilder bar = new StringBuilder("[");

        for(int i = 0; i < 50; i++){
            if( i < (percent/2)){
                bar.append("=");
            }else if( i == ((int)percent/2)){
                bar.append(">");
            }else{
                bar.append(" ");
            }
        }

        bar.append("]   " + percent + "%\t"+message);
        System.out.print("\r" + bar.toString());
    }
}
