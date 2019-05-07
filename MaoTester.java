/**
 * Does Mao work?!?!?
 */
public class MaoTester
{
    public static void main(String [] args){
        for(int i=0; i<21; i++){
            int rule = Mao.randyRules(i%3+2);
            System.out.println(rule+": "+Mao.stringRule(rule));
        }
    }
}