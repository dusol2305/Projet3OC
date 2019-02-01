import com.tdevlee.jeux.Mastermind;
import org.junit.Assert;

public class MastermindTest {

    @org.junit.Test
    public void comparaisonIdentique() {
        Assert.assertArrayEquals(new int[]{0,4}, Mastermind.comparaison("1234","1234"));
    }
    @org.junit.Test
    public void comparaison4EnDoule() {
        Assert.assertArrayEquals(new int[]{0,4},Mastermind.comparaison("1244","1244"));
    }
    @org.junit.Test
    public void comparaison1EnDouble() {
        Assert.assertArrayEquals(new int[]{0,3},Mastermind.comparaison("1234","1244"));
    }
    @org.junit.Test
    public void comparaison3EnTriple() {
        Assert.assertArrayEquals(new int[]{0,2},Mastermind.comparaison("1234","1333"));
    }
    @org.junit.Test
    public void comparaison3EnTrouver() {
        Assert.assertArrayEquals(new int[]{0,3},Mastermind.comparaison("3333","1333"));
    }

}