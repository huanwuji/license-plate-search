import org.junit.Test;

public class CodeTemplateTest {
    @Test
    public void testName() throws Exception {
        String $test = "test";
        String $strs = "strs";
        String $str = "str";
        CodeTemplate.create()._("import java.util.List")
                .$class("public class HelloWorld")
                .$def("public static void main(String args[])")
                ._("int $test = 10", $test)
                .$if("$test < 1", $test)
                ._("System.out.println($test)", $test)
                .$elseIf("$test >2", $test)
                ._("System.out.println()")
                .$else("$test > 3", $test)
                ._("System.out.println()")
                .$$if()
                ._("String[] $strs = {\"33\", \"44\"}", $strs)
                .$for("String $str : $strs", $str, $strs)
                ._("System.out.println($str)", $str)
                .$$for()
                .$$def()
                .$$class()
                .print();
    }
}