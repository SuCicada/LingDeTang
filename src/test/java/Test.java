import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/23/19
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        List a = new ArrayList();
        a.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer)o1-(Integer)o2;
            }

        });

        System.out.println(args);
    }

}
