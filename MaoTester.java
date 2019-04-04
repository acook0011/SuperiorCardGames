/**
 * Does Mao work?!?!?
 */
public class MaoTester
{
    public static void main(String [] args){
        int human = Mao.randyRules(1);
        int alice = Mao.randyRules(2);
        int bob = Mao.randyRules(3);
        int charlie = Mao.randyRules(4);
        System.out.println("Here are some random rules.");
        System.out.println(human+": "+Mao.stringRule(human));
        System.out.println(alice+": "+Mao.stringRule(alice));
        System.out.println(bob+": "+Mao.stringRule(bob));
        System.out.println(charlie+": "+Mao.stringRule(charlie));
    }
}