import com.huanwuji.lps.domain.User;
import org.junit.Test;

public class CodeTemplateTest {
    @Test
    public void testName() throws Exception {
        String $test = "test";
        String $strs = "strs";
        String $str = "str";
        CodeTemplate.create().c("import java.util.List")
                .$class("public class HelloWorld")
                .$def("public static void main(String args[])")
                .c("int $test = 10", $test)
                .$if("$test < 1", $test)
                .c("System.out.println($test)", $test)
                .$elseIf("$test >2", $test)
                .c("System.out.println()")
                .$else("$test > 3", $test)
                .c("System.out.println()")
                .$$if()
                .c("String[] $strs = {\"33\", \"44\"}", $strs)
                .$for("String $str : $strs", $str, $strs)
                .c("System.out.println($str)", $str)
                .$$for()
                .getValue(User.class, "customer", "root", "$propValueVarName")
                .$$def()
                .$$class()
                .print();
    }

    @Test
    public void testName1() throws Exception {


    }
}